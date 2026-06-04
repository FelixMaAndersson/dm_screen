package se.yrgo.services.encounter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.*;
import se.yrgo.domain.*;
import se.yrgo.domain.campaign.Campaign;
import se.yrgo.domain.enums.Difficulty;
import se.yrgo.dto.encounter.*;
import se.yrgo.dto.encounterMonster.EncounterMonsterSummary;
import se.yrgo.exceptions.campaign.CampaignNotFoundException;
import se.yrgo.exceptions.encounter.EncounterNotFoundException;
import se.yrgo.exceptions.encounterMonster.EncounterMonsterNotFoundException;
import se.yrgo.exceptions.monster.MonsterNotFoundException;
import se.yrgo.exceptions.playerCharacter.PlayerCharacterNotFoundException;

import java.util.List;

@Service
@Transactional
public class EncounterService {

    private final EncounterRepository encounterRepository;
    private final CampaignRepository campaignRepository;
    private final PlayerCharacterRepository playerCharacterRepository;
    private final EncounterMonsterRepository encounterMonsterRepository;
    private final EncounterDifficultyCalculator difficultyCalculator;
    private final MonsterRepository monsterRepository;

    @Autowired
    public EncounterService(EncounterRepository encounterRepository, CampaignRepository campaignRepository, PlayerCharacterRepository playerCharacterRepository, EncounterMonsterRepository encounterMonsterRepository, EncounterDifficultyCalculator difficultyCalculator, MonsterRepository monsterRepository) {
        this.encounterRepository = encounterRepository;
        this.campaignRepository = campaignRepository;
        this.playerCharacterRepository = playerCharacterRepository;
        this.encounterMonsterRepository = encounterMonsterRepository;
        this.difficultyCalculator = difficultyCalculator;
        this.monsterRepository = monsterRepository;
    }

    // CREATE

    public Encounter createEncounter(CreateEncounterRequest request) {
        Campaign campaign = campaignRepository.findById(request.campaignId())
                .orElseThrow(() -> new CampaignNotFoundException(request.campaignId()));

        Encounter e = new Encounter(
                request.habitat(),
                request.visionDistance(),
                campaign
        );

        return encounterRepository.save(e);
    }

    // READ

    public List<EncounterResponse> getAllEncounters() {
        return encounterRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<EncounterResponse> getAllEncountersInCampaign(Long campaignId) {
        return encounterRepository.findByCampaignId(campaignId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public EncounterResponse getEncounterById(Long id) {
        return toResponse(getOrThrow(id));
    }

    // UPDATE

    public EncounterResponse addCharacterToEncounter(Long id, UpdatePlayerCharacterRequest request) {
        Encounter e = getOrThrow(id);


        PlayerCharacter pc = playerCharacterRepository.findById(request.playerCharacterId())
                .orElseThrow(() -> new PlayerCharacterNotFoundException(request.playerCharacterId()));

        e.addPlayerCharacter(pc);

        return toResponse(e);
    }

    public EncounterResponse addMonsterToEncounter(Long id, AddMonsterToEncounterRequest request) {
        Encounter e = getOrThrow(id);

        Monster monster = monsterRepository.findById(request.monsterId())
                .orElseThrow(() -> new MonsterNotFoundException(request.monsterId()));

        EncounterMonster em = new EncounterMonster(
                monster,
                e,
                monster.getHp(),
                true,
                request.boss(),
                request.notes()
        );

        e.addEncounterMonster(em);

        return toResponse(e);
    }

    public EncounterResponse removeCharacterFromEncounter(Long id, UpdatePlayerCharacterRequest request) {
        Encounter e = getOrThrow(id);


        PlayerCharacter pc = playerCharacterRepository.findById(request.playerCharacterId())
                .orElseThrow(() -> new PlayerCharacterNotFoundException(request.playerCharacterId()));

        e.removePlayerCharacter(pc);

        return toResponse(e);
    }

    public EncounterResponse removeMonsterFromEncounter(Long id, RemoveEncounterMonsterFromEncounterRequest request) {
        Encounter e = getOrThrow(id);

        EncounterMonster em = encounterMonsterRepository.findById(request.encounterMonsterId())
                .orElseThrow(() -> new EncounterMonsterNotFoundException(request.encounterMonsterId()));

        e.removeEncounterMonster(em);

        return toResponse(e);
    }

    // BUSINESS LOGIC - DIFFICULTY

    public Difficulty calculateDifficulty(Long id) {

        Encounter encounter = getOrThrow(id);

        return difficultyCalculator.calculate(encounter);
    }

    public EncounterResponse buildEncounterWithDifficulty(
            Long encounterId,
            BuildEncounterRequest request) {

        Encounter encounter = getOrThrow(encounterId);

        List<Monster> candidates = monsterRepository.findByHabitat(request.habitat());

        for (Monster monster : candidates) {
            EncounterMonster em = new EncounterMonster(
                    monster,
                    encounter,
                    monster.getHp(),
                    true,
                    false,
                    ""
            );

            encounter.addEncounterMonster(em);

            Difficulty currentDifficulty = difficultyCalculator.calculate(encounter);

            if (currentDifficulty == request.targetDifficulty()) {
                return toResponse(encounter);
            }

            if (isHarderThan(currentDifficulty, request.targetDifficulty())) {
                encounter.removeEncounterMonster(em);
            }
        }

        return toResponse(encounter);
    }

    public List<EncounterResponse> getEncountersByDifficulty(Difficulty difficulty) {
        return encounterRepository.findAll()
                .stream()
                .filter(e -> difficultyCalculator.calculate(e) == difficulty)
                .map(this::toResponse)
                .toList();
    }

    // DELETE

    public void deleteEncounter(Long id) {
        Encounter e = getOrThrow(id);
        encounterRepository.delete(e);
    }

    // HELP METHODS

    private boolean isHarderThan(Difficulty current, Difficulty target) {
        return current.ordinal() > target.ordinal();
    }

    private Encounter getOrThrow(Long id) {
        return encounterRepository.findById(id)
                .orElseThrow(() -> new EncounterNotFoundException(id));
    }

    private EncounterResponse toResponse(Encounter e) {
        List<EncounterMonsterSummary> monsters =
                e.getEncounterMonsters()
                        .stream()
                        .map(em -> new EncounterMonsterSummary(
                                em.getId(),
                                em.getMonster().getId(),
                                em.getMonster().getName()
                        ))
                        .toList();

        Difficulty difficulty = difficultyCalculator.calculate(e);

        return new EncounterResponse(
                e.getId(),
                e.getHabitat(),
                e.getVisionDistance(),
                e.getCampaign().getId(),
                e.getCampaign().getName(),
                monsters,
                difficulty,
                e.getLore()
        );
    }
}

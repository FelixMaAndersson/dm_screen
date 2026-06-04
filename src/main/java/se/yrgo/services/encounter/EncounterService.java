package se.yrgo.services.encounter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.*;
import se.yrgo.domain.*;
import se.yrgo.domain.enums.Difficulty;
import se.yrgo.dto.encounter.*;
import se.yrgo.dto.encounterMonster.EncounterMonsterSummary;
import se.yrgo.exceptions.CampaignNotFoundException;
import se.yrgo.exceptions.EncounterMonsterNotFoundException;
import se.yrgo.exceptions.EncounterNotFoundException;
import se.yrgo.exceptions.PlayerCharacterNotFoundException;

import java.util.List;

@Service
@Transactional
public class EncounterService {

    private final EncounterRepository repository;
    private final CampaignRepository campaignRepository;
    private final PlayerCharacterRepository playerCharacterRepository;
    private final EncounterMonsterRepository encounterMonsterRepository;
    private final EncounterDifficultyCalculator difficultyCalculator;

    @Autowired
    public EncounterService(EncounterRepository repository, CampaignRepository campaignRepository, PlayerCharacterRepository playerCharacterRepository, EncounterMonsterRepository encounterMonsterRepository, EncounterDifficultyCalculator difficultyCalculator) {
        this.repository = repository;
        this.campaignRepository = campaignRepository;
        this.playerCharacterRepository = playerCharacterRepository;
        this.encounterMonsterRepository = encounterMonsterRepository;
        this.difficultyCalculator = difficultyCalculator;
    }

    // CREATE

    public Encounter createEncounter(CreateEncounterRequest request) throws CampaignNotFoundException {
        Campaign campaign = campaignRepository.findById(request.campaignId())
                .orElseThrow(() -> new CampaignNotFoundException(request.campaignId()));

        Encounter e = new Encounter(
                request.habitat(),
                request.visionDistance(),
                campaign
        );

        return repository.save(e);
    }

    // READ

    private Encounter getEncounterById(Long id) {
        return getOrThrow(id);
    }

    public List<Encounter> getAllEncounters() {
        return repository.findAll();
    }

    public List<EncounterResponse> getAllEncountersInCampaign(Long campaignId) {
        return repository.findByCampaignId(campaignId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<EncounterResponse> getEncountersByDifficulty(Difficulty difficulty) {
        return repository.findAll()
                .stream()
                .filter(e -> difficultyCalculator.calculate(e) == difficulty)
                .map(this::toResponse)
                .toList();
    }


    // UPDATE

    public EncounterResponse addCharacterToEncounter(Long id, UpdatePlayerCharacterRequest request) {
        Encounter e = getOrThrow(id);


        PlayerCharacter pc = playerCharacterRepository.findById(request.playerCharacterId())
                .orElseThrow(() -> new PlayerCharacterNotFoundException(request.playerCharacterId()));

        e.addPlayerCharacter(pc);

        return toResponse(e);
    }

    public EncounterResponse addMonsterToEncounter(Long id, UpdateMonsterInEncounterRequest request) {
        Encounter e = getOrThrow(id);

        EncounterMonster em = encounterMonsterRepository.findById(request.encounterMonsterId())
                .orElseThrow(() -> new EncounterMonsterNotFoundException(request.encounterMonsterId()));

        e.addEncounterMonster(em);

        return toResponse(e);
    }

    // BUSINESS LOGIC - DIFFICULTY

    public Difficulty calculateDifficulty(Long id) {

        Encounter encounter = getOrThrow(id);

        return difficultyCalculator.calculate(encounter);
    }

    public EncounterResponse buildEncounterWithDifficulty(
            Long id,
            Difficulty targetDifficulty) {

        Encounter e = getOrThrow(id);

        //Much code needed here

        return toResponse(e);
    }


    // DELETE

    public void deleteEncounter(Long id) {
        Encounter e = getOrThrow(id);
        repository.delete(e);
    }

    public EncounterResponse removeCharacterFromEncounter(Long id, UpdatePlayerCharacterRequest request) {
        Encounter e = getOrThrow(id);


        PlayerCharacter pc = playerCharacterRepository.findById(request.playerCharacterId())
                .orElseThrow(() -> new PlayerCharacterNotFoundException(request.playerCharacterId()));

        e.removePlayerCharacter(pc);

        return toResponse(e);
    }

    public EncounterResponse removeMonsterFromEncounter(Long id, UpdateMonsterInEncounterRequest request) {
        Encounter e = getOrThrow(id);

        EncounterMonster em = encounterMonsterRepository.findById(request.encounterMonsterId())
                .orElseThrow(() -> new EncounterMonsterNotFoundException(request.encounterMonsterId()));

        e.removeEncounterMonster(em);

        return toResponse(e);
    }


    // HELP METHODS

    private Encounter getOrThrow(Long id) {
        return repository.findById(id)
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

package se.yrgo.services.encounter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.CampaignRepository;
import se.yrgo.dataaccess.EncounterRepository;
import se.yrgo.domain.Campaign;
import se.yrgo.domain.Encounter;
import se.yrgo.domain.EncounterMonster;
import se.yrgo.domain.Monster;
import se.yrgo.dto.encounter.CreateEncounterRequest;
import se.yrgo.dto.encounter.EncounterResponse;
import se.yrgo.dto.encounterMonster.EncounterMonsterSummary;
import se.yrgo.exceptions.CampaignNotFoundException;
import se.yrgo.exceptions.EncounterNotFoundException;

import java.util.List;

@Service
@Transactional
public class EncounterService {

    private final EncounterRepository repository;
    private final CampaignRepository campaignRepository;

    @Autowired
    public EncounterService(EncounterRepository repository, CampaignRepository campaignRepository) {
        this.repository = repository;
        this.campaignRepository = campaignRepository;
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

        return new EncounterResponse(
                e.getId(),
                e.getHabitat(),
                e.getVisionDistance(),
                e.getCampaign().getId(),
                e.getCampaign().getName(),
                monsters,
                e.getEncounterDifficulty(),
                e.getLore()
        );
    }

}

package se.yrgo.services.encounter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.CampaignRepository;
import se.yrgo.dataaccess.EncounterRepository;
import se.yrgo.domain.Campaign;
import se.yrgo.domain.Encounter;
import se.yrgo.dto.encounter.CreateEncounterRequest;
import se.yrgo.exceptions.CampaignNotFoundException;

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


}

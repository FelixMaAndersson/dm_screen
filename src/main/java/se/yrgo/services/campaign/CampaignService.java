package se.yrgo.services.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.dataaccess.CampaignRepository;
import se.yrgo.dataaccess.PlayerRepository;
import se.yrgo.domain.Campaign;
import se.yrgo.domain.User;
import se.yrgo.exceptions.CampaignNotFoundException;

import java.util.List;


@Service
public class CampaignService {

    private final CampaignRepository repository;


    @Autowired
    public CampaignService(CampaignRepository repository) {
        this.repository = repository;
    }

    public Campaign createCampaign(String name, String description, User dm) {

        Campaign campaign = new Campaign(name, description, dm);

        return repository.save(campaign);
    }

    public Campaign getCampaignById(Long id) throws CampaignNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException(id));
    }

    public Campaign getCampaignByName(String name) throws CampaignNotFoundException {
        return repository.findByCampaignName(name)
                .orElseThrow(() -> new CampaignNotFoundException(name));
    }

    public void deleteCampaign(Long id) {
        repository.deleteById(id);
    }

    public List<Campaign> getAllCampaigns() {
        return repository.findAll();
    }

}

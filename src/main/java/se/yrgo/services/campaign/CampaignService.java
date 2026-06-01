package se.yrgo.services.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.dataaccess.CampaignRepository;
import se.yrgo.domain.Campaign;
import se.yrgo.domain.User;
import se.yrgo.exceptions.CampaignNotFoundException;
import se.yrgo.exceptions.UserNotFoundException;
import se.yrgo.services.user.UserService;

import java.util.List;
import java.util.Optional;


@Service
public class CampaignService {

    private final CampaignRepository repository;
    private final UserService userService;


    @Autowired
    public CampaignService(CampaignRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public Campaign createCampaign(String name, Long dmId) throws UserNotFoundException {
        User dm = userService.getUserById(dmId);

        Campaign campaign = new Campaign(name, dm);

        return repository.save(campaign);
    }

    public Campaign getCampaignById(Long id) throws CampaignNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException(id));
    }

    public Campaign getCampaignByName(String name) throws CampaignNotFoundException {
        return repository.findByName(name)
                .orElseThrow(() -> new CampaignNotFoundException(name));
    }

    public void deleteCampaign(Long id) {
        repository.deleteById(id);
    }

    public List<Campaign> getAllCampaigns() {
        return repository.findAll();
    }

    public Campaign updateCampaign(Long id, String name, String description, User dm) throws CampaignNotFoundException {
        Campaign campaign = repository.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException(id));

        campaign.setName(name);
        campaign.setDescription(description);
        campaign.setDm(dm);
        return repository.save(campaign);
    }

}

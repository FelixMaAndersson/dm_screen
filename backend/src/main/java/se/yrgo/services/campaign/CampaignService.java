package se.yrgo.services.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.CampaignRepository;
import se.yrgo.dataaccess.UserRepository;
import se.yrgo.domain.campaign.Campaign;
import se.yrgo.domain.User;
import se.yrgo.dto.campaign.CreateCampaignRequest;
import se.yrgo.dto.campaign.UpdateCampaignRequest;
import se.yrgo.exceptions.campaign.CampaignNotFoundException;
import se.yrgo.exceptions.user.UserNotFoundException;

import java.util.List;


@Service
@Transactional
public class CampaignService {

    private final CampaignRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public CampaignService(CampaignRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }


    // CREATE

    public Campaign createCampaign(CreateCampaignRequest request) throws UserNotFoundException {

        User dm = userRepository.findById(request.dmId())
                .orElseThrow(() ->
                        new UserNotFoundException(request.dmId()));

        Campaign campaign = new Campaign(
                request.name(),
                dm,
                request.currentDate()
        );

        return repository.save(campaign);
    }

    // READ

    public Campaign getCampaignById(Long id) throws CampaignNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException(id));
    }

    public Campaign getCampaignByName(String name) throws CampaignNotFoundException {
        return repository.findByName(name)
                .orElseThrow(() -> new CampaignNotFoundException(name));
    }

    public List<Campaign> getAllCampaigns() {
        return repository.findAll();
    }

    // UPDATE

    public Campaign updateCampaign(Long id, UpdateCampaignRequest request) throws CampaignNotFoundException, UserNotFoundException {
        Campaign campaign = repository.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException(id));


        User dm = userRepository.findById(request.dmId())
                .orElseThrow(() -> new UserNotFoundException(request.dmId()));

        campaign.setName(request.name());
        campaign.setDescription(request.description());
        campaign.setDm(dm);
        return repository.save(campaign);
    }

    // DELETE

    public void deleteCampaign(Long id) {
        repository.deleteById(id);
    }

}

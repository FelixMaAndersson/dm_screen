package se.yrgo.services.playerCharacter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.PlayerCharacterRepository;
import se.yrgo.domain.*;
import se.yrgo.dto.CreatePlayerCharacterRequest;
import se.yrgo.exceptions.CampaignNotFoundException;
import se.yrgo.exceptions.CharacterNotFoundException;
import se.yrgo.services.campaign.CampaignService;

import java.util.List;


@Service
@Transactional
public class PlayerCharacterService {

    private final PlayerCharacterRepository repository;
    private final CampaignService campaignService;

    @Autowired
    public PlayerCharacterService(PlayerCharacterRepository repository, CampaignService campaignService) {
        this.repository = repository;
        this.campaignService = campaignService;
    }


    public PlayerCharacter createCharacter(
            Long campaignId,
            CreatePlayerCharacterRequest request) throws CampaignNotFoundException {

        Campaign campaign = campaignService.getCampaignById(campaignId);

        PlayerCharacter character = new PlayerCharacter(
                request.name(),
                request.race(),
                request.characterClass(),
                request.level(),
                campaign
        );

        return repository.save(character);
    }


    public PlayerCharacter getCharacterById(Long id) throws CharacterNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(id));
    }

    public PlayerCharacter getCharacterByName(String name) throws CharacterNotFoundException {
        return repository.findByName(name)
                .orElseThrow(() -> new CharacterNotFoundException(name));
    }

    public void deleteCharacter(Long id) {
        repository.deleteById(id);
    }

    public List<PlayerCharacter> getAllCharacters() {
        return repository.findAll();
    }

    public List<PlayerCharacter> getCharactersForCampaign(Long campaignId) {
        return repository.findByCampaignId(campaignId);
    }

}
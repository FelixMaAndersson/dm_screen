package se.yrgo.services.playerCharacter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.PlayerCharacterRepository;
import se.yrgo.domain.*;
import se.yrgo.dto.character.CreatePlayerCharacterRequest;
import se.yrgo.dto.character.UpdatePlayerCharacterRequest;
import se.yrgo.exceptions.campaign.CampaignNotFoundException;
import se.yrgo.exceptions.playerCharacter.CharacterNotFoundException;
import se.yrgo.exceptions.user.UserNotFoundException;
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

    // CREATE

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

    // READ

    public PlayerCharacter getCharacterById(Long id) throws CharacterNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(id));
    }

    public PlayerCharacter getCharacterByName(String name) throws CharacterNotFoundException {
        return repository.findByName(name)
                .orElseThrow(() -> new CharacterNotFoundException(name));
    }

    public List<PlayerCharacter> getAllCharacters() {
        return repository.findAll();
    }

    public List<PlayerCharacter> getCharactersForCampaign(Long campaignId) {
        return repository.findByCampaignId(campaignId);
    }

    // UPDATE

    public PlayerCharacter updateCharacter(
            Long id,
            UpdatePlayerCharacterRequest request) throws UserNotFoundException {

        PlayerCharacter character = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        character.setName(request.name());
        character.setRace(request.race());
        character.setCharacterClass(request.characterClass());
        character.setLevel(request.level());

        return repository.save(character);
    }

    // DELETE

    public void deleteCharacter(Long id) {
        repository.deleteById(id);
    }

}
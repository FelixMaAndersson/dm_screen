package se.yrgo.services.playerCharacter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.CampaignRepository;
import se.yrgo.dataaccess.PlayerCharacterRepository;
import se.yrgo.domain.*;
import se.yrgo.domain.campaign.Campaign;
import se.yrgo.dto.campaign.CampaignResponse;
import se.yrgo.dto.character.CreatePlayerCharacterRequest;
import se.yrgo.dto.character.PlayerCharacterResponse;
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
    private final CampaignRepository campaignRepository;

    @Autowired
    public PlayerCharacterService(PlayerCharacterRepository repository, CampaignRepository campaignRepository) {
        this.repository = repository;

        this.campaignRepository = campaignRepository;
    }

    // CREATE

    public PlayerCharacterResponse createCharacter(
            Long campaignId,
            CreatePlayerCharacterRequest request) throws CampaignNotFoundException {

        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException(campaignId));

        PlayerCharacter character = new PlayerCharacter(
                request.name(),
                request.race(),
                request.characterClass(),
                request.level(),
                campaign
        );

        return toResponse(repository.save(character));
    }

    // READ

    public PlayerCharacterResponse getCharacterById(Long id) throws CharacterNotFoundException {
        return toResponse(repository.findById(id).orElseThrow(() -> new CharacterNotFoundException(id)));
    }

    public PlayerCharacter getCharacterByName(String name) throws CharacterNotFoundException {
        return repository.findByName(name)
                .orElseThrow(() -> new CharacterNotFoundException(name));
    }

    public List<PlayerCharacterResponse> getAllCharacters() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<PlayerCharacterResponse> getCharactersForCampaign(Long campaignId) {
        return repository.findByCampaignId(campaignId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // UPDATE

    public PlayerCharacterResponse updateCharacter(
            Long id,
            UpdatePlayerCharacterRequest request) throws UserNotFoundException {

        PlayerCharacter character = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        character.setName(request.name());
        character.setRace(request.race());
        character.setCharacterClass(request.characterClass());
        character.setLevel(request.level());

        return toResponse(repository.save(character));
    }

    // DELETE

    public void deleteCharacter(Long id) {
        repository.deleteById(id);
    }

    // HELP METHODS

    private PlayerCharacterResponse toResponse(PlayerCharacter character) {
        return new PlayerCharacterResponse(
                character.getId(),
                character.getName(),
                character.getRace().name(),
                character.getCharacterClass().name(),
                character.getLevel(),
                character.getCampaign().getId(),
                character.getCampaign().getName()
        );
    }
}
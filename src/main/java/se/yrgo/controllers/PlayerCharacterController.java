package se.yrgo.controllers;

import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.PlayerCharacter;
import se.yrgo.dto.character.CreatePlayerCharacterRequest;
import se.yrgo.dto.character.PlayerCharacterResponse;
import se.yrgo.dto.character.UpdatePlayerCharacterRequest;
import se.yrgo.exceptions.CampaignNotFoundException;
import se.yrgo.exceptions.CharacterNotFoundException;
import se.yrgo.exceptions.UserNotFoundException;
import se.yrgo.services.playerCharacter.PlayerCharacterService;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns/{campaignId}/characters")
public class PlayerCharacterController {

    private final PlayerCharacterService service;

    public PlayerCharacterController(PlayerCharacterService service) {
        this.service = service;
    }

    // CREATE

    @PostMapping
    public PlayerCharacterResponse createCharacter(
            @PathVariable Long campaignId,
            @RequestBody CreatePlayerCharacterRequest request)
            throws CampaignNotFoundException {

        PlayerCharacter character = service.createCharacter(campaignId, request);

        return toResponse(character);
    }

    // READ

    @GetMapping
    public List<PlayerCharacterResponse> getAllCharacters(
            @PathVariable Long campaignId) {

        return service.getCharactersForCampaign(campaignId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public PlayerCharacter getCharacterById(
            @PathVariable Long campaignId,
            @PathVariable Long id
    ) throws CharacterNotFoundException {
        return service.getCharacterById(id);
    }

    // UPDATE

    @PutMapping("/{id}")
    public PlayerCharacterResponse updatePlayerCharacter(
            @PathVariable Long campaignId,
            @PathVariable Long id,
            @RequestBody UpdatePlayerCharacterRequest request)
            throws UserNotFoundException {

        PlayerCharacter updatedPlayerCharacter = service.updateCharacter(
                id,
                request
        );

        return toResponse(updatedPlayerCharacter);


    }

    // DELETE


    @DeleteMapping("/{id}")
    public void deleteCharacter(
            @PathVariable Long campaignId,
            @PathVariable Long id) {
        service.deleteCharacter(id);
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

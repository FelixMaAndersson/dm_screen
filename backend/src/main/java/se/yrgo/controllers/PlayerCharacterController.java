package se.yrgo.controllers;

import org.springframework.web.bind.annotation.*;
import se.yrgo.dto.character.CreatePlayerCharacterRequest;
import se.yrgo.dto.character.PlayerCharacterResponse;
import se.yrgo.dto.character.UpdatePlayerCharacterRequest;
import se.yrgo.exceptions.campaign.CampaignNotFoundException;
import se.yrgo.exceptions.playerCharacter.CharacterNotFoundException;
import se.yrgo.exceptions.user.UserNotFoundException;
import se.yrgo.services.playerCharacter.PlayerCharacterService;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns/{campaignId}/characters")
@CrossOrigin(origins = "http://localhost:5173")
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

        return service.createCharacter(campaignId, request);
    }

    // READ

    @GetMapping
    public List<PlayerCharacterResponse> getAllCharacters(
            @PathVariable Long campaignId) {

        return service.getCharactersForCampaign(campaignId);
    }

    @GetMapping("/{id}")
    public PlayerCharacterResponse getCharacterById(
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

        return service.updateCharacter(id, request);
    }

    // DELETE


    @DeleteMapping("/{id}")
    public void deleteCharacter(
            @PathVariable Long campaignId,
            @PathVariable Long id) {

        service.deleteCharacter(id);
    }
}

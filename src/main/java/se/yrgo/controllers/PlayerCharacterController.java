package se.yrgo.controllers;

import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.PlayerCharacter;
import se.yrgo.dto.CreatePlayerCharacterRequest;
import se.yrgo.dto.PlayerCharacterResponse;
import se.yrgo.exceptions.CampaignNotFoundException;
import se.yrgo.exceptions.CharacterNotFoundException;
import se.yrgo.services.playerCharacter.PlayerCharacterService;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns/{campaignId}/characters")
public class PlayerCharacterController {

    private final PlayerCharacterService service;

    public PlayerCharacterController(PlayerCharacterService service) {
        this.service = service;
    }

    @GetMapping
    public List<PlayerCharacterResponse> getAllCharacters(
            @PathVariable Long campaignId) {

        return service.getCharactersForCampaign(campaignId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public PlayerCharacter getCharacterById(@PathVariable Long id) throws CharacterNotFoundException {
        return service.getCharacterById(id);
    }

    @PostMapping
    public PlayerCharacterResponse createCharacter(
            @PathVariable Long campaignId,
            @RequestBody CreatePlayerCharacterRequest request)
            throws CampaignNotFoundException {

        PlayerCharacter character = service.createCharacter(campaignId, request);

        return toResponse(character);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable Long id) {
        service.deleteCharacter(id);
    }

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

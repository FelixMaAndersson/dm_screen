package se.yrgo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.domain.Campaign;
import se.yrgo.domain.PlayerCharacter;
import se.yrgo.services.playerCharacter.PlayerCharacterService;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class PlayerCharacterController {

    private final PlayerCharacterService service;

    public PlayerCharacterController(PlayerCharacterService service) {
        this.service = service;
    }

    @GetMapping
    public List<PlayerCharacter> getAllCharacters() {
        return service.getAllCharacters();
    }

    @GetMapping
    public PlayerCharacterService createCharacter() {
return null;
    }



}

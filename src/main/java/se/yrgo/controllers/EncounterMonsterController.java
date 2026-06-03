package se.yrgo.controllers;

import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.EncounterMonster;
import se.yrgo.domain.Monster;
import se.yrgo.dto.EncounterMonster.CreateEncounterMonsterRequest;
import se.yrgo.dto.EncounterMonster.EncounterMonsterResponse;
import se.yrgo.dto.monster.MonsterResponse;
import se.yrgo.exceptions.EncounterMonsterNotFoundException;
import se.yrgo.exceptions.MonsterNotFoundException;
import se.yrgo.services.encounterMonster.EncounterMonsterService;

import java.util.List;

@RestController
@RequestMapping("/api/encounter-monsters")
public class EncounterMonsterController {

    private final EncounterMonsterService service;

    public EncounterMonsterController(EncounterMonsterService service) {
        this.service = service;
    }

    // CREATE

    @PostMapping
    public EncounterMonsterResponse createEncounterMonster(
            @RequestBody CreateEncounterMonsterRequest request
            ) {
        EncounterMonster encounterMonster = service.createEncounterMonster(request);

        return toResponse(encounterMonster);

    }

    // READ

    @GetMapping
    public List<EncounterMonsterResponse> getAllEncounterMonsters() {
        return service.getAllEncounterMonsters()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public EncounterMonsterResponse getEncounterMonsterById(@PathVariable Long id)
            throws EncounterMonsterNotFoundException {

        return toResponse(service.getEncounterMonsterById(id));
    }

    @GetMapping("/by-encounter/{encounterId}")
    public List<EncounterMonsterResponse> getEncounterMonstersByEncounterId(
            @PathVariable Long encounterId) {

        return service.getEncounterMonstersByEncounterId(encounterId);
    }


    // HELP METHODS

    private EncounterMonsterResponse toResponse(EncounterMonster em) {
        return new EncounterMonsterResponse(
                em.getId(),
                em.getMonster().getId(),
                em.getMonster().getName(),
                em.getEncounter().getId(),
                em.getCurrentHp(),
                em.isAlive(),
                em.isBoss(),
                em.getNotes()
        );
    }
}
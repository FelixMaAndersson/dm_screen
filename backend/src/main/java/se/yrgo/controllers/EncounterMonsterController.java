package se.yrgo.controllers;

import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.EncounterMonster;
import se.yrgo.dto.encounterMonster.*;
import se.yrgo.exceptions.encounterMonster.EncounterMonsterNotFoundException;
import se.yrgo.services.encounterMonster.EncounterMonsterService;

import java.util.List;

@RestController
@RequestMapping("/api/encounter-monsters")
@CrossOrigin(origins = "http://localhost:5173")
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

        return service.createEncounterMonster(request);

    }

    // READ

    @GetMapping
    public List<EncounterMonsterResponse> getAllEncounterMonsters() {
        return service.getAllEncounterMonsters();
    }

    @GetMapping("/{id}")
    public EncounterMonsterResponse getEncounterMonsterById(@PathVariable Long id)
            throws EncounterMonsterNotFoundException {

        return service.getEncounterMonsterById(id);
    }

    @GetMapping("/by-encounter/{encounterId}")
    public List<EncounterMonsterResponse> getEncounterMonstersByEncounterId(
            @PathVariable Long encounterId) {

        return service.getEncounterMonstersByEncounterId(encounterId);
    }

    // UPDATE

    @PutMapping("/{id}")
    public EncounterMonsterResponse updateEncounterMonster(
            @PathVariable Long id,
            @RequestBody UpdateEncounterMonsterRequest request) {

        return service.updateEncounterMonster(id, request);
    }

    //kolla upp @RequestParam istället för PathVariable
    @PatchMapping("/{id}/hp")
    public EncounterMonsterResponse updateHp(
            @PathVariable Long id,
            @RequestBody UpdateHpRequest request) {

        return service.updateHp(id, request);
    }

    @PatchMapping("/{id}/alive")
    public EncounterMonsterResponse updateAlive(
            @PathVariable Long id,
            @RequestBody UpdateAliveRequest request) {

        return service.updateAlive(id, request);
    }

    @PatchMapping("/{id}/notes")
    public EncounterMonsterResponse updateNotes(
            @PathVariable Long id,
            @RequestBody UpdateNotesRequest request) {

        return service.updateNotes(id, request);
    }

    // DELETE

    @DeleteMapping("/{id}")
    public void deleteEncounterMonster(@PathVariable Long id) {
        service.deleteEncounterMonster(id);
    }


}
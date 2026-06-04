package se.yrgo.controllers;

import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.EncounterMonster;
import se.yrgo.dto.encounterMonster.*;
import se.yrgo.exceptions.EncounterMonsterNotFoundException;
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

    // UPDATE

    @PutMapping("/{id}")
    public EncounterMonsterResponse updateEncounterMonster(
            @PathVariable Long id,
            @RequestBody UpdateEncounterMonsterRequest request) {

        return service.updateEncounterMonster(id, request);
    }

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
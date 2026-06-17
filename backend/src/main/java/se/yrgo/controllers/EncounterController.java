package se.yrgo.controllers;

import org.springframework.web.bind.annotation.*;
import se.yrgo.dto.encounter.*;
import se.yrgo.exceptions.encounter.EncounterNotFoundException;
import se.yrgo.services.encounter.EncounterDifficultyCalculator;
import se.yrgo.services.encounter.EncounterService;

import java.util.List;


@RestController
@RequestMapping("/api/encounter")
@CrossOrigin(origins = "http://localhost:5173")
public class EncounterController {

    public final EncounterService service;
    public final EncounterDifficultyCalculator difficultyCalculator;

    public EncounterController(EncounterService service, EncounterDifficultyCalculator difficultyCalculator) {
        this.service = service;
        this.difficultyCalculator = difficultyCalculator;
    }

    // CREATE

    @PostMapping
    public EncounterResponse createEncounter(
            @RequestBody CreateEncounterRequest request
            ) {

        return service.createEncounter(request);
    }

    // READ

    @GetMapping
    public List<EncounterResponse> getAllEncounters() {
        return service.getAllEncounters();
    }

    @GetMapping("/{id}")
    public EncounterResponse getEncounterById(@PathVariable Long id) throws EncounterNotFoundException {
        return service.getEncounterById(id);
    }

    @GetMapping("/by-campaign/{campaignId}")
    public List<EncounterResponse> getEncountersByCampaignId(@PathVariable Long campaignId) {
        return service.getAllEncountersInCampaign(campaignId);
    }

    // UPDATE

    @PostMapping("/{id}/characters")
    public EncounterResponse addCharacterToEncounter(
            @PathVariable Long id,
            @RequestBody AddPlayerCharacterToEncounterRequest request) {

        return service.addCharacterToEncounter(id, request);
    }

    @PostMapping("/{id}/build")
    public EncounterResponse buildEncounterWithDifficulty(
            @PathVariable Long id,
            @RequestBody BuildEncounterRequest request
    ) {

        return service.buildEncounterWithDifficulty(id, request);
    }

    @PostMapping("{id}/encounterMonster")
    public EncounterResponse addEncounterMonsterToEncounter(
            @PathVariable Long id,
            @RequestBody AddMonsterToEncounterRequest request
            ) {

        return service.addMonsterToEncounter(id, request);
    }

    // DELETE

    @DeleteMapping("/{id}/monsters/{encounterMonsterId}")
    public EncounterResponse removeMonsterFromEncounter(
            @PathVariable Long id,
            @PathVariable Long encounterMonsterId
    ) {
        return service.removeMonsterFromEncounter(id, encounterMonsterId);
    }

    @DeleteMapping("/{id}/characters/{playerCharacterId}")
    public EncounterResponse removeCharacterFromEncounter(
            @PathVariable Long id,
            @PathVariable Long playerCharacterId
    ) {
        return service.removeCharacterFromEncounter(id, playerCharacterId);
    }

    @DeleteMapping("/{id}")
    public void deleteEncounter(@PathVariable Long id) {
        service.deleteEncounter(id);
    }

}

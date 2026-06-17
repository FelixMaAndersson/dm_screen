package se.yrgo.controllers;

import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.Encounter;
import se.yrgo.domain.enums.Difficulty;
import se.yrgo.dto.encounter.CreateEncounterRequest;
import se.yrgo.dto.encounter.EncounterResponse;
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


}

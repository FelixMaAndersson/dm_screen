package se.yrgo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    public EncounterResponse createEncounter(
            @RequestBody CreateEncounterRequest request
            ) {
        Encounter e = service.createEncounter(request);

        return toResponse(e);
    }

    // HELP METHODS

    private EncounterResponse toResponse(Encounter e) {

        Difficulty difficulty = difficultyCalculator.calculate(e);



        return new EncounterResponse(
                e.getId(),
                e.getHabitat(),
                e.getVisionDistance(),
                e.getCampaign().getId(),
                e.getCampaign().getName(),
                service.toSumarizedMonsters(e),
                difficulty,
                e.getLore()
        );
    }
}

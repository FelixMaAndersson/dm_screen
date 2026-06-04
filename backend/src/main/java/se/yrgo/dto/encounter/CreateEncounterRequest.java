package se.yrgo.dto.encounter;

import se.yrgo.domain.enums.Difficulty;
import se.yrgo.domain.enums.Habitat;

public record CreateEncounterRequest(
        Habitat habitat,
        int visionDistance,
        Long campaignId
) {
}
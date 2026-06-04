package se.yrgo.dto.encounter;

import se.yrgo.domain.enums.Difficulty;
import se.yrgo.domain.enums.Habitat;
import se.yrgo.dto.encounterMonster.EncounterMonsterSummary;

import java.util.List;

public record EncounterResponse(
        Long id,
        Habitat habitat,
        int visionDistance,
        Long campaignId,
        String campaignName,
        List<EncounterMonsterSummary> monsters,
        Difficulty encounterDifficulty,
        String lore
) {
}
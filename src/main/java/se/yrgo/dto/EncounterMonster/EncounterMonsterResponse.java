package se.yrgo.dto.EncounterMonster;

import se.yrgo.domain.Encounter;
import se.yrgo.domain.Monster;

public record EncounterMonsterResponse(
        Long id,
        Monster monster,
        Encounter encounter,
        int currentHp,
        boolean alive,
        boolean boss,
        String notes
) {
}
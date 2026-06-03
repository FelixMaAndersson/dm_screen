package se.yrgo.dto.EncounterMonster;

import se.yrgo.domain.Encounter;
import se.yrgo.domain.Monster;

public record EncounterMonsterResponse(
        Long id,
        Long monsterId,
        String monsterName,
        Long encounterId,
        int currentHp,
        boolean alive,
        boolean boss,
        String notes
) {
}
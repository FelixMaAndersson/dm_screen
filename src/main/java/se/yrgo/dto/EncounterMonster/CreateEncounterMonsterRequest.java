package se.yrgo.dto.EncounterMonster;

import se.yrgo.domain.Encounter;
import se.yrgo.domain.Monster;

public record CreateEncounterMonsterRequest(
        Monster monster,
        Encounter encounter,
        String name,
        int currentHp,
        boolean alive,
        boolean villain,
        boolean bbeg,
        String lore
        ) {
}
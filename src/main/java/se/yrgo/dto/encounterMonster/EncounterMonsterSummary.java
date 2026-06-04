package se.yrgo.dto.encounterMonster;

public record EncounterMonsterSummary(
        Long encounterMonsterId,
        Long monsterId,
        String monsterName
) {}

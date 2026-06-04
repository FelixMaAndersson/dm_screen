package se.yrgo.dto.encounterMonster;

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
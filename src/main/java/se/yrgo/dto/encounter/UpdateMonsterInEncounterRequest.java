package se.yrgo.dto.encounter;

public record UpdateMonsterInEncounterRequest(
        Long encounterMonsterId,
        Long monsterId,
        String monsterName
) {
}

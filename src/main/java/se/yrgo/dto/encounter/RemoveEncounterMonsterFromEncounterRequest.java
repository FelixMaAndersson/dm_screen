package se.yrgo.dto.encounter;

public record RemoveEncounterMonsterFromEncounterRequest(Long encounterMonsterId,
                                                         Long monsterId,
                                                         String monsterName,
                                                         String notes
) {
}

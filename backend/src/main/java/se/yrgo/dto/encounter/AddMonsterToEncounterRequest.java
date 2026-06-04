package se.yrgo.dto.encounter;

public record AddMonsterToEncounterRequest(
        Long monsterId,
        boolean boss,
        String notes
) {
}

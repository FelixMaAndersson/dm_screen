package se.yrgo.dto.encounter;

public record AddPlayerCharacterToEncounterRequest(
        Long playerCharacterId,
        String playerCharacterName
) {
}

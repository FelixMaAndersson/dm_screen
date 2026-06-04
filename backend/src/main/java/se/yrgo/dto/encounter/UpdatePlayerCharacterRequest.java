package se.yrgo.dto.encounter;

public record UpdatePlayerCharacterRequest(
        Long playerCharacterId,
        String playerCharacterName
) {
}

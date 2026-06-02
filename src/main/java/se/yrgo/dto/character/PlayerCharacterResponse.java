package se.yrgo.dto.character;

public record PlayerCharacterResponse(
        Long id,
        String name,
        String race,
        String characterClass,
        int level,
        Long campaignId,
        String campaignName
) {
}
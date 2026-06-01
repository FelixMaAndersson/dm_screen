package se.yrgo.dto;

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
package se.yrgo.dto.campaign;

public record CampaignResponse(
        Long id,
        String name,
        String dmName,
        String description
) {
}
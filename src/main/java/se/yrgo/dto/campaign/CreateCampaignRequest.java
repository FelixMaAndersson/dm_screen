package se.yrgo.dto.campaign;

public record CreateCampaignRequest(
        String name,
        Long dmId
) {
}
package se.yrgo.dto.campaign;

public record UpdateCampaignRequest (
        String name,
        String description,
        Long dmId
){
}

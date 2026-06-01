package se.yrgo.dto;

public record UpdateCampaignRequest (
        String name,
        String description,
        Long dmId
){
}

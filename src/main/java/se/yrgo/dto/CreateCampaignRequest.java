package se.yrgo.dto;

import se.yrgo.domain.User;

public record CreateCampaignRequest(
        String name,
        Long dmId
) {
}
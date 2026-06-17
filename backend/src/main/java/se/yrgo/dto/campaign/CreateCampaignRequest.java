package se.yrgo.dto.campaign;

import se.yrgo.domain.campaign.CampaignDate;

public record CreateCampaignRequest(
        String name,
        Long dmId,
        CampaignDate currentDate
) {
}
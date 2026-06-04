import { useEffect, useState } from "react";
import { getCampaigns } from "../api/campaignsApi";
import type { Campaign } from "../App";

type CampaignListProps = {
    onSelectCampaign: (campaign: Campaign) => void;
};

function CampaignList({ onSelectCampaign }: CampaignListProps) {
    const [campaigns, setCampaigns] = useState<Campaign[]>([]);

    useEffect(() => {
        async function loadCampaigns() {
            const data = await getCampaigns();
            setCampaigns(data);
        }

        loadCampaigns();
    }, []);

    return (
        <div>
            <h2>Choose a campaign to play</h2>

            {campaigns.map(campaign => (
                <button
                    key={campaign.id}
                    onClick={() => onSelectCampaign(campaign)}
                >
                    {campaign.name}
                </button>
            ))}
        </div>
    );
}

export default CampaignList;
import { useEffect, useState } from "react";
import { getCampaigns } from "../api/campaignsApi";

type Campaign = {
    id: number;
    name: string;
};

function CampaignList() {
    const [campaigns, setCampaigns] = useState<Campaign[]>([]);

    useEffect(() => {
        async function loadCampaigns() {
            const data = await getCampaigns();
            setCampaigns(data);
        }

        loadCampaigns();
    }, []);

    function selectCampaign(campaign: Campaign) {
        console.log("Selected campaign:", campaign);
    }

    return (
        <div>
            <h2>Campaigns</h2>

            {campaigns.map(campaign => (
                <button
                    key={campaign.id}
                    onClick={() => selectCampaign(campaign)}
                >
                    {campaign.name}
                </button>
            ))}
        </div>
    );
}

export default CampaignList;
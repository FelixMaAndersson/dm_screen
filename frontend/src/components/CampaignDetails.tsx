import type { Campaign } from "../App";

type CampaignDetailsProps = {
    campaign: Campaign;
};

function CampaignDetails({ campaign }: CampaignDetailsProps) {
    return (
        <div>
            <h2>{campaign.name}</h2>

            <p>Campaign id: {campaign.id}</p>
        </div>
    );
}

export default CampaignDetails;
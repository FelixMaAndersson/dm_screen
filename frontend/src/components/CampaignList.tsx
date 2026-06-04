function CampaignList() {
    const campaigns = [
        { id: 1, name: "Curse of Strahd" },
        { id: 2, name: "Eberron" },
        { id: 3, name: "Lost Mine of Phandelver" }
    ];

    return (
        <div>
            <h2>Campaigns</h2>

            {campaigns.map(campaign => (
                <p key={campaign.id}>
                    {campaign.name}
                </p>
            ))}
        </div>
    );
}

export default CampaignList;
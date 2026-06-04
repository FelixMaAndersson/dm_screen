export type CreateCampaignRequest = {
    name: string;
    dmId: number;
};

export async function createCampaign(request: CreateCampaignRequest) {
    const response = await fetch("http://localhost:8080/api/campaigns", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(request),
    });

    if (!response.ok) {
        throw new Error("Could not create campaign");
    }

    return response.json();
}
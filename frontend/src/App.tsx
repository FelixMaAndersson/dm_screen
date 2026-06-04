import {useState} from "react";
import CampaignList from "./components/CampaignList";
import CampaignDetails from "./components/CampaignDetails";
import CreateCampaignForm from "./components/CreateCampaignForm.tsx";
import CreateUserForm from "./components/CreateUserForm.tsx";
import CreateMonsterForm from "./components/CreateMonsterForm.tsx";

export type Campaign = {
    id: number;
    name: string;
};

function App() {
    const [selectedCampaign, setSelectedCampaign] =
        useState<Campaign | null>(null);

    if (selectedCampaign) {
        return (
            <CampaignDetails
                campaign={selectedCampaign}
            />
        );
    }

    return (
        <div>
            <h1>D&D Screen</h1>

            <CreateUserForm />
            <CreateMonsterForm />
            <CreateCampaignForm />
            <CampaignList
                onSelectCampaign={setSelectedCampaign}
            />

        </div>
    );
}

export default App;
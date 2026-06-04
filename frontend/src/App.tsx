import CampaignList from "./components/CampaignList"
import CreateUserForm from "./components/CreateUserForm";
import CreateCampaignForm from "./components/CreateCampaignForm.tsx";

function App() {
    return (
        <div>
            <h1>D&D Screen</h1>
            <CreateUserForm />
            <CreateCampaignForm />
            <CampaignList />
        </div>
    )
}

export default App
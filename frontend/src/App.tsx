import CampaignList from "./components/CampaignList"
import CreateUserForm from "./components/CreateUserForm";

function App() {
    return (
        <div>
            <h1>D&D Screen</h1>
            <CreateUserForm />
            <CampaignList />
        </div>
    )
}

export default App
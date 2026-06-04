import { useState } from "react";
import { createCampaign } from "../api/campaignsApi";
import * as React from "react";

function CreateCampaignForm() {
    const [name, setName] = useState("");
    const [dmId, setDmId] = useState("");

    async function handleSubmit(event: React.SubmitEvent) {
        event.preventDefault();

        await createCampaign({
            name,
            dmId: Number(dmId),
        });

        setName("");
        setDmId("");
    }

    return (
        <form onSubmit={handleSubmit}>
            <h2>Create campaign</h2>

            <div>
                <label>Campaign name</label>
                <input
                    value={name}
                    onChange={event => setName(event.target.value)}
                />
            </div>

            <div>
                <label>DM id</label>
                <input
                    type="number"
                    value={dmId}
                    onChange={event => setDmId(event.target.value)}
                />
            </div>

            <button type="submit">Create campaign</button>
        </form>
    );
}

export default CreateCampaignForm;
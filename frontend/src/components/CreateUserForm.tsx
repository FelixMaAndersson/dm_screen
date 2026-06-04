import { useState } from "react";
import { createUser } from "../api/usersApi";
import * as React from "react";

function CreateUserForm() {
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");

    async function handleSubmit(event: React.SubmitEvent) {
        event.preventDefault();

        await createUser({
            name: userName,
            password,
        });

        setUserName("");
        setPassword("");
    }

    return (
        <form onSubmit={handleSubmit}>
            <h2>Create user</h2>

            <div>
                <label>User name</label>
                <input
                    value={userName}
                    onChange={event => setUserName(event.target.value)}
                />
            </div>

            <div>
                <label>Password</label>
                <input
                    type="password"
                    value={password}
                    onChange={event => setPassword(event.target.value)}
                />
            </div>

            <button type="submit">Create user</button>
        </form>
    );
}

export default CreateUserForm;
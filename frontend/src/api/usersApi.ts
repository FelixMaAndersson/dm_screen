export type CreateUserRequest = {
    name: string;
    password: string;
};

export async function createUser(request: CreateUserRequest) {
    const response = await fetch("http://localhost:8080/api/users", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(request),
    });

    if (!response.ok) {
        throw new Error("Could not create user");
    }

    return response.json();
}
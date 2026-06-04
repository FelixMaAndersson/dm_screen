import type {
    MonsterType,
    CreatureSize,
    Alignment,
    Habitat, ChallengeRating
} from "../types/enums";

export type CreateMonsterRequest = {
    name: string;
    cr: ChallengeRating;
    xp: number;
    type: MonsterType;
    size: CreatureSize;
    alignment: Alignment;
    habitat: Habitat;
    hp: number;
    tag: string;
};

export async function createMonster(request: CreateMonsterRequest) {
    const response = await fetch("http://localhost:8080/api/monsters", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(request),
    });

    if (!response.ok) {
        throw new Error("Could not create monster");
    }

    return response.json();
}


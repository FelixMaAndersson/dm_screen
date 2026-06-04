package se.yrgo.dto.monster;

import se.yrgo.domain.enums.*;

public record CreateMonsterRequest(
        String name,
        ChallengeRating cr,
        int xp,
        MonsterType type,
        CreatureSize size,
        Habitat habitat,
        Alignment alignment,
        int hp,
        String tags
) {
}

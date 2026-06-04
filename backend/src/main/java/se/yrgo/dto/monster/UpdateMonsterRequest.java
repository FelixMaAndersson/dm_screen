package se.yrgo.dto.monster;

import se.yrgo.domain.enums.*;

public record UpdateMonsterRequest (
        String name,
        ChallengeRating cr,
        int xp,
        MonsterType type,
        CreatureSize size,
        Alignment alignment,
        Habitat habitat,
        int hp,
        String tags
) {
}

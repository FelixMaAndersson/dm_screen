package se.yrgo.dto.monster;

import se.yrgo.domain.enums.*;

public record CreateMonsterRequest(
        String name,
        ChallengeRating cr,
        MonsterType type,
        CreatureSize size,
        Habitat habitat,
        Alignment alignment,
        String tags
) {
}

package se.yrgo.dto.monster;

import se.yrgo.domain.enums.*;

public record MonsterResponse(
        Long id,
        String name,
        ChallengeRating cr,
        MonsterType type,
        CreatureSize size,
        Alignment alignment,
        Habitat habitat,
        String tags
) {
}

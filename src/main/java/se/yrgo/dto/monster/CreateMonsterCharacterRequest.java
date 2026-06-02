package se.yrgo.dto.monster;

import se.yrgo.domain.enums.ChallengeRating;
import se.yrgo.domain.enums.CreatureSize;
import se.yrgo.domain.enums.Habitat;
import se.yrgo.domain.enums.MonsterType;

public record CreateMonsterCharacterRequest(
        String name,
        ChallengeRating cr,
        MonsterType type,
        CreatureSize size,
        Habitat habitat,
        String tags
) {
}

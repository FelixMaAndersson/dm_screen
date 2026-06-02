package se.yrgo.dto.character;

import se.yrgo.domain.enums.CharacterClass;
import se.yrgo.domain.enums.CharacterRace;

public record UpdatePlayerCharacterRequest (
        String name,
        CharacterRace race,
        CharacterClass characterClass,
        int level
){
}

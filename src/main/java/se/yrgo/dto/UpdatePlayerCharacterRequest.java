package se.yrgo.dto;

import se.yrgo.domain.CharacterClass;
import se.yrgo.domain.CharacterRace;

public record UpdatePlayerCharacterRequest (
        String name,
        CharacterRace race,
        CharacterClass characterClass,
        int level
){
}

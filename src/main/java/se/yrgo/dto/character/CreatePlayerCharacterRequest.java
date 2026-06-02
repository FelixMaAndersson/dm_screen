package se.yrgo.dto.character;

import se.yrgo.domain.CharacterClass;
import se.yrgo.domain.CharacterRace;

public record CreatePlayerCharacterRequest (String name, CharacterRace race, CharacterClass characterClass, int level){
}

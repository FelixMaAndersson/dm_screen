package se.yrgo.dataaccess;

import se.yrgo.domain.PlayerCharacter;
import se.yrgo.exceptions.CharacterNotFoundException;

import java.util.List;

public interface PlayerCharacterDao {

    public void createCharacter (PlayerCharacter playerCharacter);
    public void updateCharacter (PlayerCharacter playerCharacter) throws CharacterNotFoundException;
    public void deleteCharacter (PlayerCharacter playerCharacter) throws CharacterNotFoundException;
    public PlayerCharacter getCharacterById (int id) throws CharacterNotFoundException;
    public PlayerCharacter getCharacterByName (String name) throws CharacterNotFoundException;
    public List<PlayerCharacter> getAllCharacters ();

}


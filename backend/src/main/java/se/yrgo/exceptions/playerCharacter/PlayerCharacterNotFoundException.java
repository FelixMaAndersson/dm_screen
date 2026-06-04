package se.yrgo.exceptions.playerCharacter;

public class PlayerCharacterNotFoundException extends RuntimeException {
    public PlayerCharacterNotFoundException(Long id) {
        super("Character with id " + id + " was not found");
    }
}

package se.yrgo.exceptions;

public class PlayerCharacterNotFoundException extends RuntimeException {
    public PlayerCharacterNotFoundException(Long id) {
        super("Character with id " + id + " was not found");
    }
}

package se.yrgo.exceptions;

/**
 * Custom exception thrown when a team is not found in the database.
 * Provides constructors for both team ID and team name.
 */
public class CharacterNotFoundException extends RuntimeException {

    public CharacterNotFoundException(long id) {
        super("Character not found with id: " + id);
    }

    public CharacterNotFoundException(String name) {
        super("Character not found with name: " + name);
    }
}
package se.yrgo.exceptions;

/**
 * Exception thrown when a player with the given id
 * could not be found in the database.
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException(Long id) {
        super("User with id " + id + " was not found");
    }

    public UserNotFoundException(String name) {
        super("User with name " + name + " was not found");
    }
}

package se.yrgo.exceptions;

/**
 * Exception thrown when a player with the given id
 * could not be found in the database.
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException(int userId) {
        super("User with id " + userId + " was not found");
    }

}

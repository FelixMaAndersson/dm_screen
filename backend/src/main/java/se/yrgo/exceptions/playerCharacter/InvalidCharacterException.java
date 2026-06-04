package se.yrgo.exceptions.playerCharacter;

/**
 * Exception thrown when invalid character data is provided,
 * for example invalid stats.
 */
public class InvalidCharacterException extends RuntimeException {

    public InvalidCharacterException(String message) {
        super(message);
    }

}
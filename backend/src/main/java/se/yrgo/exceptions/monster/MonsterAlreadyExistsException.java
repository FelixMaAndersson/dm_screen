package se.yrgo.exceptions.monster;

public class MonsterAlreadyExistsException extends RuntimeException {
    public MonsterAlreadyExistsException(String message) {
        super(message);
    }
}

package se.yrgo.exceptions;

public class MonsterNotFoundException extends RuntimeException {

    public MonsterNotFoundException(Long id) {
        super("Monster with id " + id + " was not found");
    }

    public MonsterNotFoundException(String name) {
        super("User with name " + name + " was not found");
    }

}

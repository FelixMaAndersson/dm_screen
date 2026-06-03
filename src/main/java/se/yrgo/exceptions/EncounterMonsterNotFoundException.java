package se.yrgo.exceptions;

public class EncounterMonsterNotFoundException extends RuntimeException {

    public EncounterMonsterNotFoundException(long id) {
        super("Encounter monster not found with id: " + id);
    }

    public EncounterMonsterNotFoundException(String name) {
        super("Monster not found with name: " + name);
    }
}
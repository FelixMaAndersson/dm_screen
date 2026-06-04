package se.yrgo.exceptions.encounterMonster;

public class EncounterMonsterNotFoundException extends RuntimeException {

    public EncounterMonsterNotFoundException(long id) {
        super("Encounter monster not found with id: " + id);
    }
}
package se.yrgo.exceptions.encounter;

public class EncounterNotFoundException extends RuntimeException {

    public EncounterNotFoundException(long id) {
        super("Encounter not found with id: " + id);
    }
}

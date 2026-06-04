package se.yrgo.exceptions;

public class EncounterNotFoundException extends RuntimeException {

    public EncounterNotFoundException(long id) {
        super("Encounter not found with id: " + id);
    }
}

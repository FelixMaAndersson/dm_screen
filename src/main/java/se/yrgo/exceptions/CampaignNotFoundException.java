package se.yrgo.exceptions;

/**
 * Thrown when a requested league cannot be found in the database.
 * This is a checked exception that must be handled by the caller.
 */
public class CampaignNotFoundException extends Exception {


    public CampaignNotFoundException(String name) {
        super("Campaign: " + name + " not found.");
    }


    public CampaignNotFoundException(Long id) {
        super("Campaign with id: " + id + " not found.");
    }
}

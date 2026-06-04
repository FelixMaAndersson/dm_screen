package se.yrgo.domain.campaign;

import jakarta.persistence.Embeddable;

@Embeddable
public class CampaignDate {
    private int day; // 1-30
    private int month; // 1-12
    private int year;

}

package se.yrgo.domain.campaign;

import jakarta.persistence.Embeddable;

@Embeddable
public class CampaignDate {
    private int day; // 1-30
    private int month; // 1-12
    private int year;

    public CampaignDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public CampaignDate() {

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void advanceOneDay() {
        day++;

        if (day > 30) {
            day = 1;
            month++;
        }

        if (month > 12) {
            month = 1;
            year++;
        }
    }

    public int getTenday() {
        return ((day - 1) / 10) + 1;
    }

    public String getMonthName() {
        return Month.fromNumber(month).name();
    }

}

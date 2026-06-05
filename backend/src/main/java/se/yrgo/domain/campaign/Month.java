package se.yrgo.domain.campaign;

public enum Month {

    HAMMER("Hammer", 1),
    ALTURIAK("Alturiak", 2),
    CHES("Ches",3),
    TARSAKH("Tarsakh",4),
    MIRTUL("Mirtul",5),
    KYTHORN("Kythorn",6),
    FLAMERULE("Flamerule",7),
    ELEASIS("Eleasis",8),
    ELEINT("Eleint",9),
    MARPENOTH("Marpenoth",10),
    UKTAR("Uktar",11),
    NIGHTAL("Nightal",12);

    private final int number;
    private final String displayName;

    Month(String displayName, int number) {
        this.number = number;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


    public static Month fromNumber(int number) {
        for (Month month : values()) {
            if (month.number == number) {
                return month;
            }
        }
        throw new IllegalArgumentException("Invalid month: " + number);
    }
}

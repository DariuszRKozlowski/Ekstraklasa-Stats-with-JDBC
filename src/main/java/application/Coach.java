package application;

import java.util.Date;

public class Coach {
    private final int coachID;
    private Club club;
    private final String fName;
    private final String lName;
    private String nationality;
    private final Date birthDate;

    public Coach(int coachID, Club club, String fName, String lName, String nationality, Date birthDate) {
        this.coachID = coachID;
        this.club = club;
        this.fName = fName;
        this.lName = lName;
        this.nationality = nationality;
        this.birthDate = birthDate;
    }
}

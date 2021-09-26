package application;

import dao.CoachDAOImplementation;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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

    public int getCoachID() {
        return coachID;
    }

    public Club getClub() {
        return club;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getNationality() {
        return nationality;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public static void displayGeneralInfo(String input) throws SQLException, IOException {

    }

    public static void displayResults(String input) {

    }

    public static void displayAllCoaches() throws SQLException, IOException {
        CoachDAOImplementation coachDAO = new CoachDAOImplementation();
        List<Coach> listOfCoaches = coachDAO.getAll();
        StringBuilder message = new StringBuilder();
        for (Coach coach: listOfCoaches) {
            message.append("ID: ").append(coach.getCoachID()).append(" | Club: ").append(coach.getClub())
                    .append(" | Name: ").append(coach.getfName()).append(" ").append(coach.getlName())
                    .append(" | Nationality: ").append(coach.getNationality()).append(" | Birthdate: ")
                    .append(coach.getBirthDate().toString()).append(",\n");
        }
        JOptionPane.showMessageDialog(null, message);
    }
}

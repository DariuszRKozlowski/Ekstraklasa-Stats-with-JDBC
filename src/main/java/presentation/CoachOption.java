package presentation;

import application.Club;
import application.Coach;
import dao.ClubDAOImplementation;
import dao.CoachDAOImplementation;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoachOption implements OptionInterface {

    private final CoachDAOImplementation coachDAO = new CoachDAOImplementation();
    private final ClubDAOImplementation clubDAO = new ClubDAOImplementation();

    @Override
    public void displayAll() throws SQLException, IOException {
        List<Coach> listOfCoaches = coachDAO.getAll();
        if(listOfCoaches.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Can not find coaches in database!");
        }
        else {
            StringBuilder message = new StringBuilder();
            for (Coach coach: listOfCoaches) {
                String club;
                club = coach.getClub() == null ? "Unemployed": coach.getClub().getName();
                message.append("ID: ").append(coach.getCoachID()).append(" | Name: ").append(coach.getfName()).append(" ")
                        .append(coach.getlName()).append(" | Nationality: ").append(coach.getNationality())
                        .append(" | Birthdate: ").append(coach.getBirthDate()).append(" | Club: ").append(club)
                        .append("\n");
            }
            JOptionPane.showMessageDialog(null, message);
        }
    }

    @Override
    public void displaySpecificOne(String input) throws SQLException, IOException {
        Club clubFilter = clubDAO.filterById(input);
        List<Coach> coachByClub = new ArrayList<>();
        if (clubFilter != null) {
            coachByClub = coachDAO.filterByClub(clubFilter);
        }
        List<Coach> coachByName = coachDAO.filterByLastName(input);
        StringBuilder message = new StringBuilder();
        if(coachByName.isEmpty()) {
            if(coachByClub.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Can not find \"" + input + "\" in database!");
            }
            else {
                for (Coach coach: coachByClub) {
                    String club = coach.getClub().getName() == null ? "Unemployed" : coach.getClub().getName();
                    message.append("ID: ").append(coach.getCoachID()).append(" | Name: ").append(coach.getfName()).append(" ")
                            .append(coach.getlName()).append(" | Nationality: ").append(coach.getNationality())
                            .append(" | Birthdate: ").append(coach.getBirthDate()).append(" | Club: ").append(club)
                            .append("\n");
                }
                JOptionPane.showMessageDialog(null, message);
            }
        }
        else {
            for (Coach coach: coachByName) {
                String club = coach.getClub().getName() == null ? "Unemployed" : coach.getClub().getName();
                message.append("ID: ").append(coach.getCoachID()).append(" | Name: ").append(coach.getfName()).append(" ")
                        .append(coach.getlName()).append(" | Nationality: ").append(coach.getNationality())
                        .append(" | Birthdate: ").append(coach.getBirthDate()).append(" | Club: ").append(club)
                        .append("\n");
            }
            JOptionPane.showMessageDialog(null, message);
        }
    }

    public void displaySpecificOne(int input) throws SQLException, IOException {
        Coach coachById = coachDAO.filterById(input);
        StringBuilder message = new StringBuilder();
        if(coachById == null) {
            JOptionPane.showMessageDialog(null,"Can not find \"" + input + "\" in database!");
        }
        else {
            String club = coachById.getClub() == null ? "Unemployed" : coachById.getClub().getName();
            message.append("ID: ").append(coachById.getCoachID()).append(" | Name: ").append(coachById.getfName()).append(" ")
                    .append(coachById.getlName()).append(" | Nationality: ").append(coachById.getNationality())
                    .append(" | Birthdate: ").append(coachById.getBirthDate()).append(" | Club: ").append(club)
                    .append("\n");
            JOptionPane.showMessageDialog(null, message);
        }
    }
}

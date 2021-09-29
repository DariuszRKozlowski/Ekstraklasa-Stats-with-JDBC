package presentation;

import application.Club;
import application.Coach;
import application.Match;
import application.Player;
import dao.ClubDAOImplementation;
import dao.CoachDAOImplementation;
import dao.MatchDAOImplementation;
import dao.PlayerDAOImplementation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClubOption implements OptionInterface {

    private final ClubDAOImplementation clubDAO = new ClubDAOImplementation();
    private final PlayerDAOImplementation playerDAO = new PlayerDAOImplementation();
    private final MatchDAOImplementation matchDAO = new MatchDAOImplementation();
    private final CoachDAOImplementation coachDAO = new CoachDAOImplementation();


    @Override
    public void displayAll() throws SQLException, IOException {
        List<Club> listOfClubs = clubDAO.getAll();
        if(listOfClubs.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Can not find clubs in database!");
        }
        else {
            StringBuilder message = new StringBuilder();
            for (Club club : listOfClubs) {
                message.append("ID: ").append(club.getClubId()).append(" | Name: ").append(club.getName())
                        .append(" | Locality: ").append(club.getLocality()).append(" (").append(club.getVoivodeship())
                        .append(") | Stadium: ").append(club.getStadiumName()).append(" (").append(club.getStadiumCapacity())
                        .append(" seats).\n");
            }
            displayOnScreen(message, "All clubs");
        }
    }

    @Override
    public void displaySpecificOne(String input) throws SQLException, IOException {
        Club clubById = clubDAO.filterById(input);
        List<Club> clubByName = clubDAO.filterByName(input);
        if(clubById == null) {
            if(clubByName.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Can not find \"" + input + "\" in database!");
            }
            else {
                for (Club club: clubByName) {
                    StringBuilder message = new StringBuilder();
                    message.append("Result for \"").append(input).append("\":\nClub name: ").append(club.getName()).append("\nID: ").append(club.getClubId()).append("\nLocality: ").append(club.getLocality()).append("\nVoivodeship: ").append(club.getVoivodeship()).append("\nStadium: ").append(club.getStadiumName()).append(" (").append(club.getStadiumCapacity()).append(" seats)");
                    displayOnScreen(message, "Result for " + input);
                }
            }
        }
        else {
            StringBuilder message = new StringBuilder();
            message.append("Result for \"").append(input).append("\":\nClub name: ").append(clubById.getName()).append("\nID: ").append(clubById.getClubId()).append("\nLocality: ").append(clubById.getLocality()).append("\nVoivodeship: ").append(clubById.getVoivodeship()).append("\nStadium: ").append(clubById.getStadiumName()).append(" (").append(clubById.getStadiumCapacity()).append(" seats)");
            displayOnScreen(message, "Result for " + input);

        }
    }

    public void displayClubPlayers(String input) throws SQLException, IOException {
        Club clubById = clubDAO.filterById(input);
        List<Club> clubByName = clubDAO.filterByName(input);
        List<Player> playersByClub = null;
        if(clubById == null) {
            if(clubByName.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Can not find \"" + input + "\" in database!");
            }
            else {
                for (Club club: clubByName) {
                    playersByClub = playerDAO.filterByClub(club);
                    StringBuilder message = new StringBuilder();
                    message.append(club.getName()).append(" players:").append("\n");
                    for (Player player: playersByClub) {
                        message.append(player.getPlayerId()).append(": ").append(player.getfName()).append(" ").append(player.getlName()).append(" (").append(player.getNationality()).append(", ").append(player.getNominalPosition()).append(")\n");
                    }
                    displayOnScreen(message, "Players for " + input);
                }
            }
        }
        else {
            playersByClub = playerDAO.filterByClub(clubById);
            StringBuilder message = new StringBuilder();
            message.append(clubById.getName()).append(" players:").append("\n");
            for (Player player: playersByClub) {
                message.append(player.getPlayerId()).append(": ").append(player.getfName()).append(" ").append(player.getlName()).append(" (").append(player.getNationality()).append(", ").append(player.getNominalPosition()).append(")\n");
            }
            displayOnScreen(message, "Players for " + input);
        }
    }

    public void displayClubResults(String input) throws SQLException, IOException {
        Club clubById = clubDAO.filterById(input);
        List<Club> clubByName = clubDAO.filterByName(input);
        List<Match> matchesByClub = null;
        if(clubById == null) {
            if(clubByName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Can not find \"" + input + "\" in database!");
            }
            else {
                for (Club club: clubByName) {
                    matchesByClub = matchDAO.filterByClub(club);
                    StringBuilder message = new StringBuilder();
                    for (Match match: matchesByClub) {
                        message.append("Gameweek ").append(match.getGameweek()).append(": ").append(match.getHostID().getName()).append(" ").append(match.getGoalsHost())
                                .append("-").append(match.getGoalsGuest()).append(" ").append(match.getGuestID().getName()).append("\n");
                    }
                    displayOnScreen(message, "Game results for " + input);
                }
            }
        }
        else {
            matchesByClub = matchDAO.filterByClub(clubById);
            StringBuilder message = new StringBuilder();
            for (Match match: matchesByClub) {
                message.append("Gameweek ").append(match.getGameweek()).append(": ").append(match.getHostID().getName()).append(" ").append(match.getGoalsHost())
                        .append("-").append(match.getGoalsGuest()).append(" ").append(match.getGuestID().getName()).append("\n");
            }
            displayOnScreen(message, "Game results for " + input);
        }
    }

    public void displayClubCoach(String input) throws SQLException, IOException {
        Club clubById = clubDAO.filterById(input);
        List<Club> clubByName = clubDAO.filterByName(input);
        List<Coach> listOfCoaches = new ArrayList<>();
        if(clubById == null) {
            if(clubByName.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Can not find \"" + input + "\" in database!");
            }
            else {
                for (Club club : clubByName) {
                    listOfCoaches = coachDAO.filterByClub(club);
                    StringBuilder message = new StringBuilder();
                    for (Coach coach: listOfCoaches) {
                        message.append("Coach of ").append(coach.getClub().getName()).append(": ").append(coach.getfName()).append(" ").append(coach.getlName())
                                .append(" (").append(coach.getNationality()).append(")");
                    }
                    displayOnScreen(message, "Coach found for " + input);
                }
            }
        }
        else {
            listOfCoaches = coachDAO.filterByClub(clubById);
            StringBuilder message = new StringBuilder();
            for (Coach coach: listOfCoaches) {
                message.append("Coach of ").append(coach.getClub().getName()).append(": ").append(coach.getfName()).append(" ").append(coach.getlName())
                        .append(" (").append(coach.getNationality()).append(")");
            }
            displayOnScreen(message, "Coach found for " + input);
        }
    }

    private void displayOnScreen(StringBuilder message, String title) {
        JTextArea textArea = new JTextArea(message.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(1100, 800));
        textArea.setSize(new Dimension(1000,700));
        JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
    }
}

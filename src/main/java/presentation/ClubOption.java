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
                message.append("ID: ").append(club.getClubId()).append(" Name: ").append(club.getName())
                        .append(" Locality: ").append(club.getLocality()).append(" (").append(club.getVoivodeship())
                        .append("), Stadium: ").append(club.getStadiumName()).append(" (").append(club.getStadiumCapacity())
                        .append(" seats),\n");
            }
            JOptionPane.showMessageDialog(null, message);
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
                    JOptionPane.showMessageDialog(null, "Result for \""+input+"\":\nClub name: " + club.getName()
                            + "\nID: " + club.getClubId() + "\nLocality: " + club.getLocality() + "\nVoivodeship: " + club.getVoivodeship()
                            + "\nStadium: " + club.getStadiumName() + " (" + club.getStadiumCapacity()+ " seats)");
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Result for \""+input+"\":\nClub name: " + clubById.getName()
                    + "\nID: " + clubById.getClubId() + "\nLocality: " + clubById.getLocality() + "\nVoivodeship: " + clubById.getVoivodeship()
                    + "\nStadium: " + clubById.getStadiumName() + " (" + clubById.getStadiumCapacity() + " seats)");
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
                    for (Player player: playersByClub) {
                        message.append(player.getPlayerId()).append(": ").append(player.getfName()).append(" ").append(player.getlName()).append(" (").append(player.getNationality()).append(", ").append(player.getNominalPosition()).append(")\n");
                    }
                    JOptionPane.showMessageDialog(null, message.toString());
                }
            }
        }
        else {
            playersByClub = playerDAO.filterByClub(clubById);
            StringBuilder message = new StringBuilder();
            for (Player player: playersByClub) {
                message.append(player.getPlayerId()).append(": ").append(player.getfName()).append(" ").append(player.getlName()).append(" (").append(player.getNationality()).append(", ").append(player.getNominalPosition()).append(")\n");
            }
            JOptionPane.showMessageDialog(null, message.toString());
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
                        message.append("Gameweek ").append(match.getGameweek()).append(": ").append(match.getHostID()).append(" ").append(match.getGoalsHost())
                                .append("-").append(match.getGoalsGuest()).append(" ").append(match.getGuestID()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, message);
                }
            }
        }
        else {
            matchesByClub = matchDAO.filterByClub(clubById);
            StringBuilder message = new StringBuilder();
            for (Match match: matchesByClub) {
                message.append("Gameweek ").append(match.getGameweek()).append(": ").append(match.getHostID()).append(" ").append(match.getGoalsHost())
                        .append("-").append(match.getGoalsGuest()).append(" ").append(match.getGuestID()).append("\n");
            }
            JOptionPane.showMessageDialog(null, message);
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
                        message.append("Coach of ").append(coach.getClub()).append(": ").append(coach.getfName()).append(" ").append(coach.getlName())
                                .append(" (").append(coach.getNationality()).append(")");
                    }
                    JOptionPane.showMessageDialog(null, message.toString());
                }
            }
        }
        else {
            listOfCoaches = coachDAO.filterByClub(clubById);
            StringBuilder message = new StringBuilder();
            for (Coach coach: listOfCoaches) {
                message.append("Coach of ").append(coach.getClub()).append(": ").append(coach.getfName()).append(" ").append(coach.getlName())
                        .append(" (").append(coach.getNationality()).append(")");
            }
            JOptionPane.showMessageDialog(null, message.toString());
        }
    }
}

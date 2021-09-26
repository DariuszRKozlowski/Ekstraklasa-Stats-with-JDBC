package application;

import dao.ClubDAOImplementation;
import dao.CoachDAOImplementation;
import dao.MatchDAOImplementation;
import dao.PlayerDAOImplementation;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Club {
    private final String clubId;
    private final String name;
    private final String locality;
    private final String voivodeship;
    private String stadiumName;
    private int stadiumCapacity;
    private static final ClubDAOImplementation clubDAO = new ClubDAOImplementation();
    private static final PlayerDAOImplementation playerDAO = new PlayerDAOImplementation();
    private static final MatchDAOImplementation matchDAO = new MatchDAOImplementation();
    private static final CoachDAOImplementation coachDAO = new CoachDAOImplementation();


    public Club(String clubId, String name, String locality, String voivodeship, String stadiumName, int stadiumCapacity) {
        this.clubId = clubId;
        this.name = name;
        this.locality = locality;
        this.voivodeship = voivodeship;
        this.stadiumName = stadiumName;
        this.stadiumCapacity = stadiumCapacity;
    }

    public String getClubId() {
        return clubId;
    }

    public String getName() {
        return name;
    }

    public String getLocality() {
        return locality;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public int getStadiumCapacity() {
        return stadiumCapacity;
    }

    public static void displayGeneralInfo(String input) throws SQLException, IOException {
        Club clubById = clubDAO.filterById(input);
        List<Club> clubByName = clubDAO.filterByName(input);
        if(clubById == null) {
            if(clubByName == null) {
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

    public static void displayPlayers(String input) throws SQLException, IOException {
        Club clubById = clubDAO.filterById(input);
        List<Club> clubByName = clubDAO.filterByName(input);
        List<Player> playersByClub = null;
        if(clubById == null) {
            if(clubByName == null) {
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

    public static void displayResults(String input) throws SQLException, IOException {
        Club clubById = clubDAO.filterById(input);
        List<Club> clubByName = clubDAO.filterByName(input);
        List<Match> matchesByClub = null;
        if(clubById == null) {
            if(clubByName == null) {
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

    public static void displayCoach(String input) throws SQLException, IOException {
        Club clubById = clubDAO.filterById(input);
        List<Club> clubByName = clubDAO.filterByName(input);
        List<Coach> listOfCoaches = new ArrayList<>();
        if(clubById == null) {
            if(clubByName == null) {
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

    public static void displayAllClubs() throws SQLException, IOException {
        List<Club> listOfClubs = clubDAO.getAll();
        StringBuilder message = new StringBuilder();
        for (Club club: listOfClubs) {
            message.append("ID: ").append(club.getClubId()).append(" Name: ").append(club.getName())
                    .append(" Locality: ").append(club.getLocality()).append(" (").append(club.getVoivodeship())
                    .append("), Stadium: ").append(club.getStadiumName()).append(" (").append(club.getStadiumCapacity())
                    .append(" seats),\n");
        }
        JOptionPane.showMessageDialog(null, message);
    }
}

package presentation;

import application.Club;
import application.Match;
import application.Referee;
import dao.ClubDAOImplementation;
import dao.MatchDAOImplementation;
import dao.RefereeDAOImplementation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchOption implements OptionInterface {

    private final MatchDAOImplementation matchDAO = new MatchDAOImplementation();
    private final RefereeDAOImplementation refereeDAO = new RefereeDAOImplementation();
    private final ClubDAOImplementation clubDAO = new ClubDAOImplementation();

    @Override
    public void displayAll() throws SQLException, IOException {
        List<Match> listOfMatches = matchDAO.getAll();
        if(listOfMatches.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Can not find matches in database!");
        }
        else {
            StringBuilder message = new StringBuilder();
            for (Match match: listOfMatches) {
                message.append("Date: ").append(match.getDate().toString()).append(" | Gameweek: ").append(match.getGameweek())
                        .append(" | Game: ").append(match.getHostID().getName()).append(" ").append(match.getGoalsHost())
                        .append("-").append(match.getGoalsGuest()).append(" ").append(match.getGuestID().getName())
                        .append(" | Attendance: ").append(match.getAttendance()).append("\n");
            }
            JTextArea textArea = new JTextArea(message.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(900, 900));
            textArea.setSize(new Dimension(900,700));
            JOptionPane.showMessageDialog(null, scrollPane, "Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void displaySpecificOne(String input) throws SQLException, IOException {
        Match match = matchDAO.filterById(input);
        if(match == null) {
            JOptionPane.showMessageDialog(null, "Can not find match in database!");
        }
        else {
            StringBuilder message = new StringBuilder();
            message.append("Date: ").append(match.getDate().toString()).append(" | Gameweek: ").append(match.getGameweek())
                    .append(" | Game: ").append(match.getHostID().getName()).append(" ").append(match.getGoalsHost())
                    .append("-").append(match.getGoalsGuest()).append(" ").append(match.getGuestID().getName())
                    .append(" | Attendance: ").append(match.getAttendance()).append(" | Referee: ")
                    .append(match.getReferee().getfName()).append(" ").append(match.getReferee().getlName()).append("\n");
            JOptionPane.showMessageDialog(null, message);
        }
    }

    public void displayWholeGameweek(int gameweek) throws SQLException, IOException {
        List<Match> listOfMatches = matchDAO.filterByGameweek(gameweek);
        if(listOfMatches.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Can not find this gameweek in database!");
        }
        else {
            StringBuilder message = new StringBuilder();
            message.append("Gameweek ").append(gameweek).append("\n");
            for (Match match : listOfMatches) {
                message.append("Date: ").append(match.getDate().toString())
                        .append(" | Game: ").append(match.getHostID().getName()).append(" ").append(match.getGoalsHost())
                        .append("-").append(match.getGoalsGuest()).append(" ").append(match.getGuestID().getName())
                        .append(" | Attendance: ").append(match.getAttendance()).append(" | Referee: ")
                        .append(match.getReferee().getfName()).append(" ").append(match.getReferee().getlName()).append("\n");
            }
            JOptionPane.showMessageDialog(null, message);
        }
    }

    public void displayByReferee(String input) throws SQLException, IOException {
        List<Referee> listOfReferees = refereeDAO.filterByLastName(input);
        if(listOfReferees.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There is no referee for input: " + input + " in database!");
        }
        else {
            for (Referee referee: listOfReferees) {
                List<Match> listOfMatches = matchDAO.filterByReferee(referee);
                if(listOfMatches.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Can not find matches for referee id="+input+"!");
                }
                else {
                    StringBuilder message = new StringBuilder();
                    message.append("Referee ").append(referee.getfName()).append(" ").append(referee.getlName()).append(":\n");
                    for (Match match : listOfMatches) {
                        message.append("Date: ").append(match.getDate().toString()).append(" | Gameweek: ").append(match.getGameweek())
                                .append(" | Game: ").append(match.getHostID().getName()).append(" ").append(match.getGoalsHost())
                                .append("-").append(match.getGoalsGuest()).append(" ").append(match.getGuestID().getName())
                                .append(" | Attendance: ").append(match.getAttendance()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, message);
                }
            }
        }

    }

    public void displayByReferee(int input) throws SQLException, IOException {
        Referee referee = refereeDAO.filterById(input);
        List<Match> listOfMatches = new ArrayList<>();
        if(referee != null) {
            listOfMatches = matchDAO.filterByReferee(referee);
        }
        if(listOfMatches.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Can not find matches for referee id="+input+"!");
        }
        else {
            StringBuilder message = new StringBuilder();
            message.append("Referee ").append(referee.getfName()).append(" ").append(referee.getlName()).append(":\n");
            for (Match match : listOfMatches) {
                message.append("Date: ").append(match.getDate().toString()).append(" | Gameweek: ").append(match.getGameweek())
                        .append(" | Game: ").append(match.getHostID().getName()).append(" ").append(match.getGoalsHost())
                        .append("-").append(match.getGoalsGuest()).append(" ").append(match.getGuestID().getName())
                        .append(" | Attendance: ").append(match.getAttendance()).append("\n");
            }
            JOptionPane.showMessageDialog(null, message);
        }
    }

    public void displayByClub(String input) throws SQLException, IOException {
        Club club = clubDAO.filterById(input);
        List<Club> listOfClubs = clubDAO.filterByName(input);
        if (club == null) {
            if(listOfClubs.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Can not find matches for: "+input+"!");
            }
            else {
                for (Club clubFromList: listOfClubs) {
                    List<Match> listOfMatches = matchDAO.filterByClub(clubFromList);
                    StringBuilder message = new StringBuilder();
                    for (Match match: listOfMatches) {
                        message.append("Date: ").append(match.getDate().toString()).append(" | Gameweek: ").append(match.getGameweek())
                                .append(" | Game: ").append(match.getHostID().getName()).append(" ").append(match.getGoalsHost())
                                .append("-").append(match.getGoalsGuest()).append(" ").append(match.getGuestID().getName())
                                .append(" | Attendance: ").append(match.getAttendance()).append(" | Referee: ")
                                .append(match.getReferee().getfName()).append(" ").append(match.getReferee().getlName()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, message);
                }
            }
        }
        else {
            List<Match> listOfMatches = matchDAO.filterByClub(club);
            StringBuilder message = new StringBuilder();
            for (Match match: listOfMatches) {
                message.append("Date: ").append(match.getDate().toString()).append(" | Gameweek: ").append(match.getGameweek())
                        .append(" | Game: ").append(match.getHostID().getName()).append(" ").append(match.getGoalsHost())
                        .append("-").append(match.getGoalsGuest()).append(" ").append(match.getGuestID().getName())
                        .append(" | Attendance: ").append(match.getAttendance()).append(" | Referee: ")
                        .append(match.getReferee().getfName()).append(" ").append(match.getReferee().getlName()).append("\n");
            }
            JOptionPane.showMessageDialog(null, message);
        }
    }

}

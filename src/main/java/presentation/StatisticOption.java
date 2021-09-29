package presentation;

import application.Match;
import application.Player;
import application.Statistic;
import dao.ClubDAOImplementation;
import dao.MatchDAOImplementation;
import dao.PlayerDAOImplementation;
import dao.StatisticDAOImplementation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StatisticOption implements OptionInterface {
    private final StatisticDAOImplementation statisticDAO = new StatisticDAOImplementation();
    private final MatchDAOImplementation matchDAO = new MatchDAOImplementation();
    private final PlayerDAOImplementation playerDAO = new PlayerDAOImplementation();
    private final ClubDAOImplementation clubDAO = new ClubDAOImplementation();

    @Override
    public void displayAll() throws SQLException, IOException {
        List<Statistic> listOfStatistics = statisticDAO.getAll();
        if(listOfStatistics.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Can not find any statistic in database!");
        }
        else {
            StringBuilder message = new StringBuilder();
            for (Statistic statistic: listOfStatistics) {
                message.append("ID: ").append(statistic.getStatId()).append(" | Match: ").append(statistic.getMatchID().getMatchId())
                        .append(" | Player: ").append(statistic.getPlayerId().getfName()).append(" ").append(statistic.getPlayerId().getlName())
                        .append(" | Minutes: ").append(statistic.getMinutes()).append(" | Goals: ").append(statistic.getGoals())
                        .append(" | Assists: ").append(statistic.getAssists()).append(" | Clean sheet: ").append(statistic.getCleanSheets())
                        .append(" | Yellow card: ").append(statistic.getYellowCards()).append(" | Red card: ").append(statistic.getRedCards()).append("\n");
            }
            JTextArea textArea = new JTextArea(message.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(1100, 800));
            textArea.setSize(new Dimension(1000,700));
            JOptionPane.showMessageDialog(null, scrollPane, "Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void displaySpecificOne(String statisticId) throws SQLException, IOException {
        Statistic statistic = statisticDAO.filterById(statisticId);
        if(statistic == null) {
            JOptionPane.showMessageDialog(null, "Can not find " + statisticId + " in database!");
        }
        else {
            StringBuilder message = new StringBuilder();
            message.append("Results for \"").append(statistic.getStatId()).append("\":\n");
            message.append("ID: ").append(statistic.getStatId()).append(" | Match: ").append(statistic.getMatchID().getMatchId())
                    .append(" | Gameweek: ").append(statistic.getGameweek()).append(" | Minutes: ")
                    .append(statistic.getMinutes()).append(" | Goals: ").append(statistic.getGoals())
                    .append(" | Assist: ").append(statistic.getAssists()).append(" | Clean sheet: ").append(statistic.getCleanSheets())
                    .append(" | Own goals: ").append(statistic.getOwnGoals()).append(" | Yellow cards: ").append(statistic.getYellowCards())
                    .append(" | Red card: ").append(statistic.getRedCards()).append("\n");
            JOptionPane.showMessageDialog(null, message);
        }
    }

    public void displaySpecificPlayerStatistics(String input) throws SQLException, IOException {
        Player player = playerDAO.filterById(input);
        List<Player> listOfPlayers = playerDAO.filterByLastName(input);
        if(player == null) {
            if(listOfPlayers.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Can not find " + input + " in database!");
            }
            else {
                StringBuilder message = new StringBuilder();
                message.append("Results for \"").append(input).append("\"\n");
                for (Player playerFromList: listOfPlayers) {
                    List<Statistic> listOfStatistics = statisticDAO.filterByPlayer(playerFromList);
                    message.append("Player: ").append(playerFromList.getfName()).append(" ").append(playerFromList.getlName()).append("\n").append("Statistics:\n");
                    for (Statistic statistic: listOfStatistics) {
                        message.append("ID: ").append(statistic.getStatId()).append(" | Match: ").append(statistic.getMatchID().getMatchId())
                                .append(" | Gameweek: ").append(statistic.getGameweek()).append(" | Minutes: ")
                                .append(statistic.getMinutes()).append(" | Goals: ").append(statistic.getGoals())
                                .append(" | Assist: ").append(statistic.getAssists()).append(" | Clean sheet: ").append(statistic.getCleanSheets())
                                .append(" | Own goals: ").append(statistic.getOwnGoals()).append(" | Yellow cards: ").append(statistic.getYellowCards())
                                .append(" | Red card: ").append(statistic.getRedCards()).append("\n");
                    }
                JOptionPane.showMessageDialog(null, message);
                }
            }
        }
        else {
            StringBuilder message = new StringBuilder();
            message.append("Results for \"").append(input).append("\":\n");
            List<Statistic> listOfStatistics = statisticDAO.filterByPlayer(player);
            message.append("Player: ").append(player.getfName()).append(" ").append(player.getlName()).append(":\n");
            for (Statistic statistic: listOfStatistics) {
                message.append("ID: ").append(statistic.getStatId()).append(" | Match: ").append(statistic.getMatchID().getMatchId())
                        .append(" | Gameweek: ").append(statistic.getGameweek()).append(" | Minutes: ")
                        .append(statistic.getMinutes()).append(" | Goals: ").append(statistic.getGoals())
                        .append(" | Assist: ").append(statistic.getAssists()).append(" | Clean sheet: ").append(statistic.getCleanSheets())
                        .append(" | Own goals: ").append(statistic.getOwnGoals()).append(" | Yellow cards: ").append(statistic.getYellowCards())
                        .append(" | Red card: ").append(statistic.getRedCards()).append("\n");
            }
            JOptionPane.showMessageDialog(null, message);
        }
    }

    public void displaySpecificMatchStatistics(String input) throws SQLException, IOException {
        Match match = matchDAO.filterById(input);
        if(match == null) {
            JOptionPane.showMessageDialog(null, "Can not find \"" + input + "\" in database!");
        }
        else {
            StringBuilder message = new StringBuilder();
            List<Statistic> listOfStatistics = statisticDAO.filterByMatch(match);
            if(listOfStatistics.isEmpty()) {
                JOptionPane.showMessageDialog(null, "There isn't any statistics according to this match!");
            }
            else {
                message.append("Results for \"").append(input).append("\":\n");
                for (Statistic statistic: listOfStatistics) {
                    message.append("ID: ").append(statistic.getStatId()).append(" | Match: ").append(statistic.getMatchID().getMatchId())
                            .append(" | Gameweek: ").append(statistic.getGameweek()).append(" | Minutes: ")
                            .append(statistic.getMinutes()).append(" | Goals: ").append(statistic.getGoals())
                            .append(" | Assist: ").append(statistic.getAssists()).append(" | Clean sheet: ").append(statistic.getCleanSheets())
                            .append(" | Own goals: ").append(statistic.getOwnGoals()).append(" | Yellow cards: ").append(statistic.getYellowCards())
                            .append(" | Red card: ").append(statistic.getRedCards()).append("\n");
                }
                JOptionPane.showMessageDialog(null, message);
            }
        }
    }
}

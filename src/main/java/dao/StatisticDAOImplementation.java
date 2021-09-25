package dao;

import application.Club;
import application.Match;
import application.Player;
import application.Statistic;
import data.DatabaseConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatisticDAOImplementation implements StatisticDAOi {

    private static final String SELECT_ALL = "SELECT * FROM Stats";
    private static final String FILTER = " WHERE ";
    private static final String ID_FILTER = " statID= '";
    private static final String MATCH_FILTER = " matchID= '";
    private static final String PLAYER_FILTER = " playerID= '";
    private static final String ENDING = "';";

    public List<Statistic> getAll() throws IOException, SQLException {
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(SELECT_ALL + ";");
        List<Statistic> listOfStats = new ArrayList<>();
        MatchDAOImplementation matchDAO = new MatchDAOImplementation();
        PlayerDAOImplementation playerDAO = new PlayerDAOImplementation();
        ClubDAOImplementation clubDAO = new ClubDAOImplementation();
        while(result.next()) {
            String statId = result.getString(1);
            Match matchId = matchDAO.filterById(result.getString(2));
            int gameweek = result.getInt(3);
            Player playerId = playerDAO.filterById(result.getString(4));
            Club rivalId = clubDAO.filterById(result.getString(5));
            int minutes = result.getInt(6);
            int goals = result.getInt(7);
            int assists = result.getInt(8);
            byte cleanSheets = result.getByte(9);
            byte ownGoals = result.getByte(10);
            byte yellowCards = result.getByte(11);
            byte redCards = result.getByte(12);
            listOfStats.add(new Statistic(statId, matchId, gameweek, playerId, rivalId, minutes, goals, assists, cleanSheets, ownGoals, yellowCards, redCards));
        }
        connection.close();
        return listOfStats;
    }

    public List<Statistic> filterByPlayer(Player player) throws IOException, SQLException {
        String query = SELECT_ALL + FILTER + PLAYER_FILTER + player.getPlayerId() + ENDING;
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        List <Statistic> listOfStatsByPlayer = new ArrayList<>();
        MatchDAOImplementation matchDAO = new MatchDAOImplementation();
        PlayerDAOImplementation playerDAO = new PlayerDAOImplementation();
        ClubDAOImplementation clubDAO = new ClubDAOImplementation();
        while(result.next()) {
            String statId = result.getString(1);
            Match matchId = matchDAO.filterById(result.getString(2));
            int gameweek = result.getInt(3);
            Player playerId = playerDAO.filterById(result.getString(4));
            Club rivalId = clubDAO.filterById(result.getString(5));
            int minutes = result.getInt(6);
            int goals = result.getInt(7);
            int assists = result.getInt(8);
            byte cleanSheets = result.getByte(9);
            byte ownGoals = result.getByte(10);
            byte yellowCards = result.getByte(11);
            byte redCards = result.getByte(12);
            listOfStatsByPlayer.add(new Statistic(statId, matchId, gameweek, playerId, rivalId, minutes, goals, assists, cleanSheets, ownGoals, yellowCards, redCards));
        }
        connection.close();
        return listOfStatsByPlayer;
    }

    public List<Statistic> filterByMatch(Match match) throws IOException, SQLException {
        String query = SELECT_ALL + FILTER + MATCH_FILTER + match.getMatchId() + ENDING;
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        List<Statistic> listOfStatsByMatch = new ArrayList<>();
        MatchDAOImplementation matchDAO = new MatchDAOImplementation();
        PlayerDAOImplementation playerDAO = new PlayerDAOImplementation();
        ClubDAOImplementation clubDAO = new ClubDAOImplementation();
        while(result.next()) {
            String statId = result.getString(1);
            Match matchId = matchDAO.filterById(result.getString(2));
            int gameweek = result.getInt(3);
            Player playerId = playerDAO.filterById(result.getString(4));
            Club rivalId = clubDAO.filterById(result.getString(5));
            int minutes = result.getInt(6);
            int goals = result.getInt(7);
            int assists = result.getInt(8);
            byte cleanSheets = result.getByte(9);
            byte ownGoals = result.getByte(10);
            byte yellowCards = result.getByte(11);
            byte redCards = result.getByte(12);
            listOfStatsByMatch.add(new Statistic(statId, matchId, gameweek, playerId, rivalId, minutes, goals, assists, cleanSheets, ownGoals, yellowCards, redCards));
        }
        connection.close();
        return listOfStatsByMatch;
    }

    public Statistic filterById(Match match, Player player) throws IOException, SQLException {
        String query = SELECT_ALL + FILTER + ID_FILTER  + match.getMatchId() + ":" + player.getPlayerId() + ENDING;
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        List<Statistic> listOfStatsById = new ArrayList<>();
        MatchDAOImplementation matchDAO = new MatchDAOImplementation();
        PlayerDAOImplementation playerDAO = new PlayerDAOImplementation();
        ClubDAOImplementation clubDAO = new ClubDAOImplementation();
        while(result.next()) {
            String statId = result.getString(1);
            Match matchId = matchDAO.filterById(result.getString(2));
            int gameweek = result.getInt(3);
            Player playerId = playerDAO.filterById(result.getString(4));
            Club rivalId = clubDAO.filterById(result.getString(5));
            int minutes = result.getInt(6);
            int goals = result.getInt(7);
            int assists = result.getInt(8);
            byte cleanSheets = result.getByte(9);
            byte ownGoals = result.getByte(10);
            byte yellowCards = result.getByte(11);
            byte redCards = result.getByte(12);
            listOfStatsById.add(new Statistic(statId, matchId, gameweek, playerId, rivalId, minutes, goals, assists, cleanSheets, ownGoals, yellowCards, redCards));
        }
        connection.close();
        return listOfStatsById.get(0);
    }

}

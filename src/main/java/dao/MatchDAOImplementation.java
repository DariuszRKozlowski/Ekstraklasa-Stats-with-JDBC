package dao;

import application.Club;
import application.Match;
import application.Referee;
import data.DatabaseConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatchDAOImplementation implements MatchDAOi {

    private static final String SELECT_ALL = "SELECT * FROM Matches";
    private static final String FILTER = " WHERE ";
    private static final String GAMEWEEK_FILTER = "gameweek= ";
    private static final String ID_FILTER = "matchID= '";
    private static final String CLUB_FILTER1 = "hostID= '";
    private static final String OR = "' OR ";
    private static final String CLUB_FILTER2 = "guestID= '";
    private static final String REFEREE_FILTER = "referee=";
    private static final String ENDING = "';";
    private final ClubDAOImplementation clubDAO = new ClubDAOImplementation();
    private final RefereeDAOImplementation refereeDAO = new RefereeDAOImplementation();

    @Override
    public List<Match> getAll() throws IOException, SQLException {
        String query = SELECT_ALL + ";";
        return this.executeQuery(query);
    }

    @Override
    public List<Match> filterByGameweek(int gw) throws IOException, SQLException {
        String query = SELECT_ALL + FILTER + GAMEWEEK_FILTER + gw + ";";
        return this.executeQuery(query);
    }

    @Override
    public Match filterById(String id) throws IOException, SQLException {
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        List<Match> listOfMatchesById = new ArrayList<>();
        String query = SELECT_ALL + FILTER + ID_FILTER + id + ENDING;
        ResultSet result = statement.executeQuery(query);
        int gameweek = result.getInt(1);
        String matchId = result.getString(2);
        Date date = result.getDate(3);
        Club hostID = clubDAO.filterById(result.getString(4));
        Club guestID = clubDAO.filterById(result.getString(5));
        int goalsHost = result.getInt(6);
        int goalsGuest = result.getInt(7);
        Referee referee = refereeDAO.filterById(result.getInt(8));
        int attendance = result.getInt(9);
        listOfMatchesById.add(new Match(gameweek, matchId, date, hostID, guestID, goalsHost, goalsGuest, referee, attendance));
        connection.close();
        return listOfMatchesById.get(0);
    }

    @Override
    public List<Match> filterByClub(Club club) throws IOException, SQLException {
        String query = SELECT_ALL + FILTER + CLUB_FILTER1 + club.getClubId() + OR + CLUB_FILTER2 + club.getClubId() + ENDING;
        return this.executeQuery(query);
    }

    @Override
    public List<Match> filterByReferee(Referee ref) throws IOException, SQLException {
        String query = SELECT_ALL + FILTER + REFEREE_FILTER + ref.getId() + ";";
        return this.executeQuery(query);
    }

    private List<Match> executeQuery(String query) throws IOException, SQLException {
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        List<Match> listOfMatches = new ArrayList<>();
        ResultSet result = statement.executeQuery(query);
        while(result.next()) {
            int gameweek = result.getInt(1);
            String matchId = result.getString(2);
            Date date = result.getDate(3);
            Club hostID = clubDAO.filterById(result.getString(4));
            Club guestID = clubDAO.filterById(result.getString(5));
            int goalsHost = result.getInt(6);
            int goalsGuest = result.getInt(7);
            Referee referee = refereeDAO.filterById(result.getInt(8));
            int attendance = result.getInt(9);
            listOfMatches.add(new Match(gameweek, matchId, date, hostID, guestID, goalsHost, goalsGuest, referee, attendance));
        }
        connection.close();
        return listOfMatches;
    }
}

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
    private static final String ALTERNATIVE_ENDING1 = "' ORDER BY gameweek;";
    private static final String ALTERNATIVE_ENDING2 = " ORDER BY date";
    private final ClubDAOImplementation clubDAO = new ClubDAOImplementation();
    private final RefereeDAOImplementation refereeDAO = new RefereeDAOImplementation();

    @Override
    public List<Match> getAll() throws IOException, SQLException {
        String query = SELECT_ALL + ALTERNATIVE_ENDING2;
        return this.executeQuery(query);
    }

    @Override
    public List<Match> filterByGameweek(int gw) throws IOException, SQLException {
        String query = SELECT_ALL + FILTER + GAMEWEEK_FILTER + gw + ALTERNATIVE_ENDING2;
        return this.executeQuery(query);
    }

    @Override
    public Match filterById(String id) throws IOException, SQLException {
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        Match match = null;
        String query = SELECT_ALL + FILTER + ID_FILTER + id + ENDING;
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
            match = new Match(gameweek, matchId, date, hostID, guestID, goalsHost, goalsGuest, referee, attendance);
        }
        connection.close();
        return match;
    }

    @Override
    public List<Match> filterByClub(Club club) throws IOException, SQLException {
        String query = SELECT_ALL + FILTER + CLUB_FILTER1 + club.getClubId() + OR + CLUB_FILTER2 + club.getClubId() + ALTERNATIVE_ENDING1;
        return this.executeQuery(query);
    }

    @Override
    public List<Match> filterByReferee(Referee ref) throws IOException, SQLException {
        String query = SELECT_ALL + FILTER + REFEREE_FILTER + ref.getId() + ALTERNATIVE_ENDING2;
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

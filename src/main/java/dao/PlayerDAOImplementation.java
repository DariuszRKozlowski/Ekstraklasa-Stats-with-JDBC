package dao;

import application.Club;
import application.Player;
import data.DatabaseConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlayerDAOImplementation implements PlayerDAOi{
    private final static String SELECT_ALL = "SELECT * FROM Players";
    private final static String FILTER = " WHERE ";
    private final static String ID_FILTER = " playerID= '";
    private final static String CLUB_FILTER = " clubID= '";
    private final static String LAST_NAME_FILTER = " last_name= '";
    private final static String ENDING = "';";
    private final static ClubDAOImplementation clubDAO = new ClubDAOImplementation();

    @Override
    public List<Player> getAll() throws IOException, SQLException {
        String query = SELECT_ALL + ";";
        return this.executeQuery(query);
    }

    @Override
    public Player filterById(String id) throws IOException, SQLException {
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        String query = SELECT_ALL + FILTER + ID_FILTER + id + ENDING;
        Player player = null;
        ResultSet result = statement.executeQuery(query);
        ClubDAOImplementation clubDAO = new ClubDAOImplementation();
        String playerId = result.getString(1);
        Club clubId = clubDAO.filterById(result.getString(2));
        String fName = result.getString(3);
        String lName = result.getString(4);
        Date birthDate = result.getDate(5);
        String nationality = result.getString(6);
        Date contractValidity = result.getDate(7);
        String nominalPosition = result.getString(8);
        player = new Player(playerId, clubId, fName, lName, birthDate, nationality, contractValidity, nominalPosition);
        connection.close();
        return player;
    }

    @Override
    public List<Player> filterByClub(Club club) throws IOException, SQLException {
        String query = SELECT_ALL + FILTER + CLUB_FILTER + club.getClubId() + ENDING;
        return this.executeQuery(query);
    }

    @Override
    public List<Player> filterByLastName(String lastName) throws IOException, SQLException {
        String query = SELECT_ALL + FILTER + LAST_NAME_FILTER + lastName + ENDING;
        return this.executeQuery(query);
    }

    private List<Player> executeQuery(String query) throws IOException, SQLException {
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        List<Player> listOfPlayers = new ArrayList<>();
        ResultSet result = statement.executeQuery(query);
        while(result.next()) {
            String playerId = result.getString(1);
            Club clubId = clubDAO.filterById(result.getString(2));
            String fName = result.getString(3);
            String lName = result.getString(4);
            Date birthDate = result.getDate(5);
            String nationality = result.getString(6);
            Date contractValidity = result.getDate(7);
            String nominalPosition = result.getString(8);
            listOfPlayers.add(new Player(playerId, clubId, fName, lName, birthDate, nationality, contractValidity, nominalPosition));
        }
        connection.close();
        return listOfPlayers;
    }
}

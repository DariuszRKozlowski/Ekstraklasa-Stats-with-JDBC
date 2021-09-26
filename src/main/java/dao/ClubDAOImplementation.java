package dao;

import application.Club;
import data.DatabaseConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClubDAOImplementation implements ClubDAOi{
    private final static String SELECT_ALL = "SELECT * FROM Clubs";
    private final static String FILTER = " WHERE ";
    private final static String ID_FILTER = "clubID='";
    private final static String NAME_FILTER1 = "name LIKE '%";
    private final static String NAME_FILTER2 = "%';";

    public List<Club> getAll() throws IOException, SQLException {
        String query = SELECT_ALL + ";";
        return this.executeQuery(query);
    }

    public Club filterById(String id) throws IOException, SQLException {
        String query = SELECT_ALL + FILTER + ID_FILTER + id + "';";
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        Club club = null;
        String clubId = result.getString(1);
        String name = result.getString(2);
        String locality = result.getString(3);
        String voivodeship = result.getString(4);
        String stadiumName = result.getString(5);
        int stadiumCapacity = result.getInt(6);
        club =new Club(clubId, name, locality, voivodeship, stadiumName, stadiumCapacity);
        connection.close();
        return club;
    }

    public List<Club> filterByName(String clubName) throws IOException, SQLException {
        String query = SELECT_ALL + FILTER + NAME_FILTER1 + clubName + NAME_FILTER2;
        return this.executeQuery(query);
    }

    private List<Club> executeQuery(String query) throws SQLException, IOException {
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        List<Club> listOfClubs = new ArrayList<>();
        while(result.next()) {
            String clubId = result.getString(1);
            String name = result.getString(2);
            String locality = result.getString(3);
            String voivodeship = result.getString(4);
            String stadiumName = result.getString(5);
            int stadiumCapacity = result.getInt(6);
            listOfClubs.add(new Club(clubId, name, locality, voivodeship, stadiumName, stadiumCapacity));
        }
        connection.close();
        return listOfClubs;
    }


}

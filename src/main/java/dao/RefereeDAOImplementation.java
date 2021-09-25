package dao;

import application.Referee;
import data.DatabaseConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RefereeDAOImplementation implements RefereeDAOi{

    private static final String SELECT_ALL = "SELECT * FROM Referees";
    private static final String FILTER = " WHERE ";
    private static final String ID_FILTER = "refereeID=";
    private static final String LAST_NAME_FILTER = "last_name='";

    public List<Referee> getAll() throws IOException, SQLException {
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        String query = SELECT_ALL + ";";
        ResultSet result = statement.executeQuery(query);
        List<Referee> listOfReferees = new ArrayList<>();
        while(result.next()) {
            int id = result.getInt(1);
            String fName = result.getString(2);
            String lName = result.getString(3);
            listOfReferees.add(new Referee(id, fName, lName));
        }
        connection.close();
        return listOfReferees;
    }

    public Referee filterById(int id) throws IOException, SQLException {
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        String query = SELECT_ALL + FILTER + ID_FILTER + id + ";";
        ResultSet result = statement.executeQuery(query);
        List<Referee> listOfRefereesById = new ArrayList<>();
        while(result.next()) {
            int refereeID = result.getInt(1);
            String fName = result.getString(2);
            String lName = result.getString(3);
            listOfRefereesById.add(new Referee(refereeID, fName, lName));
        }
        connection.close();
        return listOfRefereesById.get(0);
    }

    public List<Referee> filterByLastName(String lastName) throws IOException, SQLException {
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        String query = SELECT_ALL + FILTER + LAST_NAME_FILTER + lastName + "';";
        ResultSet result = statement.executeQuery(query);
        List<Referee> listOfRefereesBylName = new ArrayList<>();
        while(result.next()) {
            int id = result.getInt(1);
            String fName = result.getString(2);
            String lName = result.getString(3);
            listOfRefereesBylName.add(new Referee(id, fName, lName));
        }
        connection.close();
        return listOfRefereesBylName;
    }
}

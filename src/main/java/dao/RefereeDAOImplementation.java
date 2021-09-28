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

    @Override
    public List<Referee> getAll() throws IOException, SQLException {
        String query = SELECT_ALL + ";";
        return this.executeQuery(query);
    }

    @Override
    public Referee filterById(int id) throws IOException, SQLException {
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        String query = SELECT_ALL + FILTER + ID_FILTER + id + ";";
        ResultSet result = statement.executeQuery(query);
        Referee referee = null;
        while(result.next()) {
            int refereeID = result.getInt(1);
            String fName = result.getString(2);
            String lName = result.getString(3);
            referee = new Referee(refereeID, fName, lName);
        }
        connection.close();
        return referee;
    }

    @Override
    public List<Referee> filterByLastName(String lastName) throws IOException, SQLException {
        String query = SELECT_ALL + FILTER + LAST_NAME_FILTER + lastName + "';";
        return this.executeQuery(query);
    }

    private List<Referee> executeQuery(String query) throws IOException, SQLException {
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
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
}

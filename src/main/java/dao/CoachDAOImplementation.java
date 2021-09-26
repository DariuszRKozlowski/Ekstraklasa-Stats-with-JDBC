package dao;

import application.Club;
import application.Coach;
import data.DatabaseConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoachDAOImplementation implements CoachDAOi{
    private static final String SELECT_ALL = "SELECT * FROM Coaches";
    private static final String FILTER = " WHERE ";
    private static final String ID_FILTER = "coachID=";
    private static final String CLUB_FILTER = "club= '";
    private static final String LAST_NAME_FILTER = "last_name= '";
    private static final String ENDING = "';";

    public List<Coach> getAll() throws IOException, SQLException {
        String query = SELECT_ALL + ";";
        List<Coach> listOfCoaches = new ArrayList<>();
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        ClubDAOImplementation clubDAO = new ClubDAOImplementation();
        while(result.next()) {
            int id = result.getInt(1);
            Club club = clubDAO.filterById(result.getString(2));
            String fName = result.getString(3);
            String lName = result.getString(4);
            String nationality = result.getString(5);
            Date birthDate = result.getDate(6);
            listOfCoaches.add(new Coach(id, club, fName, lName, nationality, birthDate));
        }
        connection.close();
        return listOfCoaches;
    }

    public Coach filterById(int coachId) throws IOException, SQLException {
        String query = SELECT_ALL + FILTER + ID_FILTER + coachId + ";";
        Coach coach = null;
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        ClubDAOImplementation coachDAO = new ClubDAOImplementation();
        while(result.next()) {
            int id = result.getInt(1);
            Club team = coachDAO.filterById(result.getString(2));
            String fName = result.getString(3);
            String lName = result.getString(4);
            String nationality = result.getString(5);
            Date birthDate = result.getDate(6);
            coach = new Coach(id, team, fName, lName, nationality, birthDate);
        }
        connection.close();
        return coach;
    }

    public List<Coach> filterByClub(Club club) throws IOException, SQLException {
        String query = SELECT_ALL + FILTER + CLUB_FILTER + club.getClubId() + ENDING;
        List<Coach> listOfCoachesByClub = new ArrayList<>();
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        ClubDAOImplementation coachDAO = new ClubDAOImplementation();
        while(result.next()) {
            int id = result.getInt(1);
            Club team = coachDAO.filterById(result.getString(2));
            String fName = result.getString(3);
            String lName = result.getString(4);
            String nationality = result.getString(5);
            Date birthDate = result.getDate(6);
            listOfCoachesByClub.add(new Coach(id, team, fName, lName, nationality, birthDate));
        }
        connection.close();
        return listOfCoachesByClub;
    }

    public List<Coach> filterByLastName(String lastName) throws IOException, SQLException {
        String query = SELECT_ALL + FILTER + LAST_NAME_FILTER + lastName + ENDING;
        List<Coach> listOfCoachesByLastName = new ArrayList<>();
        Connection connection = DatabaseConnector.connectToDatabase();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        ClubDAOImplementation coachDAO = new ClubDAOImplementation();
        while(result.next()) {
            int id = result.getInt(1);
            Club team = coachDAO.filterById(result.getString(2));
            String fName = result.getString(3);
            String lName = result.getString(4);
            String nationality = result.getString(5);
            Date birthDate = result.getDate(6);
            listOfCoachesByLastName.add(new Coach(id, team, fName, lName, nationality, birthDate));
        }
        connection.close();
        return listOfCoachesByLastName;
    }
}

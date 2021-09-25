package dao;

import application.Club;
import application.Coach;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CoachDAOi {

    List<Coach> getAll() throws IOException, SQLException;

    List<Coach> filterById(int id) throws IOException, SQLException;

    List<Coach> filterByClub(Club club) throws IOException, SQLException;

    List<Coach> filterByLastName(String lastName) throws IOException, SQLException;
}

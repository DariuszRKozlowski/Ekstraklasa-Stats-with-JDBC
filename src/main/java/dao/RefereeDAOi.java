package dao;

import application.Referee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RefereeDAOi {

    List<Referee> getAll() throws IOException, SQLException;

    Referee filterById(int id) throws IOException, SQLException;

    List<Referee> filterByLastName (String lName) throws IOException, SQLException;
}

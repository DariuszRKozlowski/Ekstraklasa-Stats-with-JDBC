package dao;

import application.Club;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ClubDAOi {

    List<Club> getAll() throws IOException, SQLException;

    Club filterById(String id) throws IOException, SQLException;

    List<Club> filterByName(String name) throws IOException, SQLException;
}

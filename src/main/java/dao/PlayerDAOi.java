package dao;

import application.Club;
import application.Player;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PlayerDAOi {

    List<Player> getAll() throws IOException, SQLException;

    Player filterById(String id) throws IOException, SQLException;

    List<Player> filterByClub(Club club) throws IOException, SQLException;

    List<Player> filterByLastName(String lName) throws IOException, SQLException;

}

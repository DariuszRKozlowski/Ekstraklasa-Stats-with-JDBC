package dao;

import application.Match;
import application.Player;
import application.Statistic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StatisticDAOi {

    List<Statistic> getAll() throws IOException, SQLException;

    List<Statistic> filterByPlayer(Player player) throws IOException, SQLException;

    List<Statistic> filterByMatch(Match match) throws IOException, SQLException;

    Statistic filterById(String id) throws IOException, SQLException;
}

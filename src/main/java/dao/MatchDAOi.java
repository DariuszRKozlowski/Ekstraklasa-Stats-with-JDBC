package dao;

import application.Club;
import application.Match;
import application.Referee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface MatchDAOi {

    List<Match> getAll() throws IOException, SQLException;

    List<Match> filterByGameweek(int gameweek) throws IOException, SQLException;

    Match filterById(String id) throws IOException, SQLException;

    List<Match> filterByClub(Club club) throws IOException, SQLException;

    List<Match> filterByReferee(Referee referee) throws IOException, SQLException;
}

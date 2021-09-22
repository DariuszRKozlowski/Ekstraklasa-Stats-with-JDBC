import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection dbConnection  = DatabaseConnector.connectToDatabase();
        Statement statement = dbConnection.createStatement();

        List<Team> teams = EkstraklasaTable.createTeamsFromQuery(statement);
        Team.sumEachTeamPoints(teams, statement);
        EkstraklasaTable.createConsoleTable(teams);

        String lname = "Ishak";
        PlayerStats.getInfoAboutPlayer(statement, lname);

        String clubName = "LPO";
        PlayerStats.getInfoAboutTeam(statement, clubName);
    }
}

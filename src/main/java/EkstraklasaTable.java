import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EkstraklasaTable {

    public static List<Team> createTeamsFromQuery(Statement statement) {
        List<Team> teamList = new ArrayList<>();
        try {
            ResultSet queryResult = statement.executeQuery("SELECT DISTINCT clubID FROM Clubs");
            while(queryResult.next()) {
                teamList.add(new Team(queryResult.getString(1)));
            }

        } catch (SQLException exception) {
            System.out.println("Can not process this query!");
            exception.printStackTrace();
        }
        return teamList;
    }

    public static void createConsoleTable(List<Team> teams) {

        for(int i = 0 ; i < teams.size() ; i++) {
            for(int j = 0 ; j < teams.size() ; j++) {
                if(teams.get(i).getPoints() > teams.get(j).getPoints()) {
                    Team helpTeam = teams.get(i);
                    teams.set(i,teams.get(j));
                    teams.set(j,helpTeam);
                }
                else if (teams.get(i).getPoints() == teams.get(j).getPoints()) {
                    int difference1 = teams.get(i).getGoalsScored() - teams.get(i).getGoalsConceded();
                    int difference2 = teams.get(j).getGoalsScored() - teams.get(j).getGoalsConceded();
                    if(difference1 > difference2) {
                        Team helpTeam = teams.get(i);
                        teams.set(i, teams.get(j));
                        teams.set(j, helpTeam);
                    }
                }
            }
        }
        System.out.println("#\tT\tM\tW\tD\tL\t G\tP\tF");
        int counter = 1;
        for (Team team: teams) {
            System.out.println(counter + "\t" + team.getName() + "\t" + team.getMatchCounter() + "\t" + team.getWinCounter() + "\t" + team.getDrawCounter() + "\t" + team.getLostCounter() + "\t"
                    + team.getGoalsScored() + ":" + team.getGoalsConceded() + " " + team.getPoints() + "\t" + team.getForm());
            counter++;
        }
        System.out.println("T- team\nM- matches\nW- wins\nD- draws\nL- losts\nG- goals balance\nP- points\nF- form");

    }
}

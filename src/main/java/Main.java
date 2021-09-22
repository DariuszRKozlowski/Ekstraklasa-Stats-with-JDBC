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
        List<Team> teams = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT DISTINCT clubID FROM Clubs");
        while(resultSet.next()) {
            teams.add(new Team(resultSet.getString(1)));
        }
        Statement matchesStatement = dbConnection.createStatement();
        for (Team team: teams) {
            ResultSet result = matchesStatement.executeQuery("SELECT * FROM Matches WHERE hostID = \'" + team.getName() + "\' OR guestID = \'" + team.getName() + "\' ORDER BY gameweek;");
            while(result.next()){
                team.addGame();
                if(result.getString("hostID").equals(team.getName())) {
                    team.addGoalScored(result.getInt("goals_host"));
                    team.addGoalConceded(result.getInt("goals_guest"));
                    if(result.getInt("goals_host") > result.getInt("goals_guest")) {
                        team.addWin();
                        team.addPoints(3);
                        team.addForm("W");
                    }
                    else if(result.getInt("goals_host") == result.getInt("goals_guest")) {
                        team.addDraw();
                        team.addPoints(1);
                        team.addForm("D");
                    }
                    else {
                        team.addLost();
                        team.addPoints(0);
                        team.addForm("L");
                    }
                }
                else {
                    team.addGoalScored(result.getInt("goals_guest"));
                    team.addGoalConceded(result.getInt("goals_host"));
                    if(result.getInt("goals_host") < result.getInt("goals_guest")) {
                        team.addWin();
                        team.addPoints(3);
                        team.addForm("W");
                    }
                    else if(result.getInt("goals_host") == result.getInt("goals_guest")) {
                        team.addDraw();
                        team.addPoints(1);
                        team.addForm("D");
                    }
                    else {
                        team.addLost();
                        team.addPoints(0);
                        team.addForm("L");
                    }
                }
            }
            System.out.println();
        }
        for (Team team: teams) {
            System.out.println(team.getName() + " " + team.getMatchCounter() + " " + team.getWinCounter() + " " + team.getDrawCounter() + " " + team.getLostCounter() + " "
            + team.getGoalsScored() + ":" + team.getGoalsConceded() + " " + team.getPoints());
        }
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
        System.out.println("Tabela po sortowaniu");
        int counter = 1;
        for (Team team: teams) {
            System.out.println(counter + ". " + team.getName() + "\t" + team.getMatchCounter() + "\t" + team.getWinCounter() + "\t" + team.getDrawCounter() + "\t" + team.getLostCounter() + "\t"
                    + team.getGoalsScored() + ":" + team.getGoalsConceded() + " " + team.getPoints() + "\t\t" + team.getForm());
            counter++;
        }

    }
}

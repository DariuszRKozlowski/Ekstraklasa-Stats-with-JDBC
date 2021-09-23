package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Team {

    private final String name;
    private int matchCounter;
    private int winCounter;
    private int drawCounter;
    private int lostCounter;
    private int goalsScored;
    private int goalsConceded;
    private int points;
    private String form = "";

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMatchCounter() {
        return matchCounter;
    }

    public int getWinCounter() {
        return winCounter;
    }

    public int getDrawCounter() {
        return drawCounter;
    }

    public int getLostCounter() {
        return lostCounter;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public int getPoints() {
        return points;
    }

    public String getForm() {
        return form;
    }

    public void addPoints(int howMany) {
        points += howMany;
    }

    public void addGame() {
        matchCounter++;
    }

    public void addGoalScored(int goals) {
        goalsScored += goals;
    }

    public void addGoalConceded(int goals) {
        goalsConceded += goals;
    }

    public void addWin() {
        winCounter++;
    }

    public void addDraw() {
        drawCounter++;
    }

    public void addLost() {
        lostCounter++;
    }

    public void addForm(String text) {
        form = text + " " + form;
    }

    public static void sumEachTeamPoints(List<Team> teams , Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        for (Team team: teams) {
            ResultSet result = null;
            try {
                result = statement.executeQuery("SELECT * FROM Matches WHERE hostID = '" + team.getName() + "' OR guestID = '" + team.getName() + "' ORDER BY gameweek;");
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
            } catch (SQLException exception) {
                System.out.println("Can not process this query!");
                exception.printStackTrace();
            }

        }
    }
}

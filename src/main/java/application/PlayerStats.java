package application;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerStats {

    public static void getInfoAboutPlayer(Connection connection, String name) throws SQLException {
        Statement statement = connection.createStatement();
        try {
            try (ResultSet resultQuery = statement.executeQuery("SELECT P.first_name, P.last_name, SUM(S.game_minutes) AS Minutes, SUM(S.goals) AS Goals, SUM(S.assists) AS Assists, " +
                    "SUM(CAST(S.clean_sheet AS tinyint)) AS CleanSheets, SUM(CAST(S.yellow_card AS tinyint)) AS YellowCards, SUM(CAST(S.red_card AS tinyint)) " +
                    "AS RedCards, P.clubID FROM Players AS P INNER JOIN Stats AS S ON P.playerID = S.playerID WHERE P.last_name = '" + name + "' GROUP BY P.last_name, P.first_name, P.clubID;")) {
                while (resultQuery.next()) {
                    String fName = resultQuery.getString(1);
                    String lName = resultQuery.getString(2);
                    int minutes = resultQuery.getInt(3);
                    int goals = resultQuery.getInt(4);
                    int assists = resultQuery.getInt(5);
                    int cleanSheets = resultQuery.getInt(6);
                    int yellowCards = resultQuery.getInt(7);
                    int redCards = resultQuery.getInt(8);
                    String club = resultQuery.getString(9);
                    JOptionPane.showMessageDialog(null, "Results for: " + name + "\nGeneral informations about " + fName + " " + lName +
                            " (" + club + "):\nOn the pitch: " + minutes + " minutes.\nGoals & Assists: " + goals + " goal(s) & " + assists + " assist(s).\n" +
                            "Clean sheets: " + cleanSheets + ".\nCards: " + yellowCards + " yellow & " + redCards + " red card(s).");
                }
            }
        } catch (SQLException exception) {
            System.out.println("Can not process query in database");
            exception.printStackTrace();
        }
    }

    public static void getInfoAboutTeam(Connection connection, String name) throws SQLException {
        Statement statement = connection.createStatement();
        System.out.println();
        ResultSet clubInfo = statement.executeQuery("SELECT * FROM Clubs WHERE clubID = '" + name + "';");
        while(clubInfo.next()) {
            String id = clubInfo.getString("clubID");
            String fullName = clubInfo.getString("name");
            String stadiumName = clubInfo.getString("stadium_name");
            int capacity = clubInfo.getInt("stadium_capacity");
            System.out.println("General informations about: " + fullName);
            System.out.println("ID: " + id);
            System.out.println("Stadium name: " + stadiumName + " (" + capacity + " seats).");
        }
        clubInfo = statement.executeQuery("SELECT * FROM Matches WHERE hostID = '" + name + "' ORDER BY gameweek;");
        System.out.println("Results as a host: ");
        while(clubInfo.next()) {
            System.out.println("Gameweek " + clubInfo.getInt(1) + ", match: " + clubInfo.getString(2) + ", result: " + clubInfo.getInt(6) + "-" +
                    clubInfo.getInt(7) + ", attendance: " + clubInfo.getInt(9) + ".");
        }
        System.out.println("Result as a guest:");
        clubInfo = statement.executeQuery("SELECT * FROM Matches WHERE guestID = '" + name + "' ORDER BY gameweek;");
        while(clubInfo.next()) {
            System.out.println("Gameweek " + clubInfo.getInt(1) + ", match: " + clubInfo.getString(2) + ", result: " + clubInfo.getInt(6) + "-" +
                    clubInfo.getInt(7) + ".");
        }
    }

}

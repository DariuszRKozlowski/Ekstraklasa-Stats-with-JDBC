package presentation;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import application.PlayerStats;
import application.Team;
import application.EkstraklasaTable;

public class Menu {
    public static void appControl(Connection connection) throws SQLException {
        String[] navigationPanel = new String[] {"Create report", "Show table" ,"Exit"};
        int init = 0;
        loop:
        while(init != 2) {
            init = JOptionPane.showOptionDialog(null, "What do you want to do?", "Ekstraklasa Database", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, navigationPanel, navigationPanel[0]);
            if(init == 0) {
                int reportKind = Menu.chooseReportOption();
                switch(reportKind) {
                    case 0 -> {
                        String answer = JOptionPane.showInputDialog(null, "Please enter player last name");
                        PlayerStats.getInfoAboutPlayer(connection, answer);
                    }
                    case 1 -> {
                        String answer = JOptionPane.showInputDialog(null, "Please enter club ID (3 characters)");
                        PlayerStats.getInfoAboutTeam(connection, answer);
                    }
                    case 2 -> {

                    }
                }



            }
            else if(init == 1) {
                List<Team> teams = EkstraklasaTable.createTeamsFromQuery(connection);
                Team.sumEachTeamPoints(teams, connection);
                EkstraklasaTable.createConsoleTable(teams);
            }
            else if(init == 2){
                JOptionPane.showMessageDialog(null, "Goodbye!");
            }
        }
    }

    private static int chooseReportOption() {
        String[] options = new String[] {"Player", "Club", "Cancel"};
        int response = JOptionPane.showOptionDialog(null, "What kind of informations do you want to know?", "Create report", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null,options, options[0]);
        return response;
    }

}

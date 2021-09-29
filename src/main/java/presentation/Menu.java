package presentation;

import application.Club;
import dao.StatisticDAOImplementation;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class Menu {

    public void mainMenu() throws SQLException, IOException {
       String[] options = {"Clubs", "Coaches", "Matches", "Players", "Referees", "Statistics", "Exit"};
       int result = JOptionPane.showOptionDialog(null, "What do you need to check?", "Main menu",
               JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[options.length-1]);
       switch (result) {
           case 0 -> {
               clubMenu();
           }
           case 1 -> {
               coachMenu();
           }
           case 2 -> {
               matchMenu();
           }
           case 3 -> {
               playerMenu();
           }
           case 4 -> {
               refereeMenu();
           }
           case 5 -> {
               statisticMenu();
           }
           case 6 -> {
               JOptionPane.showMessageDialog(null, "Goodbye!");
               System.exit(0);
           }
       }
    }

    private void clubMenu() throws SQLException, IOException {
        ClubOption clubOption = new ClubOption();
        String[] clubOptions = {"Display all", "Info about club", "Display club players", "Display club coach", "Display club results", "Cancel"};
        int result = JOptionPane.showOptionDialog(null, "Choose the right option", "Club menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, clubOptions, clubOptions[clubOptions.length-1]);
        switch (result) {
            case 0 -> {
                clubOption.displayAll();
            }
            case 1 -> {
                String club = JOptionPane.showInputDialog("Enter ClubID or name");
                clubOption.displaySpecificOne(club);
            }
            case 2 -> {
                String club = JOptionPane.showInputDialog("Entry ClubID or name");
                clubOption.displayClubPlayers(club);
            }
            case 3 -> {
                String club = JOptionPane.showInputDialog("Entry ClubID or name");
                clubOption.displayClubCoach(club);
            }
            case 4 -> {
                String club = JOptionPane.showInputDialog("Entry ClubID or name");
                clubOption.displayClubResults(club);
            }
            case 5 -> {

            }
        }
    }

    private void coachMenu() throws SQLException, IOException {
        CoachOption coachOption = new CoachOption();
        String[] coachOptions = {"Display all", "Info about coach", "Cancel"};
        int result = JOptionPane.showOptionDialog(null, "Choose the right option", "Coach menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, coachOptions, coachOptions[coachOptions.length-1]);
        switch (result) {
            case 0 -> {
                coachOption.displayAll();
            }
            case 1 -> {
                String coach = JOptionPane.showInputDialog("Enter one of them: CoachID, ClubID  or Coach last name");
                int value;
                try {
                    value = Integer.parseInt(coach);
                    coachOption.displaySpecificOne(value);
                } catch (NumberFormatException exc) {
                    coachOption.displaySpecificOne(coach);
                }
            }
            case 2 -> {

            }
        }
    }

    private void matchMenu() throws SQLException, IOException {
        MatchOption matchOption = new MatchOption();
        String[] matchOptions = {"Display all", "Info about match", "Gameweek results", "Matches by referee", "Club results", "Cancel"};
        int result = JOptionPane.showOptionDialog(null, "Choose the right option", "Match menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, matchOptions, matchOptions[matchOptions.length-1]);
        switch (result) {
            case 0 -> {
                matchOption.displayAll();
            }
            case 1 -> {
                String match = JOptionPane.showInputDialog("Enter MatchID");
                matchOption.displaySpecificOne(match);
            }
            case 2 -> {
                String match = JOptionPane.showInputDialog("Enter gameweek number");
                try {
                    int value = Integer.parseInt(match);
                    matchOption.displayWholeGameweek(value);
                } catch(NumberFormatException exc) {
                    JOptionPane.showMessageDialog(null,"Input is not number!");
                }
            }
            case 3 -> {
                String match = JOptionPane.showInputDialog("Entry referee id or last name");
                try {
                    int value = Integer.parseInt(match);
                    matchOption.displayByReferee(value);
                } catch(NumberFormatException exc) {
                    matchOption.displayByReferee(match);
                }
            }
            case 4 -> {
                String match = JOptionPane.showInputDialog("Entry club id or name");
                matchOption.displayByClub(match);
            }
            case 5 -> {

            }
        }
    }

    private void playerMenu() throws SQLException, IOException {
        PlayerOption playerOption = new PlayerOption();
        String[] playerOptions = {"Display all", "Info about player", "Players by position", "Cancel"};
        int result = JOptionPane.showOptionDialog(null, "Choose the right option", "Match menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, playerOptions, playerOptions[playerOptions.length - 1]);
        switch (result) {
            case 0 -> {
                playerOption.displayAll();
            }
            case 1 -> {
                String player = JOptionPane.showInputDialog("Entry player id or last name");
                playerOption.displaySpecificOne(player);
            }
            case 2 -> {
                String player = JOptionPane.showInputDialog("Entry position (for example: \"Goalkeeper\" ");
                playerOption.displaySamePositionPlayers(player);
            }
            case 3 -> {

            }
        }
    }

    private void refereeMenu() throws SQLException, IOException {
        RefereeOption refereeOption = new RefereeOption();
        String[] refereeOptions = {"Display all", "Info about referee", "Cancel"};
        int result = JOptionPane.showOptionDialog(null, "Choose the right option", "Referee menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, refereeOptions, refereeOptions[refereeOptions.length-1]);
        switch (result) {
            case 0 -> {
                refereeOption.displayAll();
            }
            case 1 -> {
                String referee = JOptionPane.showInputDialog("Entry referee id or last name");
                try {
                    int value = Integer.parseInt(referee);
                    refereeOption.displaySpecificOne(value);
                } catch (NumberFormatException exc) {
                    refereeOption.displaySpecificOne(referee);
                }
            }
            case 2 -> {

            }
        }
    }

    private void statisticMenu() throws SQLException, IOException {
        StatisticOption statisticOption = new StatisticOption();
        String[] statisticOptions = {"Display all", "Info about statistic", "Statistics by Player", "Statistics by Match", "Cancel"};
        int result = JOptionPane.showOptionDialog(null, "Choose the right option", "Statistic menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, statisticOptions, statisticOptions[statisticOptions.length-1]);
        switch (result) {
            case 0 -> {
                statisticOption.displayAll();
            }
            case 1 -> {
                String statistic = JOptionPane.showInputDialog("Enter statistic id (for example: \"LGD-RAD:LGD#01\")");
                statisticOption.displaySpecificOne(statistic);
            }
            case 2 -> {
                String statistic = JOptionPane.showInputDialog("Enter player id or last name");
                statisticOption.displaySpecificPlayerStatistics(statistic);
            }
            case 3 -> {
                String statistic = JOptionPane.showInputDialog("Enter match id (for example: \"LEG-WIS\"");
                statisticOption.displaySpecificMatchStatistics(statistic);
            }
            case 4 -> {

            }
        }
    }
}

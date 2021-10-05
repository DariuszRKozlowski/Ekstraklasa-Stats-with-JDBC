package presentation;

import application.Club;
import dao.StatisticDAOImplementation;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class Menu {

    public void mainMenu() throws SQLException, IOException {
       String[] options = {"Clubs", "Coaches", "Matches", "Players", "Referees", "Statistics", "Exit"};
       int result = JOptionPane.showOptionDialog(null, "What do you need to check?", "Ekstraklasa Database",
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
        int result = JOptionPane.showOptionDialog(null, "Choose the right option", "Ekstraklasa Database (Club)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, clubOptions, clubOptions[clubOptions.length-1]);
        switch (result) {
            case 0 -> {
                clubOption.displayAll();
                clubMenu();
            }
            case 1 -> {
                String club = JOptionPane.showInputDialog("Enter Club ID or name");
                if(club != null) {
                    clubOption.displaySpecificOne(club);
                }
                clubMenu();
            }
            case 2 -> {
                String club = JOptionPane.showInputDialog("Enter Club ID or name");
                if(club != null) {
                    clubOption.displayClubPlayers(club);
                }
                clubMenu();
            }
            case 3 -> {
                String club = JOptionPane.showInputDialog("Enter Club ID or name");
                if(club != null) {
                    clubOption.displayClubCoach(club);
                }
                clubMenu();
            }
            case 4 -> {
                String club = JOptionPane.showInputDialog("Enter Club ID or name");
                if(club != null) {
                    clubOption.displayClubResults(club);
                }
                clubMenu();
            }
            case 5 -> {
                mainMenu();
            }
        }
    }

    private void coachMenu() throws SQLException, IOException {
        CoachOption coachOption = new CoachOption();
        String[] coachOptions = {"Display all", "Info about coach", "Cancel"};
        int result = JOptionPane.showOptionDialog(null, "Choose the right option", "Ekstraklasa Database (Coach)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, coachOptions, coachOptions[coachOptions.length-1]);
        switch (result) {
            case 0 -> {
                coachOption.displayAll();
                coachMenu();
            }
            case 1 -> {
                String coach = JOptionPane.showInputDialog("Enter one of them: Coach ID, Club ID  or Coach last name");
                if(coach != null) {
                    int value;
                    try {
                        value = Integer.parseInt(coach);
                        coachOption.displaySpecificOne(value);
                    } catch (NumberFormatException exc) {
                        coachOption.displaySpecificOne(coach);
                    }
                }
                coachMenu();
            }
            case 2 -> {
                mainMenu();
            }
        }
    }

    private void matchMenu() throws SQLException, IOException {
        MatchOption matchOption = new MatchOption();
        String[] matchOptions = {"Display all", "Info about match", "Gameweek results", "Matches by referee", "Club results", "Cancel"};
        int result = JOptionPane.showOptionDialog(null, "Choose the right option", "Ekstraklasa Database (Match)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, matchOptions, matchOptions[matchOptions.length-1]);
        switch (result) {
            case 0 -> {
                matchOption.displayAll();
                matchMenu();
            }
            case 1 -> {
                String match = JOptionPane.showInputDialog("Enter Match ID");
                if(match != null)  {
                    matchOption.displaySpecificOne(match);
                }
                matchMenu();
            }
            case 2 -> {
                String match = JOptionPane.showInputDialog("Enter gameweek number");
                if(match != null) {
                    try {
                        int value = Integer.parseInt(match);
                        matchOption.displayWholeGameweek(value);
                    } catch(NumberFormatException exc) {
                        JOptionPane.showMessageDialog(null,"Input is not number!");
                    }
                }
                matchMenu();
            }
            case 3 -> {
                String match = JOptionPane.showInputDialog("Enter referee ID or last name");
                if(match != null) {
                    try {
                        int value = Integer.parseInt(match);
                        matchOption.displayByReferee(value);
                    } catch(NumberFormatException exc) {
                        matchOption.displayByReferee(match);
                    }
                }
                matchMenu();
            }
            case 4 -> {
                String match = JOptionPane.showInputDialog("Entry club ID or name");
                if(match != null) {
                    matchOption.displayByClub(match);
                }
                matchMenu();
            }
            case 5 -> {
                mainMenu();
            }
        }
    }

    private void playerMenu() throws SQLException, IOException {
        PlayerOption playerOption = new PlayerOption();
        String[] playerOptions = {"Display all", "Info about player", "Players by position", "Cancel"};
        int result = JOptionPane.showOptionDialog(null, "Choose the right option", "Ekstraklasa Database (Player)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, playerOptions, playerOptions[playerOptions.length - 1]);
        switch (result) {
            case 0 -> {
                playerOption.displayAll();
                playerMenu();
            }
            case 1 -> {
                String player = JOptionPane.showInputDialog("Enter player ID or last name");
                if(player != null) {
                    playerOption.displaySpecificOne(player);
                }
                playerMenu();
            }
            case 2 -> {
                String player = JOptionPane.showInputDialog("Enter position (for example: \"Goalkeeper\" ");
                if(player != null) {
                    playerOption.displaySamePositionPlayers(player);
                }
                playerMenu();
            }
            case 3 -> {
                mainMenu();
            }
        }
    }

    private void refereeMenu() throws SQLException, IOException {
        RefereeOption refereeOption = new RefereeOption();
        String[] refereeOptions = {"Display all", "Info about referee", "Cancel"};
        int result = JOptionPane.showOptionDialog(null, "Choose the right option", "Ekstraklasa Database (Referee)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, refereeOptions, refereeOptions[refereeOptions.length-1]);
        switch (result) {
            case 0 -> {
                refereeOption.displayAll();
                refereeMenu();
            }
            case 1 -> {
                String referee = JOptionPane.showInputDialog("Enter referee ID or last name");
                if(referee != null) {
                    try {
                        int value = Integer.parseInt(referee);
                        refereeOption.displaySpecificOne(value);
                    } catch (NumberFormatException exc) {
                        refereeOption.displaySpecificOne(referee);
                    }
                }
                refereeMenu();
            }
            case 2 -> {
                mainMenu();
            }
        }
    }

    private void statisticMenu() throws SQLException, IOException {
        StatisticOption statisticOption = new StatisticOption();
        String[] statisticOptions = {"Display all", "Info about statistic", "Statistics by Player", "Statistics by Match", "Cancel"};
        int result = JOptionPane.showOptionDialog(null, "Choose the right option", "Ekstraklasa Database (Statistic)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, statisticOptions, statisticOptions[statisticOptions.length-1]);
        switch (result) {
            case 0 -> {
                statisticOption.displayAll();
                statisticMenu();
            }
            case 1 -> {
                String statistic = JOptionPane.showInputDialog("Enter statistic ID (for example: \"LGD-RAD:LGD#01\")");
                if(statistic != null) {
                    statisticOption.displaySpecificOne(statistic);
                }
                statisticMenu();
            }
            case 2 -> {
                String statistic = JOptionPane.showInputDialog("Enter player ID or last name");
                if(statistic != null) {
                    statisticOption.displaySpecificPlayerStatistics(statistic);
                }
                statisticMenu();
            }
            case 3 -> {
                String statistic = JOptionPane.showInputDialog("Enter match ID (for example: \"LEG-WIS\"");
                if(statistic != null) {
                    statisticOption.displaySpecificMatchStatistics(statistic);
                }
                statisticMenu();
            }
            case 4 -> {
                mainMenu();
            }
        }
    }
}

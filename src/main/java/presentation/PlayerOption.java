package presentation;

import application.Player;
import dao.ClubDAOImplementation;
import dao.PlayerDAOImplementation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerOption implements OptionInterface {

    private final PlayerDAOImplementation playerDAO = new PlayerDAOImplementation();
    private final ClubDAOImplementation clubDAO = new ClubDAOImplementation();

    @Override
    public void displayAll() throws SQLException, IOException {
        List<Player> listOfPlayers = playerDAO.getAll();
        if(listOfPlayers.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Can not find any player in database!");
        }
        else {
            StringBuilder message = new StringBuilder();
            for (Player player: listOfPlayers) {
                message.append("Club: ").append(player.getClubId().getName()).append(" | Name: ").append(player.getfName())
                        .append(" ").append(player.getlName()).append(" | Birthdate: ").append(player.getBirthDate().toString())
                        .append(" | Nationality: ").append(player.getNationality()).append(" | Nominal position: ")
                        .append(player.getNominalPosition()).append("\n");
            }
            JTextArea textArea = new JTextArea(message.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(900, 900));
            textArea.setSize(new Dimension(900,700));
            JOptionPane.showMessageDialog(null, scrollPane, "Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void displaySpecificOne(String input) throws SQLException, IOException {
        Player player = playerDAO.filterById(input);
        List<Player> listOfPlayers = playerDAO.filterByLastName(input);
        if(listOfPlayers.isEmpty()) {
            if(player == null) {
                JOptionPane.showMessageDialog(null, "Can not find " + input + " in database!");
            }
            else {
                StringBuilder message = new StringBuilder();
                message.append("ID: ").append(player.getPlayerId()).append(" | Club: ").append(player.getClubId().getName()).append(" | Name: ").append(player.getfName())
                        .append(" ").append(player.getlName()).append(" | Birthdate: ").append(player.getBirthDate().toString())
                        .append(" | Nationality: ").append(player.getNationality()).append(" | Nominal position: ")
                        .append(player.getNominalPosition()).append(" | Contract validity: ").append(player.getContractValidity().toString());
                JOptionPane.showMessageDialog(null, message);
            }
        }
        else {
            for (Player playerFromList : listOfPlayers) {
                StringBuilder message = new StringBuilder();
                message.append("ID: ").append(playerFromList.getPlayerId()).append(" | Club: ").append(playerFromList.getClubId().getName()).append(" | Name: ").append(playerFromList.getfName())
                        .append(" ").append(playerFromList.getlName()).append(" | Birthdate: ").append(playerFromList.getBirthDate().toString())
                        .append(" | Nationality: ").append(playerFromList.getNationality()).append(" | Nominal position: ")
                        .append(playerFromList.getNominalPosition()).append(" | Contract validity: ").append(playerFromList.getContractValidity().toString());
                JOptionPane.showMessageDialog(null, message);
            }
        }
    }

    public void displaySamePositionPlayers(String position) throws SQLException, IOException {
        List<Player> listOfPlayers = playerDAO.getAll();
        List<Player> samePositionList = new ArrayList<>();
        for (Player player: listOfPlayers) {
            if(player.getNominalPosition().toLowerCase().equalsIgnoreCase(position)) {
                samePositionList.add(player);
            }
        }
        if(samePositionList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Can not find any player with nominal position: " + position);
        }
        else {
            StringBuilder message = new StringBuilder();
            message.append("Results for: ").append(position).append(":\n");
            for (Player player: samePositionList) {
                message.append("ID: ").append(player.getPlayerId()).append(" | Club: ").append(player.getClubId().getName()).append(" | Name: ").append(player.getfName())
                        .append(" ").append(player.getlName()).append(" | Birthdate: ").append(player.getBirthDate().toString())
                        .append(" | Nationality: ").append(player.getNationality()).append("\n");
            }
            JTextArea textArea = new JTextArea(message.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(900, 900));
            textArea.setSize(new Dimension(900,700));
            JOptionPane.showMessageDialog(null, scrollPane, "Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

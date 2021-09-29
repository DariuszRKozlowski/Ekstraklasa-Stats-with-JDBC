package presentation;

import application.Referee;
import dao.RefereeDAOImplementation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RefereeOption implements OptionInterface {

    RefereeDAOImplementation refereeDAO = new RefereeDAOImplementation();

    @Override
    public void displayAll() throws SQLException, IOException {
        List<Referee> listOfReferees = refereeDAO.getAll();
        if(listOfReferees.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Can not find referees in database!");
        }
        else {
            StringBuilder message = new StringBuilder();
            for (Referee referee: listOfReferees) {
                message.append("ID: ").append(referee.getId()).append(" | Name: ").append(referee.getfName())
                        .append(" ").append(referee.getlName()).append("\n");
            }
            displayOnScreen(message, "All referees");
        }
    }

    @Override
    public void displaySpecificOne(String input) throws SQLException, IOException {
        List<Referee> refereeByName = refereeDAO.filterByLastName(input);
        if(refereeByName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Can not find \n" + input + "\n in database");
        }
        else {
            StringBuilder message = new StringBuilder();
            for (Referee referee: refereeByName) {
                message.append("ID: ").append(referee.getId()).append(" | Name: ").append(referee.getfName())
                        .append(" ").append(referee.getlName()).append("\n");
            }
            displayOnScreen(message, "Results for " + input);
        }
    }

    public void displaySpecificOne(int input) throws SQLException, IOException {
        Referee refereeById = refereeDAO.filterById(input);
        if(refereeById == null) {
            JOptionPane.showMessageDialog(null, "Can not find referee with id= "+input+" in database!");
        }
        else {
            StringBuilder message = new StringBuilder();
            message.append("ID: ").append(refereeById.getId()).append(" | Name: ").append(refereeById.getfName())
                    .append(" ").append(refereeById.getlName()).append("\n");
            displayOnScreen(message, "Result for " + input);
        }
    }

    private void displayOnScreen(StringBuilder message, String title) {
        JTextArea textArea = new JTextArea(message.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(1100, 800));
        textArea.setSize(new Dimension(1000,700));
        JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
    }
}

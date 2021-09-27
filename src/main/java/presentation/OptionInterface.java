package presentation;

import java.io.IOException;
import java.sql.SQLException;

public interface OptionInterface {

    void displayAll() throws SQLException, IOException;

    void displaySpecificOne(String input) throws SQLException, IOException;
}

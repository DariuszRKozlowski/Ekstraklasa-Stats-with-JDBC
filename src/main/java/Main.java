import infrastructure.DatabaseConnector;
import presentation.Menu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        // Connection with Database
        Connection dbConnection  = DatabaseConnector.connectToDatabase();

        //Menu
        Menu.appControl(dbConnection);

    }
}

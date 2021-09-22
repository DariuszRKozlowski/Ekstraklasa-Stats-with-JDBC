import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static String url = "jdbc:sqlserver://DESKTOP-57U12SM\\sqlexpress;databaseName=Ekstraklasa 21/22";
    private static String username = "Ekstraklasa-Admin";
    private static String password = "SQL_ekstraklasa21";

    public static Connection connectToDatabase() {
        Connection result = null;
        try {
            result = DriverManager.getConnection(url, username, password);
            System.out.println("Successfully connection to database!");
        } catch (SQLException exc) {
            System.out.println("Can not connect to database: " + url +", with username \"" + username + "\" & password \"" + password + "\"" +
                    "\nCheck parameters and try again.");
            exc.printStackTrace();
        }
        return result;
    }
}

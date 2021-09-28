package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {

    private static String url;
    private static String username;
    private static String password;

    private static void getDatabaseProperties() throws IOException {
        Properties p = new Properties();
        InputStream input = new FileInputStream("database.properties");
        p.load(input);
        url = p.getProperty("url");
        username = p.getProperty("username");
        password = p.getProperty("password");
    }

    public static Connection connectToDatabase() throws IOException {
        DatabaseConnector.getDatabaseProperties();
        Connection result = null;
        try {
            result = DriverManager.getConnection(url, username, password);
        } catch (SQLException exc) {
            System.out.println("Can not connect to database: " + url +", with username \"" + username + "\" & password \"" + password + "\"" +
                    "\nCheck parameters and try again.");
            exc.printStackTrace();
        }
        return result;
    }
}

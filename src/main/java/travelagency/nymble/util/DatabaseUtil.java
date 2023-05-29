package travelagency.nymble.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections.
 */
public class DatabaseUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/travelagency";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    /**
     * Retrieves a database connection.
     *
     * @return Connection object representing the database connection
     * @throws SQLException if an error occurs while establishing the connection
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}

package in.test.backend.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtils {
    private static Connection connection;

    public static Connection connectToDB(DBHost dbHost, String dbName) {
        try {
            // Close previous connection if open
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }

            // Load driver based on DB type
            String driver = ConfigManager.get(dbHost.getDriverKey());
            Class.forName(driver);

            // Get DB URL with dbName injected
            String dbUrl = dbHost.getDbHostname(dbName);

            // Get credentials based on DB type
            String username = ConfigManager.get(dbHost.getUsernameKey());
            String password = ConfigManager.get(dbHost.getPasswordKey());

            // Establish new connection
            connection = DriverManager.getConnection(dbUrl, username, password);
            return connection;

        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to database: " + dbName, e);
        }
    }



    public static List<Map<String, Object>> executeQuery(String query) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> rowMap = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowMap.put(metaData.getColumnLabel(i), rs.getObject(i));
                }
                resultList.add(rowMap);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query execution failed", e);
        }
        return resultList;
    }

    public static int executeUpdate(String query) {
        try (Statement stmt = connection.createStatement()) {
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException("Update execution failed", e);
        }
    }

    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to close DB connection", e);
        }
    }
}
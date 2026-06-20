package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDb {

    public static void main(String[] args) {
        MySqlConnector connector = new MySqlConnector();
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = connector.openConnection();
            if (conn == null) {
                System.out.println("Failed to obtain connection.");
                return;
            }

            // Safe query with backticked alias
            String testQuery = "SELECT NOW() AS `current_time`";
            rs = connector.runQuery(conn, testQuery);
            if (rs != null && rs.next()) {
                System.out.println("Database time: " + rs.getString("current_time"));
            } else {
                System.out.println("Query returned no results.");
            }

        } catch (Exception e) {
            System.err.println("An error occurred:");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) { /* ignore */ }
            connector.closeConnection(conn);
        }
    }
}
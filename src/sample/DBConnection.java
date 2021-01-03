package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.Instant;
import java.io.IOException;
import java.sql.SQLException;

public class DBConnection {
    private static final String databaseName = "WJ07qPv";
    private static final String DB_URL = "jdbc:mysql://wgudb.ucertify.com:3306/" + databaseName;
    private static final String username = "U07qPv";
    private static final String password = "53689104949";
    public static Connection conn;

    public static Connection makeConnection() throws ClassNotFoundException, SQLException, Exception{
            try {
                conn = DriverManager.getConnection(DB_URL, username, password);
            } catch(SQLException e) {
                throw new SQLException(e.getMessage());
            } catch (Exception x) {
                throw new Exception(x.getMessage());
            }
            System.out.println("Connection successful.");
            return conn;
    }

    public static void closeConnection() throws SQLException{
        conn.close();
        System.out.println("Connection closed.");
    }
}

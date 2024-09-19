package Bus_reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Wrapper;

public class DbConnection {
    private static final String url="jdbc:mysql://localhost:3306/busresv";
    private static final String userName="root";
    private static final String password="Gokul@727";

    public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection(url,userName,password);
    }
}

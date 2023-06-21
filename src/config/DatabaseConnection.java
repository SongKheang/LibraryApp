package Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection conn() {
        try {
            String url = "jdbc:mysql://localhost:3306/library_app";
            String username = "root";
            String password = "";
            return DriverManager.getConnection(url, username, password);
            
        } catch (Exception e) {
            System.out.println("Connection unsuccessful...");
            System.out.println(e);
            return null;
        }
    };

}

package API;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Config.DatabaseConnection;
import Controllers.Services;

public class StudentSignInAPI {
    public Connection conn;
    public PreparedStatement stmt;
    public ResultSet rs;
    public Services services = new Services();

    public StudentSignInAPI() {
        conn = DatabaseConnection.conn();
    }

    public void insertStudent(Students students) {
        try {
            String sql = "INSERT INTO students VALUE (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, students.getStudentID());
            stmt.setString(2, students.getStudentName());
            stmt.setString(3, students.getStudentPhone());
            stmt.setString(4, students.getStudentPassword());

            int row = stmt.executeUpdate();
            if(row >= 1) {
                services.alertSuccess("Register succesfully ....");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

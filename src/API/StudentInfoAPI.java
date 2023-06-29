package API;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Config.DatabaseConnection;
import Controllers.Services;

public class StudentInfoAPI {
    public Connection conn;
    public PreparedStatement stmt;
    public ResultSet rs;
    Services services = new Services();

    public StudentInfoAPI() {
        conn = DatabaseConnection.conn();
    }

    public Students getStudentInfo(String studentID) {
        try {
            String sql = "Select * From students Where studentID = '" + studentID + "'";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
                return new Students(rs.getString("studentID"), rs.getString("studentName"), rs.getString("studentPhone"), rs.getString("studentPassword"));
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void updateStudentInfo(Students student) {
        try {
            String sql = "UPDATE students SET studentName = '" +student.getStudentName() + "',studentPhone = '"+ student.getStudentPhone() + "', studentPassword = '" + student.getStudentPassword() +"' Where studentID = '" + student.getStudentID() + "'";
            stmt = conn.prepareStatement(sql);
            int row = stmt.executeUpdate();
            if(row == 1) {
                services.alertSuccess("Your Info Update Successfully ...");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

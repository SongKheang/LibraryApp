package API;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Config.DatabaseConnection;
import Controllers.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminInfoAPI {
    public Connection conn;
    public PreparedStatement stmt;
    public ResultSet rs;
    public Students students;

    Services services = new Services();
    private ObservableList<Students> studentList;

    public void setStudentList(ObservableList<Students> studentList) {
        this.studentList = studentList;
    }

    public AdminInfoAPI() {
        conn = DatabaseConnection.conn();
        studentList = FXCollections.observableArrayList();
    }

    public ObservableList<Students> getAdminList() {
        studentList.clear();
        try {
            String sql = "Select * from admins";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
                String adminID = rs.getString("adminID");
                String adminName = rs.getString("adminName");
                String adminPhone = rs.getString("adminPhone");
                studentList.add(new Students(adminID, adminName, adminPhone));
            }
            return studentList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void deleteAdmin(String adminID) {
        try {
            String sql = "Delete from admins where adminID = '" + adminID + "'";
            stmt = conn.prepareStatement(sql);
            int row = stmt.executeUpdate();
            if( row > 0) {
                services.alertSuccess("Delete successfully ...");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ObservableList<Students> getStudentList(String searchText) {
        studentList.clear();
        try {
            String sql = "Select * from students";
            if(searchText != "") {
                sql += " WHERE studentName LIKE '%" + searchText + "%' OR studentID LIKE '%" + searchText + "%'";
            }
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
                String studentID = rs.getString("studentID");
                String studentName = rs.getString("studentName");
                String studentPhone = rs.getString("studentPhone");
                studentList.add(new Students(studentID, studentName, studentPhone));
            }
            return studentList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void deleteStudent(String studentID) {
        try {
            String sql = "Delete from students where studentID = '" + studentID + "'";
            stmt = conn.prepareStatement(sql);
            int row = stmt.executeUpdate();
            if( row > 0) {
                services.alertSuccess("Delete successfully ...");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void resetStudentPassword(String studentID) {
        try {
            String sql = "Update students SET studentPassword = '"+ studentID +"' where studentID = '" + studentID + "'";
            stmt = conn.prepareStatement(sql);
            int row = stmt.executeUpdate();
            if( row > 0) {
                services.alertSuccess("Password reset successfully ...");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

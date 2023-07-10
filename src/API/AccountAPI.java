package API;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Config.DatabaseConnection;

public class AccountAPI {
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    public AccountAPI() {
        try {
            conn = DatabaseConnection.conn();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void registerStudent(String studentID, String studentName, String studentPhone, String studentPassword) {
        try {
            String insertSql = "INSERT INTO students(studentID, studentName, studentPhone, studentPassword) VALUES (?,?,?,?)";
            stmt = conn.prepareStatement(insertSql);
            stmt.setString(1, studentID);
            stmt.setString(2, studentName);
            stmt.setString(3, studentPhone);
            stmt.setString(4, studentPassword);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void registerAdmin(String adminID, String adminName, String adminPhone, String adminPassword) {
        try {
            String insertSql = "INSERT INTO admins(adminID, adminName, adminPhone, adminPassword) VALUES (?,?,?,?)";
            stmt = conn.prepareStatement(insertSql);
            stmt.setString(1, adminID);
            stmt.setString(2, adminName);
            stmt.setString(3, adminPhone);
            stmt.setString(4, adminPassword);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean isLogedIn(String table, String ID, String password) {
        String columnID = "", columnPassword = "";
        if(table.equals("admins")) {
            columnID = "adminID";
            columnPassword = "adminPassword";
        } else if(table.equals("students")) {
            columnID = "studentID";
            columnPassword = "studentPassword";
        }
        try {
            String searchSQL = "SELECT * FROM " + table + " WHERE " + columnID + " = '" + ID + "' AND " + columnPassword + " = '" + password + "'";
            stmt = conn.prepareStatement(searchSQL);
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public boolean existID(String table, String columnID, String ID, String columnPhone, String phone) {
        try {
            String searchSQL = "SELECT * FROM " + table + " WHERE " + columnID + " = '" + ID + "' or " + columnPhone + " = '" + phone + "'";
            stmt = conn.prepareStatement(searchSQL);
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean isAdminExist() {
        try {
            String searchSQL = "SELECT * FROM admins";
            stmt = conn.prepareStatement(searchSQL);
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }



    




}

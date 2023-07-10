package API;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import Config.DatabaseConnection;
import Controllers.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminBorrowListAPI {
    public Connection conn;
    public PreparedStatement stmt;
    public ResultSet rs;
    public Borrows borrows;

    Services services = new Services();
    private ObservableList<Borrows> borrowsList;

    public ObservableList<Borrows> getBorrowsList() {
        return borrowsList;
    }

    public void setBorrowsList(ObservableList<Borrows> borrowsList) {
        this.borrowsList = borrowsList;
    }

    public AdminBorrowListAPI() {
        conn = DatabaseConnection.conn();
        borrowsList = FXCollections.observableArrayList();
    }

    public ObservableList<Borrows> getBorrowList(String condition, String condition2, String returnCombo) {
        borrowsList.clear();
        String sql = "SELECT books.bookID, books.title, borrowlist.borrower, students.studentPhone, borrowlist.borrowDate, borrowlist.returnDate, borrowlist.isReturned FROM students JOIN borrowlist ON students.studentID = borrowlist.borrower JOIN books ON books.bookID = borrowlist.bookID";
        if (condition != "") {
            sql += condition;
        }

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int bookID = rs.getInt("bookID");
                String title = rs.getString("title");
                String borrower = rs.getString("borrower");
                String phoneNumber = rs.getString("studentPhone");
                String borrowDate = rs.getString("borrowDate");
                String returnDate = rs.getString("returnDate");
                String isReturned = rs.getString("isReturned");

                if(isReturned.equals("0")) {
                    isReturned = "No";
                }
                else if(isReturned.equals("1")) {
                    isReturned = "Yes";
                }

                if(isReturned.equals("No") && returnCombo.equals("Late Return") && LocalDate.parse(returnDate).isBefore(LocalDate.now())) {
                    borrowsList.add(new Borrows(bookID, title, borrower, phoneNumber, borrowDate, returnDate, isReturned));
                }
                else if(isReturned.equals(returnCombo)) {
                    borrowsList.add(new Borrows(bookID, title, borrower, phoneNumber, borrowDate, returnDate, isReturned));
                }
                else if(returnCombo.equals("All")){
                    borrowsList.add(new Borrows(bookID, title, borrower, phoneNumber, borrowDate, returnDate, isReturned));
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            String sql2 = "SELECT borroweroutside.bookID, books.title, borroweroutside.name, borroweroutside.phone, borroweroutside.borrowDate, borroweroutside.returnDate, borroweroutside.isReturned FROM books JOIN borroweroutside ON books.bookID = borroweroutside.bookID";
            if (condition2 != "") {
                sql2 += condition2;
            }
            stmt = conn.prepareStatement(sql2);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int bookID = rs.getInt("bookID");
                String title = rs.getString("title");
                String borrower = rs.getString("name");
                String phoneNumber = rs.getString("phone");
                String borrowDate = rs.getString("borrowDate");
                String returnDate = rs.getString("returnDate");
                String isReturned = rs.getString("isReturned");

                if(isReturned.equals("0")) {
                    isReturned = "No";
                }
                else if(isReturned.equals("1")) {
                    isReturned = "Yes";
                }

                if(isReturned.equals("No") && returnCombo.equals("Late Return") && LocalDate.parse(returnDate).isBefore(LocalDate.now())) {
                    borrowsList.add(new Borrows(bookID, title, borrower, phoneNumber, borrowDate, returnDate, isReturned));
                }
                else if(isReturned.equals(returnCombo)) {
                    borrowsList.add(new Borrows(bookID, title, borrower, phoneNumber, borrowDate, returnDate, isReturned));
                }
                else if(returnCombo.equals("All")){
                    borrowsList.add(new Borrows(bookID, title, borrower, phoneNumber, borrowDate, returnDate, isReturned));
                }
            }
            return borrowsList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void returnBook(int bookID, String studentID) {
        try {
            String sql1 = "UPDATE borrowlist SET isReturned = '1' Where bookID = '" + bookID + "' AND borrower = '" + studentID + "'";
            stmt = conn.prepareStatement(sql1);
            int row = stmt.executeUpdate();
            if(row > 0) {
                updateRemain(bookID);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            String sql2 = "UPDATE borroweroutside SET isReturned = '1' Where bookID = '" + bookID + "' AND name = '" + studentID + "'";
            stmt = conn.prepareStatement(sql2);
            int row = stmt.executeUpdate();
            if(row > 0) {
                updateRemain(bookID);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void updateRemain(int bookID) {
        try {
            String sql = "UPDATE books set remain = remain + 1 where bookID = " + bookID;
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

package API;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Config.DatabaseConnection;
import Controllers.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentDashboardAPI {
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

    public StudentDashboardAPI() {
        conn = DatabaseConnection.conn();
        borrowsList = FXCollections.observableArrayList();
    }

    public ObservableList<Borrows> getBorrowList(String studentID, String condition, String returnCombo) {
        borrowsList.clear();
        String sql = "SELECT books.bookID, books.title, books.author, books.bookshelf, borrowlist.borrowDate, borrowlist.returnDate, borrowlist.isReturned FROM borrowlist JOIN books ON books.bookID = borrowlist.bookID WHERE borrowlist.borrower LIKE '" + studentID + "'";
        if (condition != "") {
            sql += condition;
        }
        
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int bookID = rs.getInt("bookID");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String bookshelf = rs.getString("bookshelf");
                String borrowDate = rs.getString("borrowDate");
                String returnDate = rs.getString("returnDate");
                String isReturned = rs.getString("isReturned");

                if(isReturned.equals("0")) {
                    isReturned = "No";
                }
                else if(isReturned.equals("1")) {
                    isReturned = "Yes";
                }

                if(isReturned.equals(returnCombo)) {
                    borrowsList.add(new Borrows(bookID, title, author, bookshelf, borrowDate, returnDate, isReturned, ""));
                }
                else if(returnCombo.equals("All")){
                    borrowsList.add(new Borrows(bookID, title, author, bookshelf, borrowDate, returnDate, isReturned, ""));
                }
            }
            return borrowsList;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}

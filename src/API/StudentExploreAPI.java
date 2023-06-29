package API;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Config.DatabaseConnection;
import Controllers.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentExploreAPI {
    public Connection conn;
    public PreparedStatement stmt;
    public ResultSet rs;
    public Books books;
    Services services = new Services();
    private ObservableList<Books> bookList;

    public ObservableList<Books> getBookList() {
        return bookList;
    }

    public void setBookList(ObservableList<Books> bookList) {
        this.bookList = bookList;
    }

    public StudentExploreAPI() {
        conn = DatabaseConnection.conn();
        bookList = FXCollections.observableArrayList();
        borrowedBookID = FXCollections.observableArrayList();
    }

    public void borrowBook(ObservableList<Books> cartList, String studentID, String borrowDate, String returnDate, String isReturn) {
        String sql = " INSERT INTO borrowlist(bookID, borrower, borrowDate, returnDate, isReturned) VALUES ";
        for(Books books : cartList) {
            int bookID = books.getBookID();
            updateRemain(bookID);
            sql += "('" + bookID +"','" + studentID +"','" + borrowDate +"','" + returnDate +"','" + isReturn +"'),";
        }
        sql = sql.substring(0, sql.length()-1);
        try {
            stmt = conn.prepareStatement(sql);
            int row = stmt.executeUpdate();
            if(row>0) {
                services.alertSuccess("Borrow successfully ...");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateRemain(int bookID) {
        try {
            String sql = "UPDATE books set remain = remain - 1 where bookID = " + bookID;
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getNumberOfBookStudentBorrow(String studentID) {
        int count = 0;
        String sql = "SELECT bookID from borrowlist where borrower LIKE '" + studentID + "' and isReturned = '0'";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
                count++;
                borrowedBookID.add(rs.getInt("bookID"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    private ObservableList<Integer> borrowedBookID;

    public ObservableList<Integer> getBorrowedBookID() {
        return borrowedBookID;
    }

    
}

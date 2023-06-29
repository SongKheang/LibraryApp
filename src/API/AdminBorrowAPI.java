package API;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Config.DatabaseConnection;
import Controllers.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminBorrowAPI {
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

    public AdminBorrowAPI() {
        conn = DatabaseConnection.conn();
        bookList = FXCollections.observableArrayList();
        borrowedBookID = FXCollections.observableArrayList();
    }

    public void borrowBook(ObservableList<Books> cartList, String name, String phone, String borrowDate,
            String returnDate, String isReturn) {
        String sql = " INSERT INTO borroweroutside(name, phone, bookID, borrowDate, returnDate, isReturned) VALUES ";
        for (Books books : cartList) {
            int bookID = books.getBookID();
            updateRemain(bookID);
            sql += "('" + name + "','" + phone + "','" + bookID + "','" + borrowDate + "','" + returnDate + "','"
                    + isReturn + "'),";
        }
        sql = sql.substring(0, sql.length() - 1);
        try {
            stmt = conn.prepareStatement(sql);
            int row = stmt.executeUpdate();
            if (row > 0) {
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

    public int getNumberOfBookBorrowerBorrow(String phone) {
        int count = 0;
        String sql = "SELECT bookID from borroweroutside where phone LIKE '" + phone + "' AND isReturned = '0'";
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

    public ObservableList<Books> searchBooks(String column, String value) {
        ObservableList<Books> list = FXCollections.observableArrayList();
        try {
            String sql = "Select * from books WHERE remain > 0";
            if (!value.equals("")) {
                sql += " And " + column + " LIKE '%" + value + "%'";
            }
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int bookID = rs.getInt("BookID");
                String title1 = rs.getString("title");
                String author = rs.getString("author");
                int year = rs.getInt("year");
                String category = rs.getString("category");
                int page = rs.getInt("page");
                String quality = rs.getString("quality");
                String bookshelf = rs.getString("bookshelf");
                int quantity = rs.getInt("quantity");
                int remain = rs.getInt("remain");

                books = new Books(bookID, title1, author, year, category, page, quality, bookshelf, quantity, remain);
                list.add(books);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}

package API;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Config.DatabaseConnection;
import Controllers.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookListAPI {
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

    public BookListAPI() {
        conn = DatabaseConnection.conn();
        bookList = FXCollections.observableArrayList();
    }

    public ObservableList<Books> searchBooks(String column, String value) {
        ObservableList<Books> list = FXCollections.observableArrayList();
        try {
            String sql = "Select * from books";
            if (!value.equals("")) {
                sql += " WHERE " + column + " LIKE '%" + value + "%'";
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

    public void deleteBook(int bookID) {
        try {
            String sql = "DELETE FROM books WHERE bookID = '" + bookID + "'";
            stmt = conn.prepareStatement(sql);
            int row = stmt.executeUpdate();
            if (row > 0) {
                services.alertSuccess("Delete successfully...");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ObservableList<String> getCategory() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("All");
        String sql = "SELECT * FROM books";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String category = rs.getString("category");
                if(!list.contains(category)) {
                    list.add(category);
                }
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public Books getSelectedBook(int id) {
        try {
            String sql = "Select * from books where bookID = " + id;
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
                String bookCoverPath = rs.getString("bookCoverPath");

                books = new Books(bookID, title1, author, year, category, page, quality, bookshelf, quantity, remain,
                        bookCoverPath);
            }

            return books;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void updateBook(int bookID, Books book) {
        try {
            String sql = "Update books SET title = ?, author = ?, year = ?, category = ?, page = ?, quality = ?, bookshelf = ?, quantity = ?, remain = if(remain + ? < 0, 0, remain + ?), bookCoverPath = ? WHERE bookID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getYear());
            stmt.setString(4, book.getCategory());
            stmt.setInt(5, book.getPage());
            stmt.setString(6, book.getQuality());
            stmt.setString(7, book.getBookshelf());
            stmt.setInt(8, book.getQuantity());
            stmt.setInt(9, book.getRemain());
            stmt.setInt(10, book.getRemain());
            stmt.setString(11, book.getBookCoverPath());
            stmt.setInt(12, bookID);

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean isBorrowed(int bookID) {
        try {
            String sql = "select bookID from books where bookID in (select bookID from borrowlist where bookID = '" + bookID + "' and isReturned like 0) or bookID in (select bookID from borroweroutside where bookID = '" + bookID + "' and isReturned = 0)";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if(rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static void main(String[] args) {
        BookListAPI bb = new BookListAPI();
        System.out.println(bb.isBorrowed(39));
        System.out.println(bb.isBorrowed(38));
    }
}

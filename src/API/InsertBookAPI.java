package API;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Config.DatabaseConnection;

public class InsertBookAPI {
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    public InsertBookAPI() {
        try {
            conn = DatabaseConnection.conn();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void insertBook(Books book) {
        try {   
            String sql = "INSERT INTO books(title, author, year, category, page, quality, bookshelf, quantity, remain, bookCoverPath) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getYear());
            stmt.setString(4, book.getCategory());
            stmt.setInt(5, book.getPage());
            stmt.setString(6, book.getQuality());
            stmt.setString(7, book.getBookshelf());
            stmt.setInt(8, book.getQuantity());
            stmt.setInt(9, book.getQuantity());
            stmt.setString(10, book.getBookCoverPath());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

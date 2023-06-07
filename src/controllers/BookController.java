package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import config.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BookController implements Initializable {
    @FXML
    private TableColumn<Books, String> returnDateCol;

    @FXML
    private TextField returnDateField;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchField;
    @FXML
    private Button updateBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<Books, String> authorCol;

    @FXML
    private TableColumn<Books, String> returnCol;

    @FXML
    private TextField authorField;

    @FXML
    private TableColumn<Books, String> bookIdCol;

    @FXML
    private TextField bookIdField;

    @FXML
    private TableColumn<Books, String> categoryCol;

    @FXML
    private TextField categoryField;

    @FXML
    private Button insertBtn;

    @FXML
    private TableColumn<Books, String> pageCol;

    @FXML
    private TableColumn<Books, String> borrowdateCol;

    @FXML
    private TextField pageField;

    @FXML
    private TableView<Books> tableView;

    @FXML
    private TableColumn<Books, String> titleCol;

    @FXML
    private TextField titleField;

    @FXML
    private TableColumn<Books, String> yearCol;

    @FXML
    private TableColumn<Books, String> StuIdCol;

    @FXML
    private TableColumn<Books, String> StuNameCol;

    @FXML
    private TextField borrowdateField;

    @FXML
    private TextField yearField;

    @FXML
    private Text usernameUser;

    @FXML
    private TextField StuNameField;

    @FXML
    private TextField StuIdField;

    @FXML
    private TextField studentfield;

    @FXML
    void autoGenerate(MouseEvent event) throws SQLException {
        int id = 0;
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM books";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            id = rs.getInt("bookId") + 1;
        }
        String convert = Integer.toString(id);
        bookIdField.setText(convert);
    }

    @FXML
    void handleInsert(ActionEvent event) {
        String title = titleField.getText();
        String author = authorField.getText();
        String year = yearField.getText();
        String page = pageField.getText();
        String category = categoryField.getText();
        String borrowdate = borrowdateField.getText();
        String studentname = StuNameField.getText();
        String studentid = studentfield.getText();
        String returndate = returnDateField.getText();
        if (title == null || title == "" || author == null || author == "" || year == null || year == "" || page == null
                || page == "" || category == null || category == "" || borrowdate == null || borrowdate == ""
                || studentname == null || studentname == "" || studentid == null || studentid == ""
                || returndate == null || returndate == "") {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Fail");
            alert.setHeaderText(null);
            alert.setContentText("Please input all fields!!");
            alert.showAndWait();
            return;
        } else {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String sqlInsert = "INSERT INTO `books`(`title`, `author`, `year`, `page`, `category`, `borrowdate`,`studentname`,`studentid`, `returndate`) VALUES (?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement = conn.prepareStatement(sqlInsert);
                statement.setString(1, title);
                statement.setString(2, author);
                statement.setString(3, year);
                statement.setString(4, page);
                statement.setString(5, category);
                statement.setString(6, borrowdate);
                statement.setString(7, studentname);
                statement.setString(8, studentid);
                statement.setString(9, returndate);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Insert success.");
                    alert.showAndWait();
                    handleClear(event);
                    showBooks();
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Fail");
                    alert.setHeaderText(null);
                    alert.setContentText("Fail insert!!");
                    alert.showAndWait();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Fail");
                alert.setHeaderText(null);
                alert.setContentText("Fail insert!!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void handleClear(ActionEvent event) {
        bookIdField.setText(null);
        authorField.setText(null);
        yearField.setText(null);
        pageField.setText(null);
        categoryField.setText(null);
        titleField.setText(null);
        borrowdateField.setText(null);
        StuNameField.setText(null);
        studentfield.setText(null);
        returnDateField.setText(null);
    }

    @FXML
    void handleUpdate(ActionEvent event) {
        String bookId = bookIdField.getText();
        int id;
        // System.out.println("kdkeicdlfjkwwwwwwwwwwwwww");
        String title = titleField.getText();
        String author = authorField.getText();
        String year = yearField.getText();
        String page = pageField.getText();
        String category = categoryField.getText();
        String borrowdate = borrowdateField.getText();
        String studentname = StuNameField.getText();
        String studentid = studentfield.getText();
        String returndate = returnDateField.getText();
        boolean con = true;
        if (title == null || title == "" || author == null || author == "" || year == null || year == "" || page == null
                || page == "" || category == null || category == "" || borrowdate == null || borrowdate == ""
                || studentname == null || studentname == "" || studentid == null || studentid == ""
                || returndate == null || returndate == "") {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Fail");
            alert.setHeaderText(null);
            alert.setContentText("Please input all fields!!");
            alert.showAndWait();
            return;
        } else {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String sqlSelect = "SELECT * FROM books";
                PreparedStatement statement = conn.prepareStatement(sqlSelect);
                ResultSet rs = statement.executeQuery();
                id = Integer.parseInt(bookId);
                while (rs.next()) {
                    if (rs.getInt("bookid") == id) {
                        String sqlInsert = "UPDATE books SET title= ? ,author= ? ,year= ?,page= ?, category = ?,borrowdate = ?,studentname = ?,studentid=?, rda = ? WHERE bookId= ? ";
                        PreparedStatement statement2 = conn.prepareStatement(sqlInsert);
                        statement2.setString(1, title);
                        statement2.setString(2, author);
                        statement2.setString(3, year);
                        statement2.setString(4, page);
                        statement2.setString(5, category);
                        statement2.setString(6, borrowdate);
                        statement2.setString(7, studentname);
                        statement2.setString(8, studentid);
                        statement2.setString(9, returndate);
                        statement2.setInt(10, id);

                        statement2.executeUpdate();

                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Update success.");
                        alert.showAndWait();
                        con = false;
                        handleClear(event);
                        showBooks();
                    }
                }
                if (con) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("ID not Found!");
                    alert.showAndWait();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Fail");
                alert.setHeaderText(null);
                alert.setContentText("Fail insert!");
                alert.showAndWait();
            }

        }
    }

    @FXML
    void handleDelete(ActionEvent event) {
        String bookId = bookIdField.getText();
        int id = Integer.parseInt(bookId);
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM books WHERE bookId=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                // System.out.println("Record deleted successfully.");
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Record deleted successfully.");
                alert.showAndWait();
                handleClear(event);
                showBooks();
            } else {
                // System.out.println("Record not found.");
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Fail");
                alert.setHeaderText(null);
                alert.setContentText("Fail delete!!.");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            showBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<Books> getBooksList() throws SQLException {
        ObservableList<Books> bookList = FXCollections.observableArrayList();
        try {
            String searchText = searchField.getText();
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM books";
            if (searchText != "") {
                sql += " WHERE title LIKE '%" + searchText + "%'";
            }
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Books books;
            while (resultSet.next()) {
                books = new Books(resultSet.getString("bookId"), resultSet.getString("title"),
                        resultSet.getString("author"), resultSet.getString("year"), resultSet.getString("page"),
                        resultSet.getString("category"), resultSet.getString("borrowDate"),
                        resultSet.getString("studentname"), resultSet.getString("studentid"),
                        resultSet.getString("rda"));
                System.out.println(resultSet.getString("studentname"));
                bookList.add(books);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;

    }

    public void showBooks() throws SQLException {
        ObservableList<Books> list = getBooksList();
        bookIdCol.setCellValueFactory(new PropertyValueFactory<Books, String>("bookId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Books, String>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        yearCol.setCellValueFactory(new PropertyValueFactory<Books, String>("year"));
        pageCol.setCellValueFactory(new PropertyValueFactory<Books, String>("page"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<Books, String>("category"));
        borrowdateCol.setCellValueFactory(new PropertyValueFactory<Books, String>("borrowdate"));
        StuNameCol.setCellValueFactory(new PropertyValueFactory<Books, String>("studentname"));
        StuIdCol.setCellValueFactory(new PropertyValueFactory<Books, String>("studentid"));
        returnDateCol.setCellValueFactory(new PropertyValueFactory<Books, String>("rda"));
        tableView.setItems(list);

        Connection conn = DatabaseConnection.getConnection();
        String sqlSelect = "SELECT * FROM users";
        PreparedStatement statement = conn.prepareStatement(sqlSelect);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            if (rs.getInt("isActive") == 1) {
                usernameUser.setText(rs.getString("username"));
                String sql2 = "UPDATE users SET isActive=? WHERE username = ?";
                PreparedStatement statement2 = conn.prepareStatement(sql2);
                statement2.setInt(1, 0);
                statement2.setString(2, rs.getString("username"));
                statement2.executeUpdate();
            }
        }
    }

    @FXML
    void getItem(MouseEvent event) {
        Integer index = tableView.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        bookIdField.setText(bookIdCol.getCellData(index).toString());
        titleField.setText(titleCol.getCellData(index).toString());
        authorField.setText(authorCol.getCellData(index).toString());
        yearField.setText(yearCol.getCellData(index).toString());
        pageField.setText(pageCol.getCellData(index).toString());
        categoryField.setText(categoryCol.getCellData(index).toString());
        borrowdateField.setText(borrowdateCol.getCellData(index).toString());
        StuNameField.setText(StuNameCol.getCellData(index).toString());
        studentfield.setText(StuIdCol.getCellData(index).toString());
        returnDateField.setText(returnDateCol.getCellData(index).toString());
        // System.out.println(StuNameCol.getCellData(index).toString());
    }

    @FXML
    void handleLogout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/LoginPage.fxml"));
        Parent welcomeParent = loader.load();
        Scene welcomeScene = new Scene(welcomeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(welcomeScene);
        window.show();
    }

    @FXML
    void handleSearch(ActionEvent event) throws SQLException {
        showBooks();
    }

    @FXML
    void handleSearchField(ActionEvent event) throws SQLException {
        handleSearch(event);

    }

}

package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private PasswordField  confirmPasswordField;

    @FXML
    private Text loginBtn;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpBtn;

    @FXML
    private TextField usernameField;

    @FXML
    void handleLogin(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/LoginPage.fxml"));
        Parent welcomeParent = loader.load();
        Scene welcomeScene = new Scene(welcomeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(welcomeScene);
        window.show();
    }

    @FXML
    void handleSignUp(ActionEvent event) throws IOException {
        try {
            String name = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            if (name == "" || name == null || password == "" || password == null || confirmPassword == ""
                    || confirmPassword == null) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Fail");
                alert.setHeaderText(null);
                alert.setContentText("Please input all fields!");
                alert.showAndWait();
            } else {
                if (password.equals(confirmPassword)) {
                    Connection conn = DatabaseConnection.getConnection();
                    String selectSql = "SELECT * FROM users WHERE username = ?";
                    PreparedStatement selectStmt = conn.prepareStatement(selectSql);
                    selectStmt.setString(1, name);
                    ResultSet rs = selectStmt.executeQuery();
                    if (rs.next()) {
                        // System.out.println("Data already exists. Cannot insert.");
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Username already exists");
                        alert.setHeaderText(null);
                        alert.setContentText("Username already exists");
                        alert.showAndWait();
                    } else {
                        String insertSql = "INSERT INTO users (username, password) VALUES (?, ?)";
                        PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                        insertStmt.setString(1, name);
                        insertStmt.setString(2, password);

                        int rowsInserted = insertStmt.executeUpdate();
                        if (rowsInserted > 0) {
                            // System.out.println("Data inserted successfully.");
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Insert successfully");
                            alert.setHeaderText(null);
                            alert.setContentText("Insert successfully");
                            alert.showAndWait();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/LoginPage.fxml"));
                            Parent welcomeParent = loader.load();
                            Scene welcomeScene = new Scene(welcomeParent);

                            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            window.setScene(welcomeScene);
                            window.show();
                        }

                        insertStmt.close();
                    }
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error confirm password");
                    alert.showAndWait();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}

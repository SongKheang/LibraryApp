package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import config.DatabaseConnection;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text signUpBtn;

    @FXML
    private TextField usernameField;

    @FXML
    void handleLogin(ActionEvent event) throws IOException {
        String name = usernameField.getText();
        String password = passwordField.getText();
        if (name == "" || name == null || password == "" || password == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Fail");
            alert.setHeaderText(null);
            alert.setContentText("Please input all fields!");
            alert.showAndWait();
        } else {
            try {
                Connection connection = DatabaseConnection.getConnection();
                String sql = "SELECT * FROM users WHERE username=? AND password=?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, name);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String sql2 = "UPDATE users SET isActive=? WHERE username = ?";
                    PreparedStatement statement2 = connection.prepareStatement(sql2);
                    statement2.setInt(1, 1);
                    statement2.setString(2, name);
                    statement2.executeUpdate();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/BookPage.fxml"));
                    Parent welcomeParent = loader.load();
                    Scene welcomeScene = new Scene(welcomeParent);

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(welcomeScene);
                    window.show();
                } else {
                    // System.out.println("Invalid username or password.");
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Fail");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid username or password");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    void handleSignUp(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/SignUpPage.fxml"));
        Parent welcomeParent = loader.load();
        Scene welcomeScene = new Scene(welcomeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(welcomeScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

}

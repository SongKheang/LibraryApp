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
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WelcomeController implements Initializable {

    @FXML
    private Button ContinueBtn;

    @FXML
    private Text usernameUser;

    @FXML
    void handleLogin(ActionEvent event) throws IOException {
        // System.out.println("hello");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/BookPage.fxml"));
        Parent welcomeParent = loader.load();
        Scene welcomeScene = new Scene(welcomeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(welcomeScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sqlSelect = "SELECT * FROM users";
            PreparedStatement statement = conn.prepareStatement(sqlSelect);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (rs.getInt("isActive") == 1) {
                    usernameUser.setText(rs.getString("username"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

package Controllers;

import API.AccountAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LogInController {

    Services services = new Services();
    AccountAPI acc = new AccountAPI();
    public static String role;
    public static String userID;

    @FXML
    private Button backBtn;

    @FXML
    private Button logInBtn;

    @FXML
    private TextField idField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerBtn;

    @FXML
    private AnchorPane registerPane;

    @FXML
    void handleRegisterBtn(ActionEvent event) {
        services.openPage(event, "/pages/studentSignInPage.fxml");
    }

    @FXML
    void handlelogInBtn(ActionEvent event) {
        String id = idField.getText();
        String password = passwordField.getText();

        if(id == "" || password == "") {
            services.alertWarnning("Warnning", "Please complet all fields");
        }
        else if(id != "" && password != "") {
            if(acc.isLogedIn(role, id, password)) {
                userID = id;
                if(role.equalsIgnoreCase("admins")) {
                    services.openPage(event, "/pages/adminBookListPage.fxml");
                }
                else if(role.equalsIgnoreCase("students")) {
                    services.openPage(event, "/pages/studentExplorePage.fxml");
                }
            }
            else {
                services.alertWarnning("Invalid password or ID", "Check your password or ID agian");
            }
        }

    }
    
    @FXML
    void handleBackPane(ActionEvent event) {
        services.openPage(event, "/pages/homepage.fxml");
    }

    @FXML
    void initialize() {
        if(role.equalsIgnoreCase("admins")) {
            registerPane.setVisible(false);
        }
    }

}

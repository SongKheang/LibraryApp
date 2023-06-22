package Controllers;

import API.AccountAPI;
import API.Students;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AdiminSignInController {

    @FXML
    private TextField IdField;

    @FXML
    private Button aboutMeBtn;

    @FXML
    private Button logInBtn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField numberPhoneField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField rePasswordField;

    @FXML
    private Button signInBtn;

    @FXML
    void handleAboutMeBtn(ActionEvent event) {

    }
    public Services services = new Services();
    @FXML
    void handleLogInBtn(ActionEvent event) {
        LogInController.role = "admins";
        services.openPage(event, "/pages/logInPage.fxml");
    }
    public Students students;
    public AccountAPI acc = new AccountAPI();
    @FXML
    void handleSignInBtn(ActionEvent event) {
        String studentID = IdField.getText();
        String studentName = nameField.getText();
        String studentPhone = numberPhoneField.getText();
        String studentPassword = passwordField.getText();
        String repassword = rePasswordField.getText();

        if(!repassword.equals(studentPassword) ) {
            services.alertWarnning("Incorrect Password", "Please re-input password ...!");
            passwordField.setText("");
            rePasswordField.setText("");
        }
        else {
            if(studentID == "" || studentName == "" || studentPhone == "" || studentPassword == "") {
                services.alertWarnning("Complete Problems", "Please complete all fields...");
            }
            else if(studentID != "" && studentName != "" && studentPhone != "" && studentPassword != "") {
                students = new Students(studentID, studentName, studentPhone, studentPassword);
                acc.registerAdmin(studentID, studentName, studentPhone, studentPassword);
                handleLogInBtn(event);
            }
        }
    }

}

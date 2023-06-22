package Controllers;

import API.StudentSignInAPI;
import API.Students;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StudentSignInController {

    Services services = new Services();
    Students students;
    StudentSignInAPI studentSignInAPI = new StudentSignInAPI();

    @FXML
    private Button SignInBtn;

    @FXML
    private Button aboutMeBtn;

    @FXML
    private Button logInBtn;

    @FXML
    private TextField studentIdField;

    @FXML
    private TextField studentNameField;

    @FXML
    private TextField studentNumberPhoneField;

    @FXML
    private TextField studentPasswordField;

    @FXML
    private TextField studentRePasswordField;

    @FXML
    void handleAboutMeBtn(ActionEvent event) {

    }

    @FXML
    void handleLogInBtn(ActionEvent event) {
        LogInController.role = "students";
        services.openPage(event, "/pages/logInPage.fxml");
    }

    @FXML
    void handleSignInBtn(ActionEvent event) {
        String studentID = studentIdField.getText();
        String studentName = studentNameField.getText();
        String studentPhone = studentNumberPhoneField.getText();
        String studentPassword = studentPasswordField.getText();
        String repassword = studentRePasswordField.getText();

        if(!repassword.equals(studentPassword) ) {
            services.alertWarnning("Incorrect Password", "Please re-input password ...!");
            studentPasswordField.setText("");
            studentRePasswordField.setText("");
        }
        else {
            if(studentID == "" || studentName == "" || studentPhone == "" || studentPassword == "") {
                services.alertWarnning("Complete Problems", "Please complete all fields...");
            }
            else if(studentID != "" && studentName != "" && studentPhone != "" && studentPassword != "") {
                students = new Students(studentID, studentName, studentPhone, studentPassword);
                studentSignInAPI.insertStudent(students);
                handleLogInBtn(event);
            }
        }

    }

}

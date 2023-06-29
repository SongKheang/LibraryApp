package Controllers;

import API.StudentInfoAPI;
import API.Students;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class StudentEditInfoController {
    StudentInfoAPI studentInfoAPI = new StudentInfoAPI();
    Students students;
    Services services = new Services();
    @FXML
    private Button changeInfoBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button exploreBtn;

    @FXML
    private AnchorPane listBooksPane;

    @FXML
    private Button listBorrowBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private TextField numberPhoneField;

    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private PasswordField reNewPasswordField;

    @FXML
    private Text studentName;

    @FXML
    private TextField studentNameField;

    @FXML
    void handleEditBtn(ActionEvent event) {

    }

    @FXML
    void handleExploreBtn(ActionEvent event) {
        services.openPage(event, "/pages/studentExplorePage.fxml");
    }

    @FXML
    void handleLogOut(ActionEvent event) {
        services.openPage(event, "/pages/logInPage.fxml");
    }

    @FXML
    void handleChangeInfoBtn(ActionEvent event) {
        String studentName = studentNameField.getText();
        String studentPhone = numberPhoneField.getText();
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String reNewPassword = reNewPasswordField.getText();
        if(!oldPassword.equals(students.getStudentPassword())) {
            services.alertWarnning("Incorrect Password", "Please Check Your Password Again ...!");
            oldPasswordField.setText("");
            newPasswordField.setText("");
            reNewPasswordField.setText("");
        }
        else if(!reNewPassword.equals(newPassword)) {
            services.alertWarnning("Incorrect New-Password And Re-Password", "Please Check New-Password And Re-Password again ...!");
            newPasswordField.setText("");
            reNewPasswordField.setText("");
        }
        else {
            if(studentName == "" || studentPhone == "" || newPassword == "") {
                services.alertWarnning("Complete Problems", "Please complete all fields...");
            }
            else if(studentName != "" && studentPhone != "" && newPassword != "") {
                students = new Students(LogInController.userID, studentName, studentPhone, newPassword);
                studentInfoAPI.updateStudentInfo(students);
            }
        }
    }

    @FXML
    void handlelistBorrowBtn(ActionEvent event) {
        services.openPage(event, "/pages/studentDashBoardPage.fxml");
    }

    @FXML
    void initialize() {
        students = studentInfoAPI.getStudentInfo(LogInController.userID);
        showStudentInfo();
    }
    
    void showStudentInfo() {
        studentName.setText(students.getStudentName());
        studentNameField.setText(students.getStudentName());
        numberPhoneField.setText(students.getStudentPhone());
    }

}


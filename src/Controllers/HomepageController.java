package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomepageController {
    Services services = new Services();

    @FXML
    private Button aboutMeBtn;

    @FXML
    private Button adminBtn;

    @FXML
    private Button studentBtn;

    @FXML
    void handleAboutMeBtn(ActionEvent event) {

    }

    @FXML
    void handleAdminBtn(ActionEvent event) {
        LogInController.role = "admins";
        services.openPage(event,"/pages/logInPage.fxml");
    }

    @FXML
    void handleStudentBtn(ActionEvent event) {
        LogInController.role = "students";
        services.openPage(event,"/pages/logInPage.fxml");
    }



}

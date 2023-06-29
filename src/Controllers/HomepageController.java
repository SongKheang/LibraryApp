package Controllers;

import API.AccountAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomepageController {
    Services services = new Services();
    AccountAPI accountAPI = new AccountAPI();

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
        if(accountAPI.isAdminExist()) {
            LogInController.role = "admins";
            services.openPage(event,"/pages/logInPage.fxml");
        }
        else {
            services.openPage(event,"/pages/adminSignInPage.fxml");
        }
    }

    @FXML
    void handleStudentBtn(ActionEvent event) {
        LogInController.role = "students";
        services.openPage(event,"/pages/logInPage.fxml");
    }



}

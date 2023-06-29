package Controllers;

import API.AdminInfoAPI;
import API.Students;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AdminInfoController {

    AdminInfoAPI adminInfoAPI = new AdminInfoAPI();
    Services services = new Services();

    @FXML
    private Button adminInfo;

    @FXML
    private Button borrowBookBtn;

    @FXML
    private Button deleteStudentBtn;

    @FXML
    private Button insertBookBtn;

    @FXML
    private Button listBookBtn;

    @FXML
    private AnchorPane listBooksPane;

    @FXML
    private Button listBorrowBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private TableColumn<Students, String> phoneNumberColumn;

    @FXML
    private Button registerBtn;

    @FXML
    private TableColumn<Students, String> studentIDCol;

    @FXML
    private Button studentInfoBtn;

    @FXML
    private Text studentName;

    @FXML
    private TableColumn<Students, String> studentNameCol;

    @FXML
    private TableView<Students> tableView;

    @FXML
    void handleAdminInfo(ActionEvent event) {
        services.openPage(event, "/pages/adminInfoPage.fxml");
    }

    @FXML
    void handleBorrowBookBtn(ActionEvent event) {
        services.openPage(event, "/page/adminBorrowPage.fxml");
    }

    @FXML
    void handleDeleteStudentBtn(ActionEvent event) {
        if (selectedStudent != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Account");
            alert.setHeaderText("Do you really want to delete this admin account?");
            alert.setResult(ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult().equals(ButtonType.OK)) {
                adminInfoAPI.deleteAdmin(selectedStudent.getStudentID());
                adminInfoAPI.getAdminList();
                selectedStudent = null;
            }
        } else {
            services.alertWarnning("No Book Selected!", "You need to select a book in the list first ...");
        }
    }

    @FXML
    void handleInsertBookBtn(ActionEvent event) {
        services.openPage(event, "/pages/insertBookPage.fxml");
    }

    @FXML
    void handleListBookBtn(ActionEvent event) {
        services.openPage(event, "/pages/adminBookListPage.fxml");
    }

    @FXML
    void handleLogOut(ActionEvent event) {
        services.openPage(event, "/pages/homepage.fxml");
    }

    @FXML
    void handleRegisterBtn(ActionEvent event) {
        services.openPage(event, "/pages/adminSignInPage.fxml");
    }

    @FXML
    void handleStudentInfoBtn(ActionEvent event) {
        services.openPage(event, "/pages/adminStudentInfoPage.fxml");
    }

    @FXML
    void handlelistBorrowBtn(ActionEvent event) {
        services.openPage(event, "/pages/adminBorrowListPage.fxml");
    }

    Students selectedStudent;
    @FXML
    void selectItem(MouseEvent event) {
            if (event.getClickCount() >= 1) {
            TableView.TableViewSelectionModel<Students> selectionModel = tableView.getSelectionModel();
            selectedStudent = selectionModel.getSelectedItem();
        }
    }

    @FXML
    void initialize() {
        showListBook();
        studentName.setText(LogInController.userID);
    }

    public void showListBook() {
        studentIDCol.setCellValueFactory(new PropertyValueFactory<Students, String>("studentID"));
        studentNameCol.setCellValueFactory(new PropertyValueFactory<Students, String>("studentName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Students, String>("studentPhone"));

        tableView.setItems(adminInfoAPI.getAdminList());
    }

}

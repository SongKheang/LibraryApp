package Controllers;

import API.AdminInfoAPI;
import API.Students;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AdminStudentInfoController {

    AdminInfoAPI adminInfoAPI = new AdminInfoAPI();
    Services services = new Services();
    @FXML
    private Button adminInfo;

    @FXML
    private Button borrowBookBtn;

    @FXML
    private Button clearSearchBtn;

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
    private TextField searchField;

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
        services.openPage(event, "/pages/adminBorrowPage.fxml");
    }

    @FXML
    void handleClearSearch(ActionEvent event) {

    }

    @FXML
    void handleDeleteStudentBtn(ActionEvent event) {
        String studentID = selectedStudent.getStudentID();
        if (selectedStudent != null) {
            adminInfoAPI.deleteStudent(studentID);
            adminInfoAPI.setStudentList(adminInfoAPI.getStudentList());
            selectedStudent = null;
        } else {
            services.alertWarnning("Did't selecte Books", "You need to select book in list first ...");
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
    void handleSearchField(ActionEvent event) {

    }

    @FXML
    void handleStudentInfoBtn(ActionEvent event) {

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

        tableView.setItems(adminInfoAPI.getStudentList());
    }

}

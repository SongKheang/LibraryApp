package Controllers;

import API.AdminInfoAPI;
import API.StudentExploreAPI;
import API.Students;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AdminStudentInfoController {

    AdminInfoAPI adminInfoAPI = new AdminInfoAPI();
    StudentExploreAPI studentExploreAPI = new StudentExploreAPI();
    Services services = new Services();
    @FXML
    private Button handelresetPasswordBtn;

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
        searchField.setText("");
        adminInfoAPI.getStudentList("");
        handleSearchField(event);
    }
    @FXML
    void handelresetPasswordBtn(ActionEvent event) {
        if (selectedStudent != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Reset Password");
            alert.setHeaderText("Student's password will reset to Student's ID, Do you really want to Reset?");
            alert.setResult(ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult().equals(ButtonType.OK)) {
                adminInfoAPI.resetStudentPassword(selectedStudent.getStudentID());
                adminInfoAPI.setStudentList(adminInfoAPI.getStudentList(""));
                selectedStudent = null;
            }
        }
        else {
            services.alertWarnning("No Student Selected", "You need to select student in the list first ...");
        }
    }

    @FXML
    void handleDeleteStudentBtn(ActionEvent event) {
        if(selectedStudent == null) {
            services.alertWarnning("No Student Selected", "You need to select student in the list first ...");
        }
        else if (selectedStudent != null && studentExploreAPI.getNumberOfBookStudentBorrow(selectedStudent.getStudentID()) == 0) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Comfirmation");
            alert.setHeaderText("Do you really want to delete this student info?");
            alert.setResult(ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult().equals(ButtonType.OK)) {
                adminInfoAPI.deleteStudent(selectedStudent.getStudentID());
                adminInfoAPI.setStudentList(adminInfoAPI.getStudentList(""));
                selectedStudent = null;
            }
        }
        else if(studentExploreAPI.getNumberOfBookStudentBorrow(selectedStudent.getStudentID()) > 0) {
            services.alertWarnning("Student Can't Be Delete", "Student haven't returned all borrowed books yet!");
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
        String searchText = searchField.getText();
        adminInfoAPI.getStudentList(searchText);
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

        tableView.setItems(adminInfoAPI.getStudentList(""));
    }

}

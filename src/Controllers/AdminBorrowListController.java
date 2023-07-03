package Controllers;

import API.AdminBorrowListAPI;
import API.Borrows;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AdminBorrowListController {

    AdminBorrowListAPI adminBorrowListAPI = new AdminBorrowListAPI();
    Services services = new Services();
    @FXML
    private Button adminInfo;

    @FXML
    private TableColumn<Borrows, Integer> bookIdColumn;

    @FXML
    private Button borrowBookBtn;

    @FXML
    private TableColumn<Borrows, String> borrowDateColumn;

    @FXML
    private TableColumn<Borrows, String> borrowerColumn;

    @FXML
    private Button clearSearchBtn;

    @FXML
    private Button insertBookBtn;

    @FXML
    private TableColumn<Borrows, String> isReturnedColumn;

    @FXML
    private Button listBookBtn;

    @FXML
    private AnchorPane listBooksPane;

    @FXML
    private Button listBorrowBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private TableColumn<Borrows, String> phoneNumberColumn;

    @FXML
    private Button returnBookBtn;

    @FXML
    private TableColumn<Borrows, String> returnDateColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Button studentInfoBtn;

    @FXML
    private Text studentName;

    @FXML
    private TableView<Borrows> tableView;

    @FXML
    private TableColumn<Borrows, String> titleColumn;

    @FXML
    private ComboBox<String> returnComboBox;

    @FXML
    void handleStudentInfoBtn(ActionEvent event) {
        services.openPage(event, "/pages/adminStudentInfoPage.fxml");
    }
    
    @FXML
    void handleInsertBookBtn(ActionEvent event) {
        services.openPage(event, "/pages/insertBookPage.fxml");
    }

     @FXML
    void handleAdminInfo(ActionEvent event) {
        services.openPage(event, "/pages/adminInfoPage.fxml");
    }

    @FXML
    void handleListBookBtn(ActionEvent event) {
        services.openPage(event, "/pages/AdminBookListPage.fxml");
    }

    @FXML
    void handlelistBorrowBtn(ActionEvent event) {

    }

    @FXML
    void handleBorrowBookBtn(ActionEvent event) {
        services.openPage(event, "/pages/adminBorrowPage.fxml");
    }

    @FXML
    void handleClearSearch(ActionEvent event) {
        searchField.setText("");
        adminBorrowListAPI.setBorrowsList(adminBorrowListAPI.getBorrowList("", "", comboText));
    }

    @FXML
    void handleLogOut(ActionEvent event) {
        services.openPage(event, "/pages/homepage.fxml");
    }

    @FXML
    void handleReturnBookBtn(ActionEvent event) {
        if(selectedBorrows != null) {
            int bookID = selectedBorrows.getBookID();
            String studentID = selectedBorrows.getBorrower();
            adminBorrowListAPI.returnBook(bookID, studentID);
            selectedBorrows = null;
            handleSearchField(event);
        }
        else {
            services.alertWarnning("Did't selecte Books", "You need to select book in list first ...");
        }
    }

    @FXML
    void handleSearchField(ActionEvent event) {
        String searchText = searchField.getText();
        adminBorrowListAPI.setBorrowsList(adminBorrowListAPI.getBorrowList(" where books.title LIKE '%" + searchText + "%' or students.studentID LIKE '%" + searchText + "%'"," where books.title LIKE '%" + searchText + "%' or borroweroutside.name LIKE '%" + searchText + "%'", comboText));
    }

    Borrows selectedBorrows;
    @FXML
    void selectItem(MouseEvent event) {
        if (event.getClickCount() >= 1) {
            TableView.TableViewSelectionModel<Borrows> selectionModel = tableView.getSelectionModel();
            selectedBorrows = selectionModel.getSelectedItem();
        }
    }

    String comboText = "No";
    @FXML
    void handleReturnComboBox(ActionEvent event) {
        if(returnComboBox.getValue().equals("Is Borrowing")) {
            comboText = "No";
        }
        else if(returnComboBox.getValue().equals("Returned")) {
            comboText = "Yes";
        }
        else {
            comboText = "All";
        }
        handleSearchField(event);

    }

    @FXML
    void initialize() {
        showListBook();
        setReturnComboBox();
        studentName.setText(LogInController.userID);
    }

    public void showListBook() {
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<Borrows, Integer>("bookID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Borrows, String>("title"));
        borrowerColumn.setCellValueFactory(new PropertyValueFactory<Borrows, String>("borrower"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Borrows, String>("phoneNumber"));
        borrowDateColumn.setCellValueFactory(new PropertyValueFactory<Borrows, String>("borrowDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<Borrows, String>("returnDate"));
        isReturnedColumn.setCellValueFactory(new PropertyValueFactory<Borrows, String>("isReturned"));

        adminBorrowListAPI.setBorrowsList(adminBorrowListAPI.getBorrowList("","", "No"));
        tableView.setItems(adminBorrowListAPI.getBorrowsList());
    }

    public void setReturnComboBox() {
        ObservableList<String> list = FXCollections.observableArrayList("All", "Is Borrowing", "Returned");
        returnComboBox.setItems(list);
    }

}

package Controllers;

import API.AdminBorrowListAPI;
import API.Borrows;
import API.StudentDashboardAPI;
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

public class StudentDashboardController {

    StudentDashboardAPI studentDashboardAPI = new StudentDashboardAPI();
    AdminBorrowListAPI adminBorrowListAPI = new AdminBorrowListAPI();
    Services services = new Services();
    public static String studentID = LogInController.userID;

    @FXML
    private TableColumn<Borrows, String> authorColumn;

    @FXML
    private TableColumn<Borrows, Integer> bookIdColumn;

    @FXML
    private TableColumn<Borrows, String> bookshelfColumn;

    @FXML
    private TableColumn<Borrows, String> borrowDateColumn;

    @FXML
    private Button clearSearchBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button exploreBtn;

    @FXML
    private TableColumn<Borrows, String> isReturnedColumn;

    @FXML
    private AnchorPane listBooksPane;

    @FXML
    private Button listBorrowBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button returnBookBtn;

    @FXML
    private ComboBox<String> returnComboBox;

    @FXML
    private TableColumn<Borrows, String> returnDateColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Text studentName;

    @FXML
    private TableView<Borrows> tableView;

    @FXML
    private TableColumn<Borrows, String> titleColumn;

    @FXML
    void handleClearSearch(ActionEvent event) {
        studentDashboardAPI.setBorrowsList(studentDashboardAPI.getBorrowList(studentID, "", comboText));

    }

    @FXML
    void handleEditBtn(ActionEvent event) {

    }

    @FXML
    void handleExploreBtn(ActionEvent event) {
        services.openPage(event, "/pages/studentExplorePage.fxml");
    }

    @FXML
    void handleLogOut(ActionEvent event) {
        services.openPage(event, "/pages/homepage.fxml");
    }

    @FXML
    void handleReturnBookBtn(ActionEvent event) {
        if(selectedBorrows != null) {
            int bookID = selectedBorrows.getBookID();
            adminBorrowListAPI.returnBook(bookID, studentID);
            selectedBorrows = null;
            handleClearSearch(event);
        }
        else {
            services.alertWarnning("Did't selecte Books", "You need to select book in list first ...");
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
    void handleSearchField(ActionEvent event) {
        String searchText = searchField.getText();
        studentDashboardAPI.setBorrowsList(studentDashboardAPI.getBorrowList(studentID, "AND books.title LIKE '%" + searchText + "%'", comboText));
    }


    @FXML
    void handlelistBorrowBtn(ActionEvent event) {

    }

    Borrows selectedBorrows;
    @FXML
    void selectItem(MouseEvent event) {
        if (event.getClickCount() >= 1) {
            TableView.TableViewSelectionModel<Borrows> selectionModel = tableView.getSelectionModel();
            selectedBorrows = selectionModel.getSelectedItem();
        }
    }

    @FXML
    void initialize() {
        showListBook();
        setReturnComboBox();
        studentName.setText(studentID);
    }

    public void showListBook() {

        bookIdColumn.setCellValueFactory(new PropertyValueFactory<Borrows, Integer>("bookID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Borrows, String>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Borrows, String>("author"));
        bookshelfColumn.setCellValueFactory(new PropertyValueFactory<Borrows, String>("bookshelf"));
        borrowDateColumn.setCellValueFactory(new PropertyValueFactory<Borrows, String>("borrowDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<Borrows, String>("returnDate"));
        isReturnedColumn.setCellValueFactory(new PropertyValueFactory<Borrows, String>("isReturned"));

        studentDashboardAPI.setBorrowsList(studentDashboardAPI.getBorrowList(studentID, "", "No"));
        tableView.setItems(studentDashboardAPI.getBorrowsList());
    }

    public void setReturnComboBox() {
        ObservableList<String> list = FXCollections.observableArrayList("All", "Is Borrowing", "Returned");
        returnComboBox.setItems(list);
    }

}

package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class StHistoryController {

    @FXML
    private TableColumn<?, ?> authorColumn;

    @FXML
    private TableColumn<?, ?> bookIdColumn;

    @FXML
    private TableColumn<?, ?> borrowDateColumn;

    @FXML
    private Button borrowHistoryBtn;

    @FXML
    private TableColumn<?, ?> categoryColumn;

    @FXML
    private Button clearSearchBtn;

    @FXML
    private Button dashBoardBtn;

    @FXML
    private Button editeInfoBtn;

    @FXML
    private Button exploreBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private TableColumn<?, ?> returnDateColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Text studentName;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> titleColumn;

    @FXML
    private TableColumn<?, ?> yearColumn;

    @FXML
    void handleBorrowHistoryBtn(ActionEvent event) {

    }

    @FXML
    void handleClearSearch(ActionEvent event) {

    }

    @FXML
    void handleDashboardBtn(ActionEvent event) {

    }

    @FXML
    void handleEditeInfoBtn(ActionEvent event) {

    }

    @FXML
    void handleExploreBtn(ActionEvent event) {

    }

    @FXML
    void handleLogOut(ActionEvent event) {

    }

    @FXML
    void handleSearchField(ActionEvent event) {

    }

}
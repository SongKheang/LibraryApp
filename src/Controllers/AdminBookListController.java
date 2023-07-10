package Controllers;

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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import API.BookListAPI;
import API.Books;

public class AdminBookListController {

    Services services = new Services();
    BookListAPI bookListAPI = new BookListAPI();
    Books books;

    @FXML
    private AnchorPane listBooksPane;

    @FXML
    private AnchorPane bookDetailsPane;

    @FXML
    private Button adminInfo;

    @FXML
    private Button clearSearchBtn;

    @FXML
    private Button comedyCategoryBtn;

    @FXML
    private Button historyCategoryBtn;

    @FXML
    private Button insertBookBtn;

    @FXML
    private Button listBookBtn;

    @FXML
    private Button listBorrowBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button scienceCategoryBtn;

    @FXML
    private TextField searchField;

    @FXML
    private Button studentInfoBtn;

    @FXML
    private Button thoeryCategoryBtn;

    @FXML
    private Button deleteBook;

    @FXML
    private Button deleteBook1;

    @FXML
    private Button insertBtn;

    @FXML
    private Button editBookBtn;

    @FXML
    private Button editBookBtn1;

    @FXML
    private Text studentName;

    // BookdetailPane

    @FXML
    private ImageView bookCover;

    @FXML
    private Button backBtn;

    @FXML
    private Text bookIDText;

    @FXML
    private Text titleText;

    @FXML
    private Text authorText;

    @FXML
    private Text yearText;

    @FXML
    private Text categoryText;

    @FXML
    private Text pageText;

    @FXML
    private Text qualityText;

    @FXML
    private Text quantityText;

    @FXML
    private Text remainText;

    @FXML
    private Text bookshelfText;

    @FXML
    private TableColumn<Books, String> authorColumn;

    @FXML
    private TableColumn<Books, Integer> bookIdColumn;

    @FXML
    private TableColumn<Books, String> bookSelfColumn;

    @FXML
    private TableColumn<Books, Integer> qauntityColumn;

    @FXML
    private TableColumn<Books, String> qualityColumn;

    @FXML
    private TableColumn<Books, String> titleColumn;

    @FXML
    private TableColumn<Books, Integer> remainColumn;

    @FXML
    private TableView<Books> tableView;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private Button borrowBookBtn;

    @FXML
    void handleBorrowBookBtn(ActionEvent event) {
        services.openPage(event, "/pages/adminBorrowPage.fxml");
    }

    @FXML
    void haddleCategoryComboBox(ActionEvent event) {
        String category = categoryComboBox.getValue();
        if (category.equalsIgnoreCase("All")) {
            handleClearSearch(event);
        } else {
            bookListAPI.setBookList(bookListAPI.searchBooks("category", category));
            tableView.setItems(bookListAPI.getBookList());
        }

    }

    @FXML
    void handleAdminInfo(ActionEvent event) {
        services.openPage(event, "/pages/adminInfoPage.fxml");
    }

    @FXML
    void handleClearSearch(ActionEvent event) {
        searchField.setText("");
        handleSearchField(event);
    }

    @FXML
    void handleComedyCategoryBtn(ActionEvent event) {
        bookListAPI.setBookList(bookListAPI.searchBooks("category", "comedy"));
        tableView.setItems(bookListAPI.getBookList());
    }

    @FXML
    void handleDeleteBook(ActionEvent event) {
        if (selectedBook != null && !bookListAPI.isBorrowed(selectedBook.getBookID())) {
            bookListAPI.deleteBook(selectedBook.getBookID());
            tableView.setItems(bookListAPI.getBookList());
            selectedBook = null;
            services.openPage(event, "/pages/adminBookListPage.fxml");
        }
        else if(selectedBook != null && bookListAPI.isBorrowed(selectedBook.getBookID())) {
            services.alertWarnning("Can't be delete ", "Book has been borrowed ...");
        }
        else {
            services.alertWarnning("Did't selecte Books", "You need to select book in list first ...");
        }
    }

    @FXML
    void handleEditBookBtn(ActionEvent event) {
        if (selectedBook != null) {
            editBookController.bookID = selectedBook.getBookID();
            services.openPage(event, "/pages/editBookPage.fxml");
        } else {
            services.alertWarnning("Did't selecte Books", "You need to select book in list first ...");
        }
    }

    @FXML
    void handleInsertBtn(ActionEvent event) {
        services.openPage(event, "/pages/insertBookPage.fxml");
    }

    @FXML
    void handleInsertBookBtn(ActionEvent event) {
        services.openPage(event, "/pages/insertBookPage.fxml");
    }

    @FXML
    void handleListBookBtn(ActionEvent event) {

    }

    @FXML
    void handleLogOut(ActionEvent event) {
        services.openPage(event, "/pages/homepage.fxml");
    }

    @FXML
    void handleScienceCategoryBtn(ActionEvent event) {
        bookListAPI.setBookList(bookListAPI.searchBooks("category", "science"));
        tableView.setItems(bookListAPI.getBookList());
    }

    @FXML
    void handleSearchField(ActionEvent event) {
        String title = searchField.getText();
        bookListAPI.setBookList(bookListAPI.searchBooks("title", title));
        tableView.setItems(bookListAPI.getBookList());
    }

    @FXML
    void handleStudentInfoBtn(ActionEvent event) {
        services.openPage(event, "/pages/adminStudentInfoPage.fxml");
    }

    @FXML
    void handleThoeryCategoryBtn(ActionEvent event) {
        bookListAPI.setBookList(bookListAPI.searchBooks("category", "thoery"));
        tableView.setItems(bookListAPI.getBookList());
    }

    @FXML
    void handlehistoryCategoryBtn(ActionEvent event) {
        bookListAPI.setBookList(bookListAPI.searchBooks("category", "history"));
        tableView.setItems(bookListAPI.getBookList());

    }

    @FXML
    void handlelistBorrowBtn(ActionEvent event) {
        services.openPage(event, "/pages/adminBorrowListPage.fxml");
    }

    @FXML
    void handleBackPane(ActionEvent event) {
        bookDetailsPane.setVisible(false);
    }

    Books selectedBook;

    @FXML
    void selectItem(MouseEvent event) {
        // Check if the user clicked on a row
        if (event.getClickCount() == 1) {

            // Get the table view's selection model
            TableView.TableViewSelectionModel<Books> selectionModel = tableView.getSelectionModel();

            // Get the selected row
            selectedBook = selectionModel.getSelectedItem();
        } else if (event.getClickCount() >= 2) {
            openBookDetail();
        }
    }

    @FXML
    void initialize() {
    showListBook();
    setCategoryComboBox();
    bookDetailsPane.setVisible(false);
    studentName.setText(LogInController.userID);
    }

    public void showListBook() {
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<Books, Integer>("bookID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        qualityColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("quality"));
        qauntityColumn.setCellValueFactory(new PropertyValueFactory<Books, Integer>("quantity"));
        remainColumn.setCellValueFactory(new PropertyValueFactory<Books, Integer>("remain"));
        bookSelfColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("bookshelf"));

        bookListAPI.setBookList(bookListAPI.searchBooks("title", ""));
        tableView.setItems(bookListAPI.getBookList());
    }

    public void setCategoryComboBox() {
        ObservableList<String> categoryList = FXCollections.observableArrayList();
        categoryList = bookListAPI.getCategory();
        categoryComboBox.setItems(categoryList);
    }

    public void openBookDetail() {
        bookDetailsPane.setVisible(true);
        books = bookListAPI.getSelectedBook(selectedBook.getBookID());
        bookIDText.setText(Integer.toString(books.getBookID()));
        titleText.setText(books.getTitle());
        authorText.setText(books.getAuthor());
        yearText.setText(Integer.toString(books.getYear()));
        categoryText.setText(books.getCategory());
        pageText.setText(Integer.toString(books.getPage()));
        qualityText.setText(books.getQuality());
        quantityText.setText(Integer.toString(books.getQuantity()));
        remainText.setText(Integer.toString(books.getRemain()));
        bookshelfText.setText(books.getBookshelf());
        bookCover.setImage(services.getImageWithPath(books.getBookCoverPath()));

    }

}

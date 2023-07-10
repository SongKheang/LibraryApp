package Controllers;

import API.BookListAPI;
import API.Books;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class editBookController {

    public static int bookID;
    Books books;
    BookListAPI bookListAPI = new BookListAPI();
    Services services = new Services();

    @FXML
    private Button backBtn;

    @FXML
    private Button adminInfo;

    @FXML
    private TextField authorField;

    @FXML
    private ImageView bookCover;

    @FXML
    private TextField booksheltField;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private TextField categoryField;

    @FXML
    private Button clearBtn;

    @FXML
    private Button inputImage;

    @FXML
    private Button insertBookBtn;

    @FXML
    private Button insertBtn;

    @FXML
    private Button listBookBtn;

    @FXML
    private Button listBorrowBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private TextField pageField;

    @FXML
    private ComboBox<String> qualityComboBox;

    @FXML
    private TextField quantityField;

    @FXML
    private Button studentInfoBtn;

    @FXML
    private Text studentName;

    @FXML
    private TextField titleField;

    @FXML
    private TextField yearField;

    @FXML
    private Button borrowBookBtn;

    @FXML
    void handleBorrowBookBtn(ActionEvent event) {
        services.openPage(event, "/pages/adminBorrowPage.fxml");
    }

    @FXML
    void handleAdminInfo(ActionEvent event) {
        services.openPage(event, "/pages/adminInfoPage.fxml");
    }

    @FXML
    void handleCategoryComboBox(ActionEvent event) {
        categoryField.setText(categoryComboBox.getValue());
    }

    @FXML
    void handleClear(ActionEvent event) {
        titleField.setText("");
        authorField.setText("");
        yearField.setText("");
        categoryField.setText("");
        pageField.setText("");
        qualityComboBox.setValue("");
        quantityField.setText("");
        booksheltField.setText("");
        bookCover.setImage(services.getImageWithPath("/BookCover/default-book.png"));
    }

    @FXML
    void handleInputImage(ActionEvent event) {
        String path = services.uploadImage(bookCover);
        Image image = services.getImageWithPath(path);
        bookCover.setImage(image);
    }

    @FXML
    void handleInsertBookBtn(ActionEvent event) {

    }

    @FXML
    void handleInsertBtn(ActionEvent event) {
        String title = titleField.getText();
        String author = authorField.getText();
        String pageText = pageField.getText();
        String Category = categoryField.getText();
        String yearText = yearField.getText();
        String quality = qualityComboBox.getValue();
        String qualityText = quantityField.getText();
        String bookshelf = booksheltField.getText();
        String bookCoverPath = services.getPathImage(bookCover.getImage());
        int quantity = Integer.parseInt(qualityText);

        int page = 0;
        int year = 0;

        if(pageText != "" && yearText != "") {
            page = Integer.parseInt(pageText);
            year = Integer.parseInt(yearText);
        }

        if (title == "" || author == "" || quality == "" || quantity == 0 || bookshelf == "") {
            services.alertWarnning("Complete problem", "Please complete all inportances Info");
        } else if (title != "" && author != "" && quality != "" && quantity != 0 && bookshelf != "") {
            int newQty = quantity - oldBookQty; 
            books = new Books(title, author, year, Category, page, quality, bookshelf, quantity, newQty, bookCoverPath);
            bookListAPI.updateBook(bookID, books);
            handleClear(event);
            services.openPage(event, "/pages/adminBookListPage.fxml");
            services.alertSuccess("Update Book Successfully");
        }
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
    void handleQualityComboBox(ActionEvent event) {

    }

    @FXML
    void handleStudentInfoBtn(ActionEvent event) {
        services.openPage(event, "/pages/adminStudentInfoPage.fxml");
    }

    @FXML
    void handlelistBorrowBtn(ActionEvent event) {
        services.openPage(event, "/pages/adminBorrowListPage.fxml");
    }

    @FXML
    void handleBackPane(ActionEvent event) {
        services.openPage(event, "/pages/adminBookListPage.fxml");
    }

    @FXML
    void initialize() {
        setField();
        setCategoryComboBox();
        setQualityComboBox();
        studentName.setText(LogInController.userID);
    }

    private int oldBookQty;
    public void setField() {
        books = bookListAPI.getSelectedBook(bookID);
        titleField.setText(books.getTitle());
        authorField.setText(books.getAuthor());
        yearField.setText(Integer.toString(books.getYear()));
        categoryField.setText(books.getCategory());
        pageField.setText(Integer.toString(books.getPage()));
        qualityComboBox.setValue(books.getQuality());
        quantityField.setText(Integer.toString(books.getQuantity()));
        booksheltField.setText(books.getBookshelf());
        bookCover.setImage(services.getImageWithPath(books.getBookCoverPath()));
        oldBookQty = books.getQuantity();
    }

    public void setCategoryComboBox() {
        ObservableList<String> categoryList = FXCollections.observableArrayList();
        categoryList = bookListAPI.getCategory();
        categoryComboBox.setItems(categoryList);
    }

    public void setQualityComboBox() {
        ObservableList<String> qualityList = FXCollections.observableArrayList("New", "Meduim", "Old", "Unreadable");
        qualityComboBox.setItems(qualityList);
    }

}

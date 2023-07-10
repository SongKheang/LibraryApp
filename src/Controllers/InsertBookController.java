package Controllers;

import API.BookListAPI;
import API.Books;
import API.InsertBookAPI;
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

public class InsertBookController {

    Services services = new Services();
    Books books;
    InsertBookAPI insertBookAPI = new InsertBookAPI();
    BookListAPI bookListAPI = new BookListAPI();

    @FXML
    private Button adminInfo;

    @FXML
    private TextField authorField;

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
    private ImageView bookCover;

    @FXML
    private Button borrowBookBtn;

    @FXML
    void handleBorrowBookBtn(ActionEvent event) {
        services.openPage(event, "/pages/adminBorrowPage.fxml");
    }

    @FXML
    void handleCategoryComboBox(ActionEvent event) {
        categoryField.setText(categoryComboBox.getValue());
    }

    @FXML
    void handleAdminInfo(ActionEvent event) {
        services.openPage(event, "/pages/adminInfoPage.fxml");
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
    void handleInsertBookBtn(ActionEvent event) {}

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

        if(bookCoverPath.equals("file:/C:/Users/user/AppData/Roaming/Code/User/workspaceStorage/5571cae77d9f83bb0d1ba296165637b9/redhat.java/jdt_ws/LibraryApp_VENDara_b7016c9/bin/pages/images/default-book.png")) {
            bookCoverPath = "/BookCover/default-book.png";
        }

        int page = 0;
        int year = 0;
        int quantity = 0;

        if(services.isNumber(pageText) && services.isNumber(yearText) && services.isNumber(qualityText)) {
            page = Integer.parseInt(pageText);
            year = Integer.parseInt(yearText);
            quantity = Integer.parseInt(qualityText);
        } else {
            services.alertWarnning("Wrong Input", "Please check Year/Page/Quality again.");
            return;
        }

        if (title == "" || author == "" || quality == "" || quantity == 0 || bookshelf == "") {
            services.alertWarnning("Complete problem", "Please complete all important Info");
        } else if (title != "" && author != "" && quality != "" && quantity != 0 && bookshelf != "") {
            books = new Books(title, author, year, Category, page, quality, bookshelf, quantity, bookCoverPath);
            insertBookAPI.insertBook(books);
            handleClear(event);
            services.alertSuccess("Insert Book Successfully");
            services.openPage(event, "/pages/insertBookPage.fxml");
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
    void initialize() {
        setCategoryComboBox();
        setQualityComboBox();
        studentName.setText(LogInController.userID);
    }

    public void setCategoryComboBox() {
        ObservableList<String> categoryList = FXCollections.observableArrayList();
        categoryList = bookListAPI.getCategory();
        categoryList.remove(0);
        categoryComboBox.setItems(categoryList);
    }

    public void setQualityComboBox() {
        ObservableList<String> qualityList = FXCollections.observableArrayList("New", "Meduim", "Old", "Unreadable");
        qualityComboBox.setItems(qualityList);
    }
}

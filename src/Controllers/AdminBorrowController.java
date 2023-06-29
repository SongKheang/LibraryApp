package Controllers;

import java.time.LocalDate;

import API.AdminBorrowAPI;
import API.BookListAPI;
import API.Books;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AdminBorrowController {

    Services services = new Services();
    BookListAPI bookListAPI = new BookListAPI();
    Books books;
    AdminBorrowAPI adminBorrowAPI = new AdminBorrowAPI();

    @FXML
    private Button addToCartBtn;

    @FXML
    private Button addToCartBtn1;

    @FXML
    private TableColumn<Books, String> authorColCart;

    @FXML
    private TableColumn<Books, String> authorColumn;

    @FXML
    private Text authorText;

    @FXML
    private Button backBtn;

    @FXML
    private Button backBtn1;

    @FXML
    private ImageView bookCover;

    @FXML
    private AnchorPane bookDetailsPane;

    @FXML
    private Text bookIDText;

    @FXML
    private TableColumn<Books, Integer> bookIdColCart;

    @FXML
    private TableColumn<Books, Integer> bookIdColumn;

    @FXML
    private TableColumn<Books, String> bookSelfColCart;

    @FXML
    private TableColumn<Books, String> bookSelfColumn;

    @FXML
    private Text bookshelfText;

    @FXML
    private Button borrowBtn;

    @FXML
    private Button borrowBtn1;

    @FXML
    private Button borrowBtn2;

    @FXML
    private Button cartBtn;

    @FXML
    private Text cartNumber;

    @FXML
    private AnchorPane cartPane;

    @FXML
    private TableColumn<Books, String> categoryColCart;

    @FXML
    private TableColumn<Books, String> categoryColumn;

    @FXML
    private Text categoryText;

    @FXML
    private Button clearSearchBtn;

    @FXML
    private Button comedyCategoryBtn;

    @FXML
    private Button deleteCartBtn;

    @FXML
    private Button historyCategoryBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button listBookBtn;
    @FXML
    private Button listBorrowBtn;
    @FXML
    private Button studentInfoBtn;
    @FXML
    private Button adminInfo;
    @FXML
    private Button insertBookBtn;

    @FXML
    private TableColumn<Books, Integer> pageColCart;

    @FXML
    private TableColumn<Books, Integer> pageColumn;

    @FXML
    private Text pageText;

    @FXML
    private Text qualityText;

    @FXML
    private DatePicker returnDatePicker;

    @FXML
    private Button scienceCategoryBtn;

    @FXML
    private TextField searchField;

    @FXML
    private Text adminID;

    @FXML
    private TableView<Books> tableView;

    @FXML
    private TableView<Books> tableViewCart;

    @FXML
    private Button thoeryCategoryBtn;

    @FXML
    private TableColumn<Books, String> titleColCart;

    @FXML
    private TableColumn<Books, String> titleColumn;

    @FXML
    private Text titleText;

    @FXML
    private TableColumn<Books, Integer> yearColCart;

    @FXML
    private TableColumn<Books, Integer> yearColumn;

    @FXML
    private Text yearText;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private TextField borrowerNameField;

    @FXML
    private TextField borrowerNumberPhoneField;

    @FXML
    void haddleCategoryComboBox(ActionEvent event) {
        String category = categoryComboBox.getValue();
        if (category.equalsIgnoreCase("All")) {
            handleClearSearch(event);
        } else {
            bookListAPI.setBookList(adminBorrowAPI.searchBooks("category", category));
            tableView.setItems(bookListAPI.getBookList());
        }

    }

    @FXML
    void handleAddToCartBtn(ActionEvent event) {
        if (selectedBook != null) {
            books = new Books(selectedBook.getBookID(), selectedBook.getTitle(), selectedBook.getAuthor(),
                    selectedBook.getYear(), selectedBook.getCategory(), selectedBook.getPage(),
                    selectedBook.getBookshelf());

            boolean bookExist = false;
            for(Books b : cartList) {
                if(b.getBookID() == books.getBookID()) {
                    bookExist = true;
                    break;
                }
            }
            
            if(bookExist) {
                services.alertWarnning("Warning", "Books already exist in cart ...");

            }
            else if (cartList.size() < 5 && !cartList.contains(books)) {
                selectedBook = null;
                cartList.add(books);
                cartNumber.setText(Integer.toString(cartList.size()));
            }
            else {
                services.alertWarnning("Sorry", "You're out of limitation for cart ... !");
            }
        } else {
            services.alertWarnning("Warning", "Please select book first ...");
        }
    }

    @FXML
    void handleBackPane(ActionEvent event) {
        services.openPage(event, "/pages/adminBorrowPage.fxml");
    }

    @FXML
    void handleBorrowBtn(ActionEvent event) {
        if (selectedBook != null) {
            books = new Books(selectedBook.getBookID(), selectedBook.getTitle(), selectedBook.getAuthor(),
                    selectedBook.getYear(), selectedBook.getCategory(), selectedBook.getPage(),
                    selectedBook.getBookshelf());

            boolean bookExist = false;
            for(Books b : cartList) {
                if(b.getBookID() == books.getBookID()) {
                    bookExist = true;
                    break;
                }
            }
            
            if(bookExist) {
                services.alertWarnning("Warning", "Book already exists in cart ...");

            }
            else if (cartList.size() < 5 && !cartList.contains(books)) {
                cartList.add(books);
                handleCartBtn(event);
                bookDetailsPane.setVisible(false);
                selectedBook = null;
                cartNumber.setText(Integer.toString(cartList.size()));
            }
            else {
                services.alertWarnning("Sorry", "You have reached the limit for cart ... !");
            }
        } else {
            services.alertWarnning("Warning", "Please select book first ...");
        }
    }

    @FXML
    void handleBorrowCartBtn(ActionEvent event) {
        LocalDate now = LocalDate.now();
        String borrowDate = now.toString();
        String returnDate = "0000-00-00";
        String borrower = borrowerNameField.getText();
        String numberPhone = borrowerNumberPhoneField.getText();
        int limitOfBorrowing = 5 - adminBorrowAPI.getNumberOfBookBorrowerBorrow(numberPhone);
        boolean alreadyBorrowed = false;

        for(Books b : cartList) {
            if(adminBorrowAPI.getBorrowedBookID().contains(b.getBookID())) {
                alreadyBorrowed = true;
            }
        }

        if (returnDatePicker.getValue() != null) {
            returnDate = returnDatePicker.getValue().toString();
        }
        if (returnDate.equalsIgnoreCase("0000-00-00") || borrower == "" || numberPhone == "") {
            services.alertWarnning("Warning ...", "Please complete all fields ...");
        }
        else if(alreadyBorrowed) {
            services.alertWarnning("Warning ...", "Some Books already exist in your borrow list(Not return yet) ...");
        }
        else if(!services.canReturnOn(returnDatePicker.getValue())) {
            services.alertWarnning("Warning ...", "You can borrow books only for 2 weeks ...");
        }
        else if(limitOfBorrowing == 0) {
            services.alertWarnning("You're out of limitation for borrowing ... !", "You already borrowed 5 books.");
        }
        else if(cartList.size() > limitOfBorrowing) {
            services.alertWarnning("You're out of limitation for borrowing ... !", "You can borrow only " + limitOfBorrowing + " more books.");
        }
        else {
            adminBorrowAPI.borrowBook(cartList, borrower, numberPhone, borrowDate, returnDate, "0");
            cartList.clear();
            tableViewCart.setItems(null);
            handleBackPane(event);
        }
    }

    @FXML
    void handleCartBtn(ActionEvent event) {
        cartPane.setVisible(true);
        tableViewCart.setItems(cartList);
        showCartList();
    }

    @FXML
    void handleClearSearch(ActionEvent event) {
        searchField.setText("");
        handleSearchField(event);
    }

    @FXML
    void handleComedyCategoryBtn(ActionEvent event) {
        bookListAPI.setBookList(adminBorrowAPI.searchBooks("category", "comedy"));
        tableView.setItems(bookListAPI.getBookList());
    }

    @FXML
    void handleDeleteCartBtn(ActionEvent event) {
        if (selectedIndex == -1) {
            services.alertWarnning("Wanning", "Please select book in the list first ...");
        } else {
            int index = selectedIndex;
            cartList.remove(index);
            selectedIndex = -1;
        }

    }

    @FXML
    void handleLogOut(ActionEvent event) {
        services.openPage(event, "/pages/homepage.fxml");
    }

    @FXML
    void handleScienceCategoryBtn(ActionEvent event) {
        bookListAPI.setBookList(adminBorrowAPI.searchBooks("category", "science"));
        tableView.setItems(bookListAPI.getBookList());
    }

    @FXML
    void handleSearchField(ActionEvent event) {
        String title = searchField.getText();
        bookListAPI.setBookList(adminBorrowAPI.searchBooks("title", title));
        tableView.setItems(bookListAPI.getBookList());
    }

    @FXML
    void handleThoeryCategoryBtn(ActionEvent event) {
        bookListAPI.setBookList(adminBorrowAPI.searchBooks("category", "thoery"));
        tableView.setItems(bookListAPI.getBookList());
    }

    @FXML
    void handlehistoryCategoryBtn(ActionEvent event) {
        bookListAPI.setBookList(adminBorrowAPI.searchBooks("category", "history"));
        tableView.setItems(bookListAPI.getBookList());
    }

    @FXML
    void handleListBookBtn(ActionEvent event) {
        services.openPage(event, "/pages/adminBookListPage.fxml");
    }
    @FXML
    void handlelistBorrowBtn(ActionEvent event) {
        services.openPage(event, "/pages/adminBorrowListPage.fxml");
    }
    @FXML
    void handleStudentInfoBtn(ActionEvent event) {
        services.openPage(event, "/pages/adminStudentInfoPage.fxml");
    }
    @FXML
    void handleAdminInfo(ActionEvent event) {
        services.openPage(event, "/pages/adminInfoPage.fxml");
    }
    @FXML
    void handleInsertBookBtn(ActionEvent event) {
        services.openPage(event, "/pages/insertBookPage.fxml");
    }

    Books selectedBook;

    @FXML
    void selectItem(MouseEvent event) {

        if (event.getClickCount() == 1) {
            TableView.TableViewSelectionModel<Books> selectionModel = tableView.getSelectionModel();
            selectedBook = selectionModel.getSelectedItem();
        } else if (event.getClickCount() >= 2) {
            openBookDetail();
        }
    }

    int selectedIndex;
    @FXML
    void selectItemCart(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() >= 1) {
            selectedIndex = tableViewCart.getSelectionModel().getSelectedIndex();
        }
    }

    @FXML
    void initialize() {
        showListBook();
        showCartList();
        setCategoryComboBox();
        bookDetailsPane.setVisible(false);
        cartPane.setVisible(false);
        cartNumber.setText(Integer.toString(cartList.size()));
        adminID.setText(LogInController.userID);
    }

    public void showListBook() {
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<Books, Integer>("bookID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Books, Integer>("year"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("category"));
        pageColumn.setCellValueFactory(new PropertyValueFactory<Books, Integer>("page"));
        bookSelfColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("bookshelf"));

        bookListAPI.setBookList(adminBorrowAPI.searchBooks("title", ""));
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
        bookshelfText.setText(books.getBookshelf());
        bookCover.setImage(services.getImageWithPath(books.getBookCoverPath()));

    }

    public static ObservableList<Books> cartList = FXCollections.observableArrayList();
    public void showCartList() {
        bookIdColCart.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        titleColCart.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColCart.setCellValueFactory(new PropertyValueFactory<>("author"));
        yearColCart.setCellValueFactory(new PropertyValueFactory<>("year"));
        categoryColCart.setCellValueFactory(new PropertyValueFactory<>("category"));
        pageColCart.setCellValueFactory(new PropertyValueFactory<>("page"));
        bookSelfColCart.setCellValueFactory(new PropertyValueFactory<>("bookshelf"));
        tableViewCart.setItems(cartList);
    }
}

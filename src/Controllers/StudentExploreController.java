package Controllers;

import java.time.LocalDate;

import API.BookListAPI;
import API.Books;
import API.StudentExploreAPI;
import API.StudentInfoAPI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class StudentExploreController {
    StudentInfoAPI studentInfoAPI = new StudentInfoAPI();
    Services services = new Services();
    BookListAPI bookListAPI = new BookListAPI();
    Books books;
    StudentExploreAPI studentExploreAPI = new StudentExploreAPI();
    public String studentID = LogInController.userID;

    @FXML
    private Button addToCartBtn;
    @FXML
    private Button addToCartBtnUI;

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
    private Button dashBoardBtn;

    @FXML
    private Button deleteCartBtn;

    @FXML
    private Button editeInfoBtn;

    @FXML
    private Button exploreBtn;

    @FXML
    private Button historyCategoryBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private TableColumn<Books, Integer> pageColCart;

    @FXML
    private TableColumn<Books, Integer> pageColumn;

    @FXML
    private TableColumn<Books, Integer> remainColumn;

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
    private Text studentName;

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
    void handleAddToCartBtn(ActionEvent event) {
        int limitOfBorrowing = 5 - studentExploreAPI.getNumberOfBookStudentBorrow(studentID);
        if (selectedBook != null) {
            boolean bookExist = false;
            for (Books b : cartList) {
                if (b.getBookID() == selectedBook.getBookID()) {
                    bookExist = true;
                    break;
                }
            }

            if (bookExist) {
                services.alertWarnning("Warning", "Books already exist in cart ...");

            }
            else if (limitOfBorrowing - cartList.size() > 0) {
                books = new Books(selectedBook.getBookID(), selectedBook.getTitle(), selectedBook.getAuthor(),
                        selectedBook.getYear(), selectedBook.getCategory(), selectedBook.getPage(),
                        selectedBook.getBookshelf());
                cartList.add(books);
                selectedBook = null;
                setOpacityAddToCartBtnUI();
                cartNumber.setText(Integer.toString(cartList.size()));
            } else {
                services.alertWarnning("Can not Borrow", "You're out of limitation for borrowing ... !");
            }
        } else {
            services.alertWarnning("Warning", "Please select book first ...");
        }
    }

    @FXML
    void handleBackPane(ActionEvent event) {
        services.openPage(event, "/pages/studentExplorePage.fxml");
    }

    @FXML
    void handleBorrowBtn(ActionEvent event) {
        handleAddToCartBtn(event);
        handleCartBtn(event);
        bookDetailsPane.setVisible(false);
    }

    @FXML
    void handleBorrowCartBtn(ActionEvent event) {
        LocalDate now = LocalDate.now();
        String borrowDate = now.toString();
        String returnDate = "0000-00-00";
        boolean alreadyBorrowed = false; 

        for(Books b : cartList) {
            if(studentExploreAPI.getBorrowedBookID().contains(b.getBookID())) {
                alreadyBorrowed = true;
            }
        }

        if (returnDatePicker.getValue() != null) {
            returnDate = returnDatePicker.getValue().toString();
        }
        if (returnDate.equalsIgnoreCase("0000-00-00")) {
            services.alertWarnning("Warning ...", "Please pick return date ...");
        } 
        else if(alreadyBorrowed) {
            services.alertWarnning("Warning ...", "Some Books already exist in your borrow list(Not return yet) ...");
        }
        else if(!services.canReturnOn(returnDatePicker.getValue())) {
            services.alertWarnning("Warning ...", "You can borrow book only for 2 weeks ...");
        }
        else {
            studentExploreAPI.borrowBook(cartList, studentID, borrowDate, returnDate, "0");
            cartList.clear();
            tableViewCart.setItems(null);
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
        bookListAPI.setBookList(bookListAPI.searchBooks("category", "comedy"));
        tableView.setItems(bookListAPI.getBookList());
    }

    @FXML
    void handleDashboardBtn(ActionEvent event) {
        services.openPage(event, "/pages/studentDashboardPage.fxml");
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
    void handleEditeBtn(ActionEvent event) {
        services.openPage(event, "/pages/studentEditInfoPage.fxml");
    }

    @FXML
    void handleExploreBtn(ActionEvent event) {

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
    void handleThoeryCategoryBtn(ActionEvent event) {
        bookListAPI.setBookList(bookListAPI.searchBooks("category", "thoery"));
        tableView.setItems(bookListAPI.getBookList());
    }

    @FXML
    void handlehistoryCategoryBtn(ActionEvent event) {
        bookListAPI.setBookList(bookListAPI.searchBooks("category", "history"));
        tableView.setItems(bookListAPI.getBookList());
    }

    Books selectedBook;

    @FXML
    void selectItem(MouseEvent event) {
        TableView.TableViewSelectionModel<Books> selectionModel = tableView.getSelectionModel();
        selectedBook = selectionModel.getSelectedItem();
        if (event.getClickCount() == 1) {
            setOpacityAddToCartBtnUI();
        } else if (event.getClickCount() >= 2) {
            openBookDetail();
        }
    }

    int selectedIndex;

    @FXML
    void selectItemCart(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
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
        StudentInfoAPI studentInfoAPI = new StudentInfoAPI();
        studentName.setText(studentInfoAPI.getStudentInfo(LogInController.userID).getStudentName());
        setOpacityAddToCartBtnUI();
    
    }

    public void showListBook() {
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<Books, Integer>("bookID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Books, Integer>("year"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("category"));
        pageColumn.setCellValueFactory(new PropertyValueFactory<Books, Integer>("page"));
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

    void setOpacityAddToCartBtnUI() {
        if(selectedBook == null) {
            addToCartBtnUI.setOpacity(0.7);
        }
        else {
            addToCartBtnUI.setOpacity(1);
        }
    }
}

package API;

public class Borrows {
    private int bookID;
    private String title;
    private String borrower;
    private String phoneNumber;
    private String author;
    private String bookshelf;
    private String borrowDate;
    private String returnDate;
    private String isReturned;
    public Borrows(int bookID, String title, String author, String bookshelf, String borrowDate, String returnDate,
            String isReturned, String student) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.bookshelf = bookshelf;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }
    public Borrows(int bookID, String title, String borrower, String phoneNumber, String borrowDate, String returnDate,
            String isReturned) {
        this.bookID = bookID;
        this.title = title;
        this.borrower = borrower;
        this.phoneNumber = phoneNumber;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }
    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBorrower() {
        return borrower;
    }
    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }
    public String getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
    public String getIsReturned() {
        return isReturned;
    }
    public void setIsReturned(String isReturned) {
        this.isReturned = isReturned;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getBookshelf() {
        return bookshelf;
    }
    public void setBookshelf(String bookshelf) {
        this.bookshelf = bookshelf;
    }
}

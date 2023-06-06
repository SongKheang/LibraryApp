package controllers;

public class Books {
    private String bookId;
    private String title;
    private String author;
    private String year;
    private String page;
    private String category;
    private String borrowdate;
    public Books(String bookId, String title, String author, String year, String page, String category,
            String borrowdate) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.year = year;
        this.page = page;
        this.category = category;
        this.borrowdate = borrowdate;
    }
    public String getBorrowDate() {
        return borrowdate;
    }
    public void setBorrowDate(String borrowdate) {
        this.borrowdate = borrowdate;
    }
    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getPage() {
        return page;
    }
    public void setPage(String page) {
        this.page = page;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}

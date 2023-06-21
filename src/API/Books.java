package API;

public class Books {
    private int bookID;
    private String title;
    private String author;
    private int year;
    private String category;
    private int page;
    private String quality;     //: old medium new unreadable
    private String bookshelf;
    private int quantity;
    private int remain;
    private String bookCoverPath;

    
    public Books(int bookID, String title, String author, int year, String category, int page, String bookshelf) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.year = year;
        this.category = category;
        this.page = page;
        this.bookshelf = bookshelf;
    }

    public Books(int bookID, String title, String author, int year, String category, int page, String quality,
            String bookshelf, int quantity, int remain, String bookCoverPath) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.year = year;
        this.category = category;
        this.page = page;
        this.quality = quality;
        this.bookshelf = bookshelf;
        this.quantity = quantity;
        this.remain = remain;
        this.bookCoverPath = bookCoverPath;
    }

    public Books(String title, String author, int year, String category, int page, String quality,
            String bookshelf, int quantity, String bookCoverPath) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.category = category;
        this.page = page;
        this.quality = quality;
        this.bookshelf = bookshelf;
        this.quantity = quantity;
        this.bookCoverPath = bookCoverPath;
    }

    public String getBookCoverPath() {
        return bookCoverPath;
    }

    public void setBookCoverPath(String bookCoverPath) {
        this.bookCoverPath = bookCoverPath;
    }

    public Books(int bookID, String title, String author, int year, String category, int page, String quality,
            String bookshelf, int quantity, int remain) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.year = year;
        this.category = category;
        this.page = page;
        this.quality = quality;
        this.bookshelf = bookshelf;
        this.quantity = quantity;
        this.remain = remain;
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
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public String getQuality() {
        return quality;
    }
    public void setQuality(String quality) {
        this.quality = quality;
    }
    public String getBookshelf() {
        return bookshelf;
    }
    public void setBookshelf(String bookshelf) {
        this.bookshelf = bookshelf;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getRemain() {
        return remain;
    }
    public void setRemain(int remain) {
        this.remain = remain;
    }
}

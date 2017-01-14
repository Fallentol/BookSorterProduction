package essence;

import javax.persistence.*;

/**
 * Created by New on 1/14/2017.
 */
@Entity
@Table(name = "books", schema = "booksorterpro", catalog = "")
public class BooksEntity {
    private int bookId;
    private String bookName;
    private String bookAuthor;
    private String bookLanguage;
    private String bookType;
    private String bookFormat;
    private String bookPath;
    private String bookDescription;
    private Integer bookYear;
    private int bookSize;

    @Id
    @Column(name = "book_id", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "bookName", nullable = false, length = 255)
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Basic
    @Column(name = "bookAuthor", nullable = false, length = 255)
    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    @Basic
    @Column(name = "bookLanguage", nullable = false, length = 60)
    public String getBookLanguage() {
        return bookLanguage;
    }

    public void setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    @Basic
    @Column(name = "bookType", nullable = true, length = 60)
    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    @Basic
    @Column(name = "bookFormat", nullable = false, length = 60)
    public String getBookFormat() {
        return bookFormat;
    }

    public void setBookFormat(String bookFormat) {
        this.bookFormat = bookFormat;
    }

    @Basic
    @Column(name = "bookPath", nullable = false, length = 255)
    public String getBookPath() {
        return bookPath;
    }

    public void setBookPath(String bookPath) {
        this.bookPath = bookPath;
    }

    @Basic
    @Column(name = "bookDescription", nullable = false, length = 255)
    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    @Basic
    @Column(name = "bookYear", nullable = true)
    public Integer getBookYear() {
        return bookYear;
    }

    public void setBookYear(Integer bookYear) {
        this.bookYear = bookYear;
    }

    @Basic
    @Column(name = "bookSize", nullable = false)
    public int getBookSize() {
        return bookSize;
    }

    public void setBookSize(int bookSize) {
        this.bookSize = bookSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BooksEntity that = (BooksEntity) o;

        if (bookId != that.bookId) return false;
        if (bookSize != that.bookSize) return false;
        if (bookName != null ? !bookName.equals(that.bookName) : that.bookName != null) return false;
        if (bookAuthor != null ? !bookAuthor.equals(that.bookAuthor) : that.bookAuthor != null) return false;
        if (bookLanguage != null ? !bookLanguage.equals(that.bookLanguage) : that.bookLanguage != null) return false;
        if (bookType != null ? !bookType.equals(that.bookType) : that.bookType != null) return false;
        if (bookFormat != null ? !bookFormat.equals(that.bookFormat) : that.bookFormat != null) return false;
        if (bookPath != null ? !bookPath.equals(that.bookPath) : that.bookPath != null) return false;
        if (bookDescription != null ? !bookDescription.equals(that.bookDescription) : that.bookDescription != null)
            return false;
        if (bookYear != null ? !bookYear.equals(that.bookYear) : that.bookYear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (bookAuthor != null ? bookAuthor.hashCode() : 0);
        result = 31 * result + (bookLanguage != null ? bookLanguage.hashCode() : 0);
        result = 31 * result + (bookType != null ? bookType.hashCode() : 0);
        result = 31 * result + (bookFormat != null ? bookFormat.hashCode() : 0);
        result = 31 * result + (bookPath != null ? bookPath.hashCode() : 0);
        result = 31 * result + (bookDescription != null ? bookDescription.hashCode() : 0);
        result = 31 * result + (bookYear != null ? bookYear.hashCode() : 0);
        result = 31 * result + bookSize;
        return result;
    }
}

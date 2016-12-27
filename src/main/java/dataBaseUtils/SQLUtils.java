package dataBaseUtils;


import essence.Book;
import essence.Tag;
import interfase.mySQLhandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLUtils implements mySQLhandler {

    public static ArrayList<Book> getAllBooks(Connection sqlConnection) {
        ArrayList<Book> result = new ArrayList<Book>();
        Statement stmt = null;
        try {
            stmt = sqlConnection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                Book book = new Book();
                book.name = rs.getString("bookName");
                book.author = rs.getString("bookAuthor");
                book.language = rs.getString("bookLanguage");
                book.type = rs.getString("bookType");
                book.format = rs.getString("bookFormat");
                book.path = rs.getString("bookPath");
                book.description = rs.getString("bookDescription");
                book.year = Integer.valueOf(rs.getString("bookYear"));
                book.size = Integer.valueOf(rs.getString("bookSize"));
                result.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
        //куку
    }

    public Book getBookFromId(String id) {
        return null;
    }

    public Tag getTagFromId(String id) {
        return null;
    }

    public void insertNewBook(Book book) {

    }

    public void insertNewTag(Tag tag) {

    }

    public void updateBook(Book book) {

    }

    public void updateTag(Tag tag) {

    }

    public void deleteBook(String Id) {

    }

    public void deleteTag(String Id) {

    }

    public ArrayList<Book> getBooksFromTo(int from, int to, int quantity) {
        return null;
    }

    public ArrayList<Book> getFilteredBooks(String field, ArrayList<String> filters) {
        return null;
    }

    public List<Tag> getTagsFromTo(int from, int to, int quantity) {
        return null;
    }

    public ArrayList<Book> getFilteredTags(String field, ArrayList<String> filters) {
        return null;
    }
}

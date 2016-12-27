package dataBaseUtils;


import essence.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLUtils {

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
}

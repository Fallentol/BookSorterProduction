package dataBaseUtils;

import essence.Book;
import essence.Tag;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static config.Configurator.*;

public class SQLConnection {

    public ArrayList<Book> getBooks() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // в загрузчик попадает класс из драйвера. Драйвер скачивается и устанавливается бибиотекой к проекту
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + baseName + "?user=" + userName + "&password=" + userPass + "&useSSL=true");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SQLUtils sqlUtilsObject = new SQLUtils();
        ArrayList<Book> books = sqlUtilsObject.getAllBooks();
        return books;
    }

    public ArrayList<Tag> getTags() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // в загрузчик попадает класс из драйвера. Драйвер скачивается и устанавливается бибиотекой к проекту
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + baseName + "?user=" + userName + "&password=" + userPass + "&useSSL=true");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SQLUtils sqlUtilsObject = new SQLUtils();
        ArrayList<Tag> tags = sqlUtilsObject.getAllTags();
        return tags;
    }

    public static String getAnyValue() {
        return "Fuck";
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver"); // в загрузчик попадает класс из драйвера. Драйвер скачивается и устанавливается бибиотекой к проекту

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mysql?user=root&password=root&useSSL=true");
        SQLUtils sqlUtilsObject = new SQLUtils();
        ArrayList<Book> books = sqlUtilsObject.getAllBooks();
        for (Book b : books) {
            System.out.println(b);
        }

        /*Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/booksorterpro?user=admin&password=214926341&useSSL=true");

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM books");

            while (rs.next()) {
                String str = rs.getString("bookName");
                System.out.println(str);
            }

            rs = stmt.executeQuery("SHOW FIELDS FROM books");
            while (rs.next()) {
                String str = rs.getString(1);
                System.out.println(str);
            }

            rs.last(); // перемотка индекса в конец очереди
            int size = rs.getRow(); // узнаем какой иднекс
            rs.beforeFirst(); // возвращаем в начало очереди
            System.out.println("Всего записей = " + size );

            rs = stmt.executeQuery("SELECT COUNT(*) FROM books");
            System.out.println(rs.getString(1));

            // Do something with the Connection


        } catch (SQLException ex) {
            // handle any errors
            System.out.println(ex);
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }*/

    }
}
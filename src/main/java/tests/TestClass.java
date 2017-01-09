package tests;

import dataBaseUtils.SQLConnection;
import dataBaseUtils.SQLUtils;
import essence.Book;
import fileUtils.FileController;

import java.util.ArrayList;

/**
 * Created by New on 12/24/2016.
 */
public class TestClass {

    private static Book testBook = new Book(18, "Name", "Author", "ru", "book", "pdf", "Path", "test", 1997, 18);

    public static void main(String[] args) {
        //runFileControllerTest();
        //runSQLConnectionTest();
        runSQLUtilsTest();

    }

    private static void runSQLUtilsTest() {
        SQLUtils testObj = new SQLUtils();
        Book book = testObj.getBookFromId("2");
        //System.out.println("book=" + book);
        /////////////////////////////////////////
        int newBookId = testObj.insertNewBook(testBook);
        System.out.println("newBookId=" + newBookId);
    }

    public static void runFileControllerTest() {
        FileController testObject = new FileController();
        ArrayList<String> filesName = testObject.getFileBooksByName("10");
        for (String s : filesName) {
            System.out.println(s);
        }
        System.out.println("///////////////////////////////////////////");
    }

    public static void runSQLConnectionTest() {
        SQLConnection sqlCon = new SQLConnection();
        ArrayList<Book> books = sqlCon.getBooks();
        System.out.println(books);
    }


}

package tests;

import dataBaseUtils.SQLConnection;
import essence.Book;
import fileUtils.FileController;

import java.util.ArrayList;

/**
 * Created by New on 12/24/2016.
 */
public class TestClass {

    public static void main(String[] args) {
        runFileControllerTest();
        runSQLConnectionTest();

    }

    public static void runFileControllerTest () {
        FileController testObject = new FileController();
        ArrayList<String> filesName = testObject.getFileBooksByName("10");
        for(String s : filesName) {
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

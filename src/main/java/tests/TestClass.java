package tests;

import dataBaseUtils.SQLConnection;
import dataBaseUtils.SQLUtils;
import essence.Book;
import essence.LinksEntity;
import fileUtils.FileController;
import fileUtils.FileProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.Query;
import org.hibernate.Session;
import essence.BooksEntity;
import dataBaseUtils.HibernateSessionFactory;


public class TestClass {

    private static Book testBook = new Book(18, "Name", "Author", "ru", "book", "pdf", "Path", "test", 1997, 18);

    public static void main(String[] args) {
        //runFileControllerTest();
        //runSQLConnectionTest();
        //runSQLUtilsTest();
        //fileProcessorTest();

        System.out.println("Hibernate tutorial");

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        /*BooksEntity contactEntity = new BooksEntity();
        contactEntity.setBookName("rte");
        contactEntity.setBookAuthor("Nick");
        contactEntity.setBookDescription("VN");
        contactEntity.setBookFormat("pdf");
        contactEntity.setBookSize(55);
        contactEntity.setBookLanguage("Ru");
        contactEntity.setBookPath("Path");
        contactEntity.setBookType("Jork");
        contactEntity.setBookYear(2008);
        session.save(contactEntity);
        session.getTransaction().commit();*/

        /// вот так делаются запросы
        /*String foundname = "rte";
        Query query = session.createQuery("FROM BooksEntity WHERE bookName =:paramName ");
        query.setParameter("paramName", foundname);
        List<BooksEntity> listOfBook = query.list();
        for (BooksEntity name : listOfBook) {
            System.out.println("Name=" + name.getBookAuthor());
        }*/



        /*Query query = session.createQuery("FROM LinksEntity WHERE linkId =:paramName ");
        query.setParameter("paramName", 2);
        List<LinksEntity> listOfLink = query.list();
        for (LinksEntity name : listOfLink) {
            System.out.println("Name=" + name.getBook().getBookName());
        }*/

        Query query = session.createQuery("FROM LinksEntity WHERE linkId =:paramName ");
        query.setParameter("paramName", 2);
        List<LinksEntity> listOfLink = query.list();
        for (LinksEntity name : listOfLink) {
            System.out.println("Name=" + name.getBook().getBookName());
        }

        query = session.createQuery("FROM BooksEntity as book WHERE bookName =:paramName ");
        query.setParameter("paramName", "rte");
        List<BooksEntity> listOfBook = query.list();
        for (BooksEntity name : listOfBook) {
            //System.out.println("Name=" + name.getBookName());
        }

        query = session.createQuery("SELECT bookName, bookAuthor FROM BooksEntity WHERE bookName =:paramName ");
        query.setParameter("paramName", "rte");
        listOfBook = query.list();

        for (Object name : listOfBook) {
            System.out.println(name.getClass().getName()); // почему-то возварщает Object
        }





        session.close();

    }

    private static void fileProcessorTest() {
        FileProcessor.openFile("Akulin V.M. Coherent dynamics of complex quantum systems (Springer, 2006)(476s)_PQm_.pdf");
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

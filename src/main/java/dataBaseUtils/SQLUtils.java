package dataBaseUtils;


import essence.Book;
import essence.Tag;
import interfase.mySQLhandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SQLUtils implements mySQLhandler {

    private static Connection sqlConnection;
    private static final String sqlHost = "jdbc:mysql://localhost/booksorterpro?user=admin&password=214926341&useSSL=true";

    static SQLConnection SQLConnectionObject;
    Scanner sc = new Scanner(System.in);

    static {
        try {
            sqlConnection = DriverManager.getConnection(sqlHost);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDB() {
        //подготавливаю запрос на создание БД
        System.out.println("Для создания новой БД введите её название(bookstore, tagstore, linkstore...):");
        String dbName = sc.nextLine();
        String createCommand = "CREATE DATABASE " + dbName + " CHARACTER SET utf8 COLLATE utf8_general_ci";

        try {
            Statement stCR = sqlConnection.createStatement();
            stCR.execute(createCommand);
            System.out.println("База данных с именем: " + dbName + " создана успешно.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDB() {
        //подготавливаю запрос на удаление БД
        System.out.println("Для удаление БД введите её название:");
        String dbName = sc.nextLine();

        String createCommand = "DROP DATABASE " + dbName;

        try {
            Statement stCR = sqlConnection.createStatement();
            stCR.execute(createCommand);
            System.out.println("База данных с именем: " + dbName + " удалена успешно.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDBTableBooks() {
        //подготавливаю запрос на создание таблицы
        String tableName = "books";

        String createCommand = "CREATE TABLE " + tableName + " (" +
                "  `id` int(11) NOT NULL auto_increment," +
                "  `title` varchar(50) default NULL," +
                "  `author` varchar(50) default NULL," +
                "  `year` int(11) default NULL," +
                "  `type` varchar(45) default NULL," +
                "  `link` varchar(45) default NULL," +
                "  PRIMARY KEY  (`id`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

        try {
            allocateTableField(createCommand, tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDBTableTags() {
        //подготавливаю запрос на создание таблицы
        String tableName = "tags";

        String createCommand = "CREATE TABLE " + tableName + " (" +
                "  `id` int(11) NOT NULL auto_increment," +
                "  `title` varchar(50) default NULL," +
                "  `link` varchar(45) default NULL," +
                "  PRIMARY KEY  (`id`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

        try {
            allocateTableField(createCommand, tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDBTableLinks() {
        //подготавливаю запрос на создание таблицы
        String tableName = "links";

        String createCommand = "CREATE TABLE " + tableName + " (" +
                "  `id` int(11) NOT NULL auto_increment," +
                "  `title` varchar(50) default NULL," +
                "  PRIMARY KEY  (`id`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

        try {
            allocateTableField(createCommand, tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDBTable() {
        //подготавливаю запрос на DROP Table
        System.out.println("Для удаления таблицы введите её название(books):");
        String tableName = sc.nextLine();
        String createCommand = ("DROP TABLE " + tableName);

        //передаю БД команду на удаление таблицы (пред запрос + название)
        try {
            Statement stDel = sqlConnection.createStatement();
            stDel.execute(createCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> result = new ArrayList<Book>();
        try {
            ResultSet rs = sqlConnection.createStatement().executeQuery("SELECT * FROM books");
            while (rs.next()) {
                result.add(allocateBookFields(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Book getBookFromId(String id) {
        Book result = new Book();
        if (id == null || "".equals(id)) return result;
        try {
            ResultSet rs = sqlConnection.createStatement().executeQuery("SELECT * FROM books WHERE book_id = " + id);
            result = allocateBookFields(rs);
        } catch (SQLException e) {
            System.err.println("getBookFromId WARNING!! " + e.getStackTrace());
            return result;
        }
        return result;
    }

    public Tag getTagFromId(String id) {
        return null;
    }

    public void insertNewBook(Book book) {
        //найденная и расшифрованная книга на ПК передается в prepInsert
        String createCommand = "insert into bookstorepro.books (author, year, name, language, path, type, format, description, size) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);
            preStatement.setString(1,book.getAuthor());
            preStatement.setInt(2, book.getYear());
            preStatement.setString(3, book.getName());
            preStatement.setString(4, book.getLanguage());
            preStatement.setString(5, book.getPath());
            preStatement.setString(6,book.getType());
            preStatement.setString(7,book.getFormat());
            preStatement.setString(8,book.getDescription());
            preStatement.setInt(9,book.getSize());
            preStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewTag(Tag tag) {

    }

    public void updateBook(Book book) {
        //определяю книгу для обновления по ID таблицы БД
        int ID = book.getId();

        //заполняю обновляемые поля книги
        System.out.println("Введите автора книги:");
        String author = book.setAuthor(sc.nextLine());
        System.out.println("Введите год издания книги:");
        int year = book.setYear(Integer.parseInt(sc.nextLine()));
        System.out.println("Введите название книги:");
        String title = book.setName(sc.nextLine());
        System.out.println("Введите язык книги:");
        String language = book.setLanguage(sc.nextLine());
        System.out.println("Введите path книги:");
        String path = book.setPath(sc.nextLine());
        System.out.println("Введите type книги:");
        String type = book.setType(sc.nextLine());
        System.out.println("Введите тип (pdf, djvu...) книги:");
        String format = book.setFormat(sc.nextLine());
        System.out.println("Введите description книги:");
        String description = book.setDescription(sc.nextLine());
        System.out.println("Введите размер книги:");
        int size = book.setSize(Integer.parseInt(sc.nextLine()));

        //передаю запрос обновить книгу (поле книги)
        String createCommand = "insert into bookstorepro.books (author, year, name, language, path, type, format, description, size) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);
            preStatement.setString(1,book.getAuthor());
            preStatement.setInt(2, book.getYear());
            preStatement.setString(3, book.getName());
            preStatement.setString(4, book.getLanguage());
            preStatement.setString(5, book.getPath());
            preStatement.setString(6,book.getType());
            preStatement.setString(7,book.getFormat());
            preStatement.setString(8,book.getDescription());
            preStatement.setInt(9,book.getSize());
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTag(Tag tag) {

    }

    public void deleteBook(String Id) {

        //передаю запрос
        String createCommand = "DELETE FROM bookstore.books WHERE id=?";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);;
            preStatement.setString(1,Id);
            preStatement.execute();
            System.out.println("Запись с id: " + Id + " успешно удалена из bookstore.books");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteTag(String Id) {

    }

    public void readDB() {

        //вывожу все имеющиеся БД на сервере
        try (Statement st = sqlConnection.createStatement()) {
            System.out.println("Читаю сервер mysql:" + st.execute("SHOW DATABASES")); //нужно вывести на экран список БД
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //подготавливаю запрос
        System.out.println("Какую БД читать (bookstore):");
        String dbName = sc.nextLine();
        System.out.println("Какую таблицу БД:" + dbName + " читать (books):");
        String tableDBName = sc.nextLine();

        //запрос в БД на вывод всех элементов таблицы
        String query = "select * from " + dbName + "." + tableDBName;
        ResultSet resultSet;

        ArrayList<Book> books = new ArrayList<>();

        try (Statement st = sqlConnection.createStatement()){
            //System.out.println(DBConnectorObject.getConnection() + " 174");
            resultSet = st.executeQuery(query);

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setAuthor(resultSet.getString("author"));
                book.setYear(resultSet.getInt("year"));
                book.setName(resultSet.getString("name"));
                book.setLanguage(resultSet.getString("language"));
                book.setPath(resultSet.getString("path"));
                book.setType(resultSet.getString("type"));
                book.setFormat(resultSet.getString("format"));
                book.setDescription(resultSet.getString("desctiption"));
                book.setSize(resultSet.getInt("size"));
                books.add(book);
                System.out.println("Читаю таблицу: " + tableDBName + " из БД: " + dbName);
                System.out.println(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*for (Book b: books){
            System.out.println(b);
        }*/
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


    ////////   SERVICE METHODS //////
    private static Book allocateBookFields(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.id = rs.getInt("book_id");
        book.name = rs.getString("bookName");
        book.author = rs.getString("bookAuthor");
        book.language = rs.getString("bookLanguage");
        book.type = rs.getString("bookType");
        book.format = rs.getString("bookFormat");
        book.path = rs.getString("bookPath");
        book.description = rs.getString("bookDescription");
        book.year = Integer.valueOf(rs.getString("bookYear"));
        book.size = Integer.valueOf(rs.getString("bookSize"));
        return book;
    }

    private static void allocateTableField (String createCommand, String tableName) throws SQLException {
        try {
            Statement stCR = sqlConnection.createStatement();
            stCR.execute(createCommand);
            System.out.println("Таблица с именем: " + tableName + " создана успешно.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    ////////   SERVICE METHODS //////
}

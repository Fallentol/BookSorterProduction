package dataBaseUtils;


import essence.Book;
import essence.Tag;
import interfase.mySQLhandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLUtils implements mySQLhandler {

    private static Connection sqlConnection;
    private static final String sqlHost = "jdbc:mysql://localhost/booksorterpro?user=admin&password=214926341&useSSL=true";

    static {
        try {
            sqlConnection = DriverManager.getConnection(sqlHost);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void refreshLocalRoot () {
        //задаю пароль для локального root пустого сервера MySQL для дальнейшей работы
        try {
            //исполняющий файл
            String contentCMD = "cd C:/Program Files/MySQL/MySQL Server 5.7/bin/" +
                    "mysqld --init-file=C:/Users/All Users/Temp/SQL.txt"; //два запроса в одном CMD????????????
            String pathNameCMD = "C:/Users/All Users/Temp/rootpass.bat";

            File CMDfile = new File(pathNameCMD);
            // если нет файла, то создаю его
            if (!CMDfile.exists()) {
                CMDfile.createNewFile();
                System.out.println("Файл обновления пароля root создан");
            }

            FileWriter fwCMD = new FileWriter(CMDfile.getAbsoluteFile());
            BufferedWriter bwCMD = new BufferedWriter(fwCMD);
            bwCMD.write(contentCMD);
            bwCMD.flush();
            bwCMD.close();
            System.out.println("Изменения в исполняющий файл обновления пароля root внесены");


            //файл SQL запроса
            String contentSQL = "SET PASSWORD FOR 'root'@'localhost' = PASSWORD('root');";
            String pathNameSQL = "C:/Users/All Users/Temp/SQL.txt";

            File SQLfile = new File(pathNameSQL);
            // если нет файла, то создаю его
            if (!SQLfile.exists()) {
                SQLfile.createNewFile();
                System.out.println("Файл SQL-запроса создан");
            }

            FileWriter fwSQL = new FileWriter(SQLfile.getAbsoluteFile());
            BufferedWriter bwSQL = new BufferedWriter(fwSQL);
            bwSQL.write(contentSQL);
            bwSQL.flush();
            bwSQL.close();
            System.out.println("Изменения в файл SQL-запроса внесены");

            //нужно остановить службу MySQL для подмены пароля root
            String pathNameStopS = "C:/Users/All Users/Temp/stopS.bat";
            String stopService = "net stop MySQL57";
            File stopSfile = new File(pathNameStopS);
            // если нет файла, то создаю его
            if (!stopSfile.exists()) {
                stopSfile.createNewFile();
                System.out.println("Файл остановки службы MySQL создан");
            }

            FileWriter fwStopS = new FileWriter(stopSfile.getAbsoluteFile());
            BufferedWriter bwStopS = new BufferedWriter(fwStopS);
            bwStopS.write(stopService);
            bwStopS.flush();
            bwStopS.close();
            System.out.println("Изменения в файл остановки службы внесены");

            //запускаю stopS
            new ProcessBuilder("cmd", "start", pathNameStopS).start();
            Runtime.getRuntime().exec("cmd /c " + pathNameStopS);
            System.out.println("Службу MySQL57 остановил");

            //запускаю CMD
            new ProcessBuilder("cmd", "start", pathNameCMD).start();
            Runtime.getRuntime().exec("cmd /c " + pathNameCMD);
            System.out.println("Батник выполнил");

            //удаляю исполняющий файл rootpass.bat
            if(CMDfile.delete()){
                System.out.println("temp/rootpass.bat файл был удален");
            }else System.out.println("Файл temp/rootpass.bat не был найден");

            //удаляю файл запроса SQL.txt
            if(SQLfile.delete()){
                System.out.println("temp/SQL.txt файл был удален");
            }else System.out.println("Файл temp/SQL.txt не был найден");

            //удаляю файл остановки службы stopS.bat
            if(stopSfile.delete()){
                System.out.println("temp/stopS.bat файл был удален");
            }else System.out.println("Файл temp/stopS.bat не был найден");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createUserAP(String userName, int userPass) {
        //создаю нового пользователя и пароль
        userName = "admin";
        userPass = 214926341;
        String createCommandUser = "CREATE USER '" + userName + "'@'localhost' IDENTIFIED BY '" + userPass +"';";

        try {
            Statement stCR = sqlConnection.createStatement();
            stCR.execute(createCommandUser);
            System.out.println("пользователь '" + userName + "' с паролем " + userPass + "' успешно добавлен!" );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //раздаю права
        String createCommandPrivileges = "GRANT ALL PRIVILEGES ON * . * TO 'newuser'@'localhost';";

        try {
            Statement stCR = sqlConnection.createStatement();
            stCR.execute(createCommandPrivileges);
            System.out.println("Права пользователю '" + userName + " успешно назначены!" );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //обновляю права
        String createCommandFlush = "FLUSH PRIVILEGES;";

        try {
            Statement stCR = sqlConnection.createStatement();
            stCR.execute(createCommandFlush);
            System.out.println("Права пользователей успешно обновлены!" );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDB(String dbName) {
        //подготавливаю запрос на создание БД
        dbName = "BookSorterPro";
        String createCommand = "CREATE DATABASE '" + dbName + "' CHARACTER SET utf8 COLLATE utf8_general_ci";

        try {
            Statement stCR = sqlConnection.createStatement();
            stCR.execute(createCommand);
            System.out.println("База данных: " + dbName + " создана успешно.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDB(String dbName) {
        //подготавливаю запрос на удаление БД
        dbName = "BookSorterPro";
        String createCommand = "DROP DATABASE " + dbName;

        try {
            Statement stCR = sqlConnection.createStatement();
            stCR.execute(createCommand);
            System.out.println("База данных: " + dbName + " удалена успешно.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDBTableBooks(String tableName) {
        //подготавливаю запрос на создание таблицы
        tableName = "books";

        String createCommand = "CREATE TABLE " + tableName + " (" +
                "  `id` int(11) NOT NULL auto_increment," +
                "  `author` varchar(50) default NULL," +
                "  `year` int(11) default NULL," +
                "  `name` varchar(45) default NULL," +
                "  `path` varchar(45) default NULL," +
                "  `type` varchar(45) default NULL," +
                "  `format` varchar(45) default NULL," +
                "  `description` varchar(45) default NULL," +
                "  `size` int(11) default NULL," +
                "  PRIMARY KEY  (`id`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

        try {
            allocateTableField(createCommand, tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDBTableTags(String tableName) {
        //подготавливаю запрос на создание таблицы
        tableName = "tags";

        String createCommand = "CREATE TABLE " + tableName + " (" +
                "  `id` int(11) NOT NULL auto_increment," +
                "  `name` varchar(50) default NULL," +
                "  `parent` varchar(45) default NULL," +
                "  PRIMARY KEY  (`id`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

        try {
            allocateTableField(createCommand, tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDBTableLinks(String tableName) {
        //подготавливаю запрос на создание таблицы
        tableName = "links";

        String createCommand = "CREATE TABLE " + tableName + " (" +
                "  `id` int(11) NOT NULL auto_increment," +
                "  `tag_id` int(11) default NULL," +
                "  `book_id` int(11) default NULL," +
                "  PRIMARY KEY  (`link_id`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

        try {
            allocateTableField(createCommand, tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDBTable(String tableName) {
        //подготавливаю запрос на DROP Table
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
            System.err.println("insertNewBook WARNING!! " + e.getStackTrace());
        }
    }

    public void insertNewTag(Tag tag) {

    }

    public void updateBook(Book book) {
        //определяю книгу для обновления по ID таблицы БД
        int ID = book.getId();

        //заполняю обновляемые поля книги
        String author = book.getAuthor();
        int year = book.getYear();
        String name = book.getName();
        String language = book.getLanguage();
        String path = book.getPath();
        String type = book.getType();
        String format = book.getFormat();
        String description = book.getDescription();
        int size = book.getSize();

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

    public void readDB(String dbName, String tableName) {

        //вывожу все имеющиеся БД на сервере
        try (Statement st = sqlConnection.createStatement()) {
            System.out.println("Читаю сервер mysql:" + st.execute("SHOW DATABASES")); //нужно вывести на экран список БД
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //подготавливаю запрос
        System.out.println("Читаю таблицу: " + tableName + " БД: " + dbName);

        //запрос в БД на вывод всех элементов таблицы
        String query = "select * from " + dbName + "." + tableName;
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
                System.out.println("Читаю таблицу: " + tableName + " из БД: " + dbName);
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

package dataBaseUtils;


import essence.Book;
import essence.Link;
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
    private static final String sqlHost = "jdbc:mysql://localhost/mysql?user=admin&password=214926341&useSSL=true";

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
        String createCommandPrivileges = "GRANT ALL PRIVILEGES ON * . * TO '" + userName + "'@'localhost';";

        try {
            Statement stCR = sqlConnection.createStatement();
            stCR.execute(createCommandPrivileges);
            System.out.println("Права 'All Privileges' пользователю '" + userName + " успешно назначены!" );
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
        //dbName = "BookSorterPro";
        String createCommand = "CREATE DATABASE " + dbName + " CHARACTER SET utf8 COLLATE utf8_general_ci";

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
        //dbName = "BookSorterPro";
        String createCommand = "DROP DATABASE " + dbName;

        try {
            Statement stCR = sqlConnection.createStatement();
            stCR.execute(createCommand);
            System.out.println("База данных: " + dbName + " удалена успешно.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDBTableBooks(String dbName, String tableName) {
        //подготавливаю запрос на создание таблицы
        dbName = "BookSorterPro";
        //tableName = "books";

        String createCommand = "CREATE TABLE " + dbName + "." + tableName + " (" +
                "  `book_id` int(11) NOT NULL auto_increment," +
                "  `bookName` varchar(255) default NULL," +
                "  `bookAuthor` varchar(255) default NULL," +
                "  `bookLanguage` varchar(45) default NULL," +
                "  `bookType` varchar(45) default NULL," +
                "  `bookFormat` varchar(45) default NULL," +
                "  `bookPath` varchar(45) default NULL," +
                "  `bookDescription` varchar(45) default NULL," +
                "  `bookYear` int(11) default NULL," +
                "  `bookSize` int(11) default NULL," +
                "  PRIMARY KEY  (`book_id`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

        try {
            allocateTableField(createCommand, tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDBTableTags(String dbName, String tableName) {
        //подготавливаю запрос на создание таблицы
        dbName = "BookSorterPro";
        //tableName = "tags";

        String createCommand = "CREATE TABLE " + dbName + "." + tableName + " (" +
                "  `tag_id` int(11) NOT NULL auto_increment," +
                "  `tagName` varchar(50) default NULL," +
                "  `tagParent` varchar(45) default NULL," +
                "  PRIMARY KEY  (`tag_id`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

        try {
            allocateTableField(createCommand, tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDBTableLinks(String dbName, String tableName) {
        //подготавливаю запрос на создание таблицы
        dbName = "BookSorterPro";
        //tableName = "links";

        String createCommand = "CREATE TABLE " + dbName + "." + tableName + " (" +
                "  `link_id` int(11) NOT NULL auto_increment," +
                "  `tagId` int(11) default NULL," +
                "  `bookId` int(11) default NULL," +
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
        Tag result = new Tag();
        if (id == null || "".equals(id)) return result;
        try {
            ResultSet rs = sqlConnection.createStatement().executeQuery("SELECT * FROM books WHERE tag_id = " + id);
            result = allocateTagFields(rs);
        } catch (SQLException e) {
            System.err.println("getTagFromId WARNING!! " + e.getStackTrace());
            return result;
        }
        return result;
    }

    public Link getLinkFromId(String id) {
        Link result = new Link();
        if (id == null || "".equals(id)) return result;
        try {
            ResultSet rs = sqlConnection.createStatement().executeQuery("SELECT * FROM books WHERE link_id = " + id);
            result = allocateLinkFields(rs);
        } catch (SQLException e) {
            System.err.println("getLinkFromId WARNING!! " + e.getStackTrace());
            return result;
        }
        return result;
    }

    public void insertNewBook(Book book) {
        //найденная и расшифрованная книга на ПК передается в prepInsert
        String createCommand = "insert into BookStorePro.books (bookName, bookAuthor, bookLanguage, bookType, bookFormat, bookPath, bookDescription, bookYear, bookSize) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);
            preStatement.setString(1, book.getName());
            preStatement.setString(2,book.getAuthor());
            preStatement.setString(3, book.getLanguage());
            preStatement.setString(4,book.getType());
            preStatement.setString(5,book.getFormat());
            preStatement.setString(6, book.getPath());
            preStatement.setString(7,book.getDescription());
            preStatement.setInt(8, book.getYear());
            preStatement.setInt(9,book.getSize());
            preStatement.execute();
        } catch (SQLException e) {
            System.err.println("insertNewBook WARNING!! " + e.getStackTrace());
        }
    }

    public void insertNewTag(Tag tag) {
        //навый Tag передается в prepInsert
        String createCommand = "insert into BookStorePro.Tags (tagName, tagParent) values (?, ?)";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);
            preStatement.setString(1,tag.getName());
            preStatement.setInt(2, tag.getParent());
            preStatement.execute();
        } catch (SQLException e) {
            System.err.println("insertNewTag WARNING!! " + e.getStackTrace());
        }
    }

    public void insertNewLink(Link link) {
        //навый Link передается в prepInsert
        String createCommand = "insert into BookStorePro.Links (tagId, bookId) values (?, ?)";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);
            preStatement.setInt(1,link.getTag_id());
            preStatement.setInt(2,link.getBook_id());
            preStatement.execute();
        } catch (SQLException e) {
            System.err.println("insertNewLink WARNING!! " + e.getStackTrace());
        }
    }

    public void updateBook(Book book) {
        //передаю запрос обновить книгу (поле книги)
        String createCommand = "insert into BookStorePro.books (book_id, bookName, bookAuthor, bookLanguage, bookType, bookFormat, bookPath, bookDescription, bookYear, bookSize) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);
            preStatement.setInt(1, book.getId());
            preStatement.setString(2, book.getName());
            preStatement.setString(3,book.getAuthor());
            preStatement.setString(4, book.getLanguage());
            preStatement.setString(5,book.getType());
            preStatement.setString(6,book.getFormat());
            preStatement.setString(7, book.getPath());
            preStatement.setString(8,book.getDescription());
            preStatement.setInt(9, book.getYear());
            preStatement.setInt(10,book.getSize());
            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updateBook WARNING!! " + e.getStackTrace());
        }

    }

    public void updateTag(Tag tag) {
        //передаю запрос обновить книгу (поле книги)
        String createCommand = "insert into BookStorePro.Tags (tag_id, tagName, tagParent) values (?, ?, ?)";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);
            preStatement.setInt(1, tag.getId()); //определяю элемент для обновления по ID таблицы БД
            preStatement.setString(2,tag.getName());
            preStatement.setInt(3, tag.getParent());
            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updateTag WARNING!! " + e.getStackTrace());
        }
    }

    public void updateLink(Link link) {
        //передаю запрос обновить книгу (поле книги)
        String createCommand = "insert into BookStorePro.Links (link_id, tagId, bookId) values (?, ?, ?)";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);
            preStatement.setInt(1, link.getId()); //определяю элемент для обновления по ID таблицы БД
            preStatement.setInt(2,link.getTag_id());
            preStatement.setInt(3,link.getBook_id());
            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updateLink WARNING!! " + e.getStackTrace());
        }
    }

    public void deleteBook(String Id) {
        //передаю запрос
        String createCommand = "DELETE FROM BookSorterPro.Books WHERE id=?";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);;
            preStatement.setString(1,Id);
            preStatement.execute();
            System.out.println("Book с id: " + Id + " успешно удалена из bookstore.books");
        } catch (SQLException e) {
            System.err.println("deleteBook WARNING!! " + e.getStackTrace());
        }

    }

    public void deleteTag(String Id) {
        //передаю запрос
        String createCommand = "DELETE FROM BookBorterPro.Tags WHERE id=?";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);;
            preStatement.setString(1,Id);
            preStatement.execute();
            System.out.println("Tag с id: " + Id + " успешно удален из BookSorterPro.Tags");
        } catch (SQLException e) {
            System.err.println("deleteTag WARNING!! " + e.getStackTrace());
        }
    }

    public void deleteLink(String Id) {
        //передаю запрос
        String createCommand = "DELETE FROM BookBorterPro.Links WHERE id=?";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);;
            preStatement.setString(1,Id);
            preStatement.execute();
            System.out.println("Link с id: " + Id + " успешно удален из BookSorterPro.Links");
        } catch (SQLException e) {
            System.err.println("deleteLink WARNING!! " + e.getStackTrace());
        }
    }

    public void readDB(String dbName, String tableName) {
        dbName = "BookSorterPro";
        tableName = "Books";

        if (tableName == "Books") {
            try (Statement st = sqlConnection.createStatement()) {
                ResultSet resultSet;
                resultSet = st.executeQuery("SELECT * FROM " + dbName + "." + tableName);
                while (resultSet.next()) {
                    Book book = new Book();
                    book.id = resultSet.getInt("book_id");
                    book.name = resultSet.getString("bookName");
                    book.author = resultSet.getString("bookAuthor");
                    book.language = resultSet.getString("bookLanguage");
                    book.type = resultSet.getString("bookType");
                    book.format = resultSet.getString("bookFormat");
                    book.path = resultSet.getString("bookPath");
                    book.description = resultSet.getString("bookDescription");
                    book.year = Integer.valueOf(resultSet.getString("bookYear"));
                    book.size = Integer.valueOf(resultSet.getString("bookSize"));
                    System.out.println("Читаю таблицу: " + tableName + " из БД: " + dbName);
                    System.out.println(book);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (tableName == "Tags") {
            try (Statement st = sqlConnection.createStatement()) {
                ResultSet resultSet;
                resultSet = st.executeQuery("SELECT * FROM " + dbName + "." + tableName);
                while (resultSet.next()) {
                    Tag tag = new Tag();
                    tag.id = resultSet.getInt("tag_id");
                    tag.name = resultSet.getString("tagName");
                    tag.parent = resultSet.getInt("tagParent");
                    System.out.println("Читаю таблицу: " + tableName + " из БД: " + dbName);
                    System.out.println(tag);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (tableName == "links") {
            try (Statement st = sqlConnection.createStatement()){
                ResultSet resultSet;
                resultSet = st.executeQuery("SELECT * FROM " + dbName + "." + tableName);
                while (resultSet.next()) {
                    Link link = new Link();
                    link.id = resultSet.getInt("link_id");
                    link.tag_id = resultSet.getInt("tagId");
                    link.book_id = resultSet.getInt("bookId");
                    System.out.println("Читаю таблицу: " + tableName + " из БД: " + dbName);
                    System.out.println(link);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Такой БД пока не создано!");
        }

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

    private static Tag allocateTagFields(ResultSet rs) throws SQLException {
        Tag tag = new Tag();
        tag.id = rs.getInt("tag_id");
        tag.name = rs.getString("tagName");
        tag.parent = rs.getInt("tagParent");
        return tag;
    }

    private static Link allocateLinkFields(ResultSet rs) throws SQLException {
        Link link = new Link();
        link.id = rs.getInt("link_id");
        link.tag_id = rs.getInt("tagId");
        link.book_id = rs.getInt("bookId");
        return link;
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

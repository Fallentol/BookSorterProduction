package dataBaseUtils;


import config.Configurator;
import essence.Book;
import essence.Link;
import essence.Tag;
import essence.UserProfile;
import interfase.mySQLhandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static config.Configurator.*;

public class SQLUtils implements mySQLhandler {

    public static Connection sqlConnection;

    public static Map<String, Integer> fileBaseIdMap; // мапа, где ключ -путь к файлу, а значение Айдишник book из базы данных
    public static int bookQuantity = 0;

    public static void initFileBaseIdMap() {
        try {
            ResultSet rs = sqlConnection.createStatement().executeQuery("SELECT book_Id, bookPath FROM books");
            fileBaseIdMap = new HashMap<>();
            bookQuantity = 0;
            while (rs.next()) {
                String bookPA = rs.getString("bookPath");
                int bookId = rs.getInt("book_Id");
                fileBaseIdMap.put(bookPA, bookId);
                bookQuantity++;
            }
        } catch (Exception e) {
            System.out.println("ОШИБКА НАПОЛНЕНИЯ МАПЫ");
            e.printStackTrace();
        }
    }

    static {

        try {
            Class.forName("com.mysql.jdbc.Driver"); // в загрузчик попадает класс из драйвера. Драйвер скачивается и устанавливается бибиотекой к проекту
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ОШИБКА ЗАГРУЗКИ ДРАЙВЕРА");
        }
        try {
            sqlConnection = DriverManager.getConnection("jdbc:mysql://" + Configurator.serverURL + "/" + baseName + "?user=" + userName + "&password=" + userPass + "&useSSL=true");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ОШИБКА ПОДКЛЮЧЕНИЯ К БД");
        }
        initFileBaseIdMap();
    }

    public static String testConnection() {
        String result = "All parameters are correct";
        try {
            Class.forName("com.mysql.jdbc.Driver"); // в загрузчик попадает класс из драйвера. Драйвер скачивается и устанавливается бибиотекой к проекту
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            return "DataBase is not installed";
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + Configurator.serverURL + "/" + baseName + "?user=" + userName + "&password=" + userPass + "&useSSL=true");
        } catch (SQLException e) {
            //e.printStackTrace();
            return "User's Name or Password are not valid";
        }
        return result;
    }


    public static int getUserIdFromName(String userName) {
        int user_id = 0;
        String query = "SELECT user_id FROM sys.UserList WHERE userName = '" + userName + "'";
        try {
            Statement stID = sqlConnection.createStatement();
            stID.execute(query);
        } catch (SQLException e) {
            System.err.println("getUserIdFromName WARNING!! " + e.getStackTrace());
        }
        return user_id;
    }

    public static ArrayList<String> collectorUserPath(String userName) {
        ArrayList<String> results = new ArrayList<>();
        String query = "SELECT profPath FROM sys.UserProfile WHERE prof_id = '" + getUserIdFromName(userName) + "'";
        try {
            ResultSet rs = sqlConnection.createStatement().executeQuery(query);
            while (rs.next()) {
                results.add(rs.getString(1)); //нужно проверить columnIndex
            }
        } catch (SQLException e) {
            System.err.println("collectorUserPath WARNING!! " + e.getStackTrace());
        }
        return results;
    }

    public void refreshLocalRoot() {
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
            String contentSQL = "SET PASSWORD FOR 'root'@'" + Configurator.serverURL + "' = PASSWORD('root');";
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
            if (CMDfile.delete()) {
                System.out.println("temp/rootpass.bat файл был удален");
            } else System.out.println("Файл temp/rootpass.bat не был найден");

            //удаляю файл запроса SQL.txt
            if (SQLfile.delete()) {
                System.out.println("temp/SQL.txt файл был удален");
            } else System.out.println("Файл temp/SQL.txt не был найден");

            //удаляю файл остановки службы stopS.bat
            if (stopSfile.delete()) {
                System.out.println("temp/stopS.bat файл был удален");
            } else System.out.println("Файл temp/stopS.bat не был найден");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String createUserAP(String userName, String userPass) {
        String result = "New user added";
        //создаю нового пользователя и пароль
        String createCommandUser = "CREATE USER '" + userName + "'@'" + Configurator.serverURL + "' IDENTIFIED BY '" + userPass + "';";

        try {
            Statement stCR = sqlConnection.createStatement();
            stCR.execute(createCommandUser);
            System.out.println("пользователь '" + userName + "' с паролем " + userPass + "' успешно добавлен!");
        } catch (SQLException e) {
            e.printStackTrace();
            return "User's Name or Password are not valid";
        }

        //раздаю права
        String createCommandPrivileges = "GRANT ALL PRIVILEGES ON * . * TO '" + userName + "'@'" + Configurator.serverURL + "';";

        try {
            Statement stCR = sqlConnection.createStatement();
            stCR.execute(createCommandPrivileges);
            System.out.println("Права 'All Privileges' пользователю '" + userName + " успешно назначены!");
        } catch (SQLException e) {
            e.printStackTrace();
            return "Can`t grant AP for new User";
        }

        //обновляю права
        String createCommandFlush = "FLUSH PRIVILEGES;";

        try {
            Statement stCR = sqlConnection.createStatement();
            stCR.execute(createCommandFlush);
            System.out.println("Права пользователей успешно обновлены!");
        } catch (SQLException e) {
            e.printStackTrace();
            return "Flush is not done";
        }
//        ////////////////////////////////////////User DB//////////////////////////////////////////
//        //добавляю запись в БД с пользователем
//        String createCommandList = "insert into sys.UserList (userName, userPass) values (?, ?)";
//        try {
//            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommandList);
//            preStatement.setString(1, userName);
//            preStatement.setString(2, userPass);
//            preStatement.execute();
//            preStatement.close();
//
//            String query = "SELECT user_id FROM sys.UserList WHERE userName = '" + userName + "'";
//            ResultSet rs = sqlConnection.createStatement().executeQuery(query);
//            int prof_id = 0;
//            while (rs.next()) {
//                prof_id = rs.getInt("user_id");
//            }
//            insertUserProfile(prof_id, userPath);
//        } catch (SQLException e) {
//            System.err.println("insertNewUser WARNING!! " + e.getStackTrace());
//            return "Can`t insert to sys.UserList new User. Table is created?";
//        }
//        //////////////////////////////////////////////////////////////////////////////////////////
        return result;
    }

    public String insertUserProfile(String userName, String profPath) {
        String result = "User`s profile inserted";
        String createCommandProfile = "insert into sys.UserProfile (prof_id, profPath) values (?, ?)";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommandProfile);
            preStatement.setInt(1, getUserIdFromName(userName));
            preStatement.setString(2, profPath);
            preStatement.execute();
            preStatement.close();
        } catch (SQLException e) {
            System.err.println("insertNewUser WARNING!! " + e.getStackTrace());
            return "Can`t insert to sys.UserProfile new userPath. Table is created?";
        }
        return result;
    }

    public void createAdminServer(String serverURL, String userName, String userPass) {
        //создаю нового пользователя и пароль
        String createCommandUser = "CREATE USER '" + userName + "'@'" + Configurator.serverURL + "' IDENTIFIED BY '" + userPass + "';";

        try {
            Statement stCR = sqlConnection.createStatement();
            stCR.execute(createCommandUser);
            System.out.println("АДМИН '" + Configurator.userName + "' с паролем " + Configurator.userPass + "' успешно добавлен!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("User's Name or Password are not valid");
        }

        //раздаю права
        String createCommandPrivileges = "GRANT ALL PRIVILEGES ON * . * TO '" + userName + "'@'" + Configurator.serverURL + "';";

        try {
            Statement stCR = sqlConnection.createStatement();
            stCR.execute(createCommandPrivileges);
            System.out.println("Права 'All Privileges' АДМИНУ '" + userName + " успешно назначены!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can`t grant AP for new User");
        }

        //обновляю права
        String createCommandFlush = "FLUSH PRIVILEGES;";

        try {
            Statement stCR = sqlConnection.createStatement();
            stCR.execute(createCommandFlush);
            System.out.println("Права АДМИНА успешно обновлены!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Flush is not done");
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

    public void createSYSTableUserData() {
        //таблица списка пользователей
        final String dbName = "sys"; //системная БД, живет уже при создании сервера
        final String tableNameList = "UserList";
        String createCommandList = "CREATE TABLE " + dbName + "." + tableNameList + " (" +
                "  `user_id` int(11) NOT NULL auto_increment," +
                "  `userName` varchar(255) default NULL," +
                "  `userPass` varchar(255) default NULL," +
                "  PRIMARY KEY  (`user_id`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        try {
            allocateTableField(createCommandList, tableNameList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //таблица данных пользователей
        final String tableNameProf = "UserProfile";
        String createCommandProf = "CREATE TABLE " + dbName + "." + tableNameProf + " (" +
                "  `id` int(11) NOT NULL auto_increment," +
                "  `prof_id` int(11) default NULL," +
                "  `profPath` varchar(255) default NULL," +
                "  PRIMARY KEY  (`id`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        try {
            allocateTableField(createCommandProf, tableNameProf);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //таблица создана, нужно условие для проверки существования таблицы
    }

    public void createDBTableBooks() {
        //подготавливаю запрос на создание таблицы
        String dbName = "BookSorterPro";
        String tableName = "books";

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

    public void createDBTableTags() {
        //подготавливаю запрос на создание таблицы
        String dbName = "BookSorterPro";
        String tableName = "tags";

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

    public void createDBTableLinks() {
        //подготавливаю запрос на создание таблицы
        String dbName = "BookSorterPro";
        String tableName = "links";

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
            while (rs.next()) {
                result = allocateBookFields(rs);
            }
        } catch (SQLException e) {
            System.err.println("getBookFromId WARNING!! " + e.getStackTrace());
            return result;
        }
        return result;
    }

    public ArrayList<Book> getBooksFromTagIds(Set<Integer> tagIds) {
        ArrayList<Book> result = new ArrayList<Book>();
        if (tagIds == null || tagIds.size() == 0) return result;

        Set<Integer> bookIds = new TreeSet<>();
        String whereString = prepareString(tagIds, "tagId");
        try {
            ResultSet rs = sqlConnection.createStatement().executeQuery("SELECT * FROM links " + whereString);
            while (rs.next()) {
                bookIds.add(rs.getInt("bookId"));
            }
            whereString = prepareString(bookIds, "book_id");
            rs = sqlConnection.createStatement().executeQuery("SELECT * FROM books " + whereString);
            while (rs.next()) {
                result.add(allocateBookFields(rs));
            }
        } catch (SQLException e) {
            System.err.println("getBookFromId WARNING!! " + e.getStackTrace());
            return result;
        }
        return result;
    }

    public ArrayList<Book> getBooksFromName(String bookName) {
        ArrayList<Book> result = new ArrayList<Book>();
        if (bookName == null || bookName.equals("")) return result;

        Set<Integer> bookIds = new TreeSet<>();
        try {
            ResultSet rset = sqlConnection.createStatement().executeQuery("SELECT * FROM books WHERE bookName LIKE '%" + bookName + "%'");
            while (rset.next()) {
                result.add(allocateBookFields(rset));
            }
        } catch (SQLException e) {
            System.err.println("getBookFromId WARNING!! " + e.getStackTrace());
            return result;
        }
        return result;
    }

    public ArrayList<Tag> getAllTags() {
        ArrayList<Tag> result = new ArrayList<>();
        try {
            ResultSet rs = sqlConnection.createStatement().executeQuery("SELECT * FROM tags ORDER BY tagName");
            System.out.println("RS=" + rs);
            while (rs.next()) {
                result.add(allocateTagFields(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Set<Integer> getTagChildren(Set<Integer> result) {
        if (result == null || result.size() == 0) return new TreeSet<>();

        String whereString = "WHERE";
        for (Integer i : result) {
            if (!"WHERE".equals(whereString)) whereString += " OR ";
            whereString += " tagParent = " + i;
        }
        System.out.println("whereString=" + whereString);
        try {
            ResultSet rs = sqlConnection.createStatement().executeQuery("SELECT * FROM tags " + whereString + " ORDER BY tagName");
            System.out.println("RS=" + rs);
            while (rs.next()) {
                result.add(rs.getInt("tag_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Tag getTagFromId(String id) {
        Tag result = new Tag();
        if (id == null || "".equals(id)) return result;
        try {
            ResultSet rs = sqlConnection.createStatement().executeQuery("SELECT * FROM tags WHERE tag_id = " + id);
            while (rs.next()) {
                result = allocateTagFields(rs);
            }
        } catch (SQLException e) {
            System.err.println("getTagFromId WARNING!! " + e.toString());
            return result;
        }
        return result;
    }

    public Link getLinkFromId(String id) {
        Link result = new Link();
        if (id == null || "".equals(id)) return result;
        try {
            ResultSet rs = sqlConnection.createStatement().executeQuery("SELECT * FROM links WHERE link_id = " + id);
            result = allocateLinkFields(rs);
        } catch (SQLException e) {
            System.err.println("getLinkFromId WARNING!! " + e.getStackTrace());
            return result;
        }
        return result;
    }

    public ArrayList<Link> getLinkAttachedToBook(String bookId) {
        ArrayList<Link> result = new ArrayList<Link>();
        if (bookId == null || "".equals(bookId)) return result;
        try {
            ResultSet rs = sqlConnection.createStatement().executeQuery("SELECT * FROM links WHERE bookId = " + bookId);
            while (rs.next()) {
                result.add(allocateLinkFields(rs));
            }
        } catch (SQLException e) {
            System.err.println("getLinkAttachedToBook WARNING!! " + e.getStackTrace());
            return result;
        }
        return result;
    }

    public int insertNewBook(Book book) {
        //найденная и расшифрованная книга на ПК передается в prepInsert
        try {
            String query = "INSERT INTO " + Configurator.baseName + ".books (bookName, bookAuthor, bookLanguage, bookType, bookFormat, bookPath, bookDescription, bookYear, bookSize) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preStatement = sqlConnection.prepareStatement(query);
            preStatement.setString(1, book.getName());
            preStatement.setString(2, book.getAuthor());
            preStatement.setString(3, book.getLanguage());
            preStatement.setString(4, book.getType());
            preStatement.setString(5, book.getFormat());
            preStatement.setString(6, book.getPath());
            preStatement.setString(7, book.getDescription());
            preStatement.setInt(8, book.getYear());
            preStatement.setInt(9, book.getSize());
            preStatement.execute();
            preStatement.close();

            query = "SELECT book_id FROM " + Configurator.baseName + ".books WHERE bookPath = '" + book.getPath() + "'";
            ResultSet rs = sqlConnection.createStatement().executeQuery(query);
            while (rs.next()) {
                return rs.getInt("book_id");
            }
            return -1;
        } catch (SQLException e) {
            System.err.println("insertNewBook WARNING!! " + e.getStackTrace());
            return -1;
        }
    }

    public void insertNewTag(Tag tag) {
        //навый Tag передается в prepInsert
        String createCommand = "insert into " + Configurator.baseName + ".Tags (tagName, tagParent) values (?, ?)";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);
            preStatement.setString(1, tag.getName());
            preStatement.setInt(2, tag.getParent());
            preStatement.execute();
            preStatement.close();
        } catch (SQLException e) {
            System.err.println("insertNewTag WARNING!! " + e.toString());
        }
    }

    public void insertNewLink(Link link) {
        //навый Link передается в prepInsert
        String createCommand = "insert into " + Configurator.baseName + ".Links (tagId, bookId) values (?, ?)";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);
            preStatement.setInt(1, link.getTag_id());
            preStatement.setInt(2, link.getBook_id());
            preStatement.execute();
            preStatement.close();
        } catch (SQLException e) {
            System.err.println("insertNewLink WARNING!! " + e.getMessage());
        }
    }

    public void updateBook(Book book) {
        //передаю запрос обновить книгу (поле книги)
        String createCommand = "insert into " + Configurator.baseName + ".books (book_id, bookName, bookAuthor, bookLanguage, bookType, bookFormat, bookPath, bookDescription, bookYear, bookSize) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);
            preStatement.setInt(1, book.getId());
            preStatement.setString(2, book.getName());
            preStatement.setString(3, book.getAuthor());
            preStatement.setString(4, book.getLanguage());
            preStatement.setString(5, book.getType());
            preStatement.setString(6, book.getFormat());
            preStatement.setString(7, book.getPath());
            preStatement.setString(8, book.getDescription());
            preStatement.setInt(9, book.getYear());
            preStatement.setInt(10, book.getSize());
            preStatement.executeUpdate();
            preStatement.close();
        } catch (SQLException e) {
            System.err.println("updateBook WARNING!! " + e.getStackTrace());
        }

    }

    public void updateTag(Tag tag) {
        //передаю запрос обновить книгу (поле книги)
        String createCommand = "insert into " + Configurator.baseName + ".Tags (tag_id, tagName, tagParent) values (?, ?, ?)";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);
            preStatement.setInt(1, tag.getId()); //определяю элемент для обновления по ID таблицы БД
            preStatement.setString(2, tag.getName());
            preStatement.setInt(3, tag.getParent());
            preStatement.executeUpdate();
            preStatement.close();
        } catch (SQLException e) {
            System.err.println("updateTag WARNING!! " + e.getStackTrace());
        }
    }

    public void updateLink(Link link) {
        //передаю запрос обновить книгу (поле книги)
        String createCommand = "insert into " + Configurator.baseName + ".Links (link_id, tagId, bookId) values (?, ?, ?)";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);
            preStatement.setInt(1, link.getId()); //определяю элемент для обновления по ID таблицы БД
            preStatement.setInt(2, link.getTag_id());
            preStatement.setInt(3, link.getBook_id());
            preStatement.executeUpdate();
            preStatement.close();
        } catch (SQLException e) {
            System.err.println("updateLink WARNING!! " + e.getStackTrace());
        }
    }

    public void deleteBook(String Id) {
        //передаю запрос
        String createCommand = "DELETE FROM " + Configurator.baseName + ".books WHERE book_id=?";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);
            preStatement.setString(1, Id);
            preStatement.execute();
            preStatement.close();
            System.out.println("Book с id: " + Id + " успешно удалена из bookstore.books");
        } catch (SQLException e) {
            System.err.println("deleteBook WARNING!! " + e.getMessage());
        }

    }

    public void deleteTag(String Id) {
        //передаю запрос
        String createCommand = "DELETE FROM " + Configurator.baseName + ".Tags WHERE id=?";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);
            preStatement.setString(1, Id);
            preStatement.execute();
            preStatement.close();
            System.out.println("Tag с id: " + Id + " успешно удален из BookSorterPro.Tags");
        } catch (SQLException e) {
            System.err.println("deleteTag WARNING!! " + e.getStackTrace());
        }
    }

    public void deleteLink(String Id) {
        //передаю запрос
        String createCommand = "DELETE FROM " + Configurator.baseName + ".Links WHERE id=?";
        try {
            PreparedStatement preStatement = sqlConnection.prepareStatement(createCommand);
            ;
            preStatement.setString(1, Id);
            preStatement.execute();
            preStatement.close();
            System.out.println("Link с id: " + Id + " успешно удален из BookSorterPro.Links");
        } catch (SQLException e) {
            System.err.println("deleteLink WARNING!! " + e.getStackTrace());
        }
    }

    public void readDB(String dbName, String tableName) {
        //нужно сделать метод выводящим любой массив по определенному запросу!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //по входящим названию БД и таблице читаю
        if (tableName == "Books") {
            ArrayList<Book> result = new ArrayList<>();
            try {
                ResultSet resultSet = sqlConnection.createStatement().executeQuery("SELECT * FROM " + dbName + "." + tableName);
                while (resultSet.next()) {
                    result.add(allocateBookFields(resultSet));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (tableName == "Tags") {
            ArrayList<Tag> result = new ArrayList<>();
            try {
                ResultSet resultSet = sqlConnection.createStatement().executeQuery("SELECT * FROM " + dbName + "." + tableName);
                while (resultSet.next()) {
                    result.add(allocateTagFields(resultSet));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (tableName == "links") {
            ArrayList<Link> result = new ArrayList<>();
            try {
                ResultSet resultSet = sqlConnection.createStatement().executeQuery("SELECT * FROM " + dbName + "." + tableName);
                while (resultSet.next()) {
                    result.add(allocateLinkFields(resultSet));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Такой таблицы или БД еще не создано!");
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

    static void allocateTableField(String createCommand, String tableName) throws SQLException {
        try {
            Statement stCR = sqlConnection.createStatement();
            stCR.execute(createCommand);
            System.out.println("Таблица с именем: " + tableName + " создана успешно.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String prepareString(Set<Integer> items, String field) {
        String whereString = "WHERE";
        for (Integer i : items) {
            if (!"WHERE".equals(whereString)) whereString += " OR ";
            whereString += " " + field + " = " + i;
        }
        return whereString;
    }
    ////////   SERVICE METHODS //////
}

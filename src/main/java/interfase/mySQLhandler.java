package interfase;

import essence.Book;
import essence.Link;
import essence.Tag;
import essence.UserProfile;

import java.util.ArrayList;
import java.util.List;

public interface mySQLhandler {

    public ArrayList<Book> getAllBooks();
    public ArrayList<Tag> getAllTags();

    public void refreshLocalRoot ();
    public String createUserAP(String userName, String userPass, String userPath); //создаю нового пользователя и назначаю ему права All Privileges, добавляю настройки пользователя в БД
    public String insertUserProfile(int prof_id, String profPath);
    public void createAdminServer(String serverURL, String userName, String userPass); //создаю нового пользователя и назначаю ему права All Privileges, добавляю настройки пользователя в БД

    public void createDB(String dbName); //создаю БД на сервере (поумолчанию:BookSorterPro)
    public void deleteDB(String dbName); //удаляю БД из сервера (поумолчанию:BookSorterPro)
    public void createSYSTableUserData();
    public void createDBTableBooks();
    public void createDBTableTags();
    public void createDBTableLinks();
    public void deleteDBTable(String tableName); //удаляю БД с сервера

    // получает аргументом айдишник книги, возвращает наполненый полями экземпляр класса Book
    public Book getBookFromId(String id);

    public Tag getTagFromId(String id);

    public Link getLinkFromId(String id);

    // получает аргументом Book и инсертит в базу данных, возвращает айдишник записи
    public int insertNewBook(Book book);

    public void insertNewTag(Tag tag);

    public void insertNewLink(Link link);

    public void updateBook(Book book);

    public void updateTag(Tag tag);

    public void updateLink(Link link);

    // получает аргументом номер айдишника, который нужно удалить
    public void deleteBook(String Id);

    public void deleteTag(String Id);

    public void deleteLink(String Id);

    public void readDB(String dbName, String tableName);

    // получает аргументом номер первой и последней записи из БД
    // а так же их количество
    public ArrayList<Book> getBooksFromTo(int from, int to, int quantity);

    // получает аргументом название поля книги (например "Автор"),
    // а так же список фильтров, напрмер ({Чехов, Пушкин})
    // Возвращает дист с книгами
    public ArrayList<Book> getFilteredBooks(String field, ArrayList<String> filters);

    public List<Tag> getTagsFromTo(int from, int to, int quantity);

    public ArrayList<Book> getFilteredTags(String field, ArrayList<String> filters);


}

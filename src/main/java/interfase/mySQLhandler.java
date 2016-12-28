package interfase;

import essence.Book;
import essence.Tag;

import java.util.ArrayList;
import java.util.List;

public interface mySQLhandler {

    public ArrayList<Book> getAllBooks();

    public void refreshLocalRoot ();
    public void createUserAP(String userName, int userPass); //создаю нового пользователя и назначаю ему права All Privileges

    public void createDB(String dbName); //создаю БД на сервере (поумолчанию:BookSorterPro)
    public void deleteDB(String dbName); //удаляю БД из сервера (поумолчанию:BookSorterPro)
    public void createDBTableBooks(String tableName);
    public void createDBTableTags(String tableName);
    public void createDBTableLinks(String tableName);
    public void deleteDBTable(String tableName); //удаляю БД с сервера

    // получает аргументом айдишник книги, возвращает наполненый полями экземпляр класса Book
    public Book getBookFromId(String id);

    public Tag getTagFromId(String id);

    // получает аргументом Book и инсертит в базу данных
    public void insertNewBook(Book book);

    public void insertNewTag(Tag tag);

    public void updateBook(Book book);

    public void updateTag(Tag tag);

    // получает аргументом номер айдишника, который нужно удалить
    public void deleteBook(String Id);

    public void deleteTag(String Id);

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

package interfase;

import essence.Book;
import essence.Tag;

import java.util.ArrayList;
import java.util.List;

public interface mySQLhandler {

    public ArrayList<Book> getAllBooks();

    public void createDB();
    public void deleteDB();
    public void createDBTableBooks();
    public void createDBTableTags();
    public void createDBTableLinks();
    public void deleteDBTable();

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

    public void readDB();

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

package debugging;

import essence.Book;
import essence.Tag;
import interfase.mySQLhandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by New on 12/17/2016.
 */
class DBtap implements mySQLhandler {
    public ArrayList<Book> getAllBooks() {
        return null;
    }

    public Book getBookFromId(String id) {
        return null;
    }

    public Tag getTagFromId(String id) {
        return null;
    }

    public void insertNewBook(Book book) {

    }

    public void insertNewTag(Tag tag) {

    }

    public void updateBook(Book book) {

    }

    public void updateTag(Tag tag) {

    }

    public void deleteBook(String Id) {

    }

    public void deleteTag(String Id) {

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

    /*@Override
    public Book getBookFromId(String id) {
        return null;
    }

    @Override
    public Tag getTagFromId(String id) {
        return null;
    }

    @Override
    public void insertNewBook(Book book) {

    }

    @Override
    public void insertNewTag(Tag tag) {

    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public void updateTag(Tag tag) {

    }

    @Override
    public void deleteBook(String Id) {

    }

    @Override
    public void deleteTag(String Id) {

    }

    @Override
    public ArrayList<Book> getBooksFromTo(int from, int to, int quantity) {
        ArrayList<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 10; i++) {
            Book b = new Book();
            b.name = "Book" + Math.random();
            b.autor = "Pushkin " + Math.random() + " Al";
            b.year = Math.random() * 2000;
            books.add(b);
        }
        return books;
    }

    @Override
    public ArrayList<Book> getFilteredBooks(String field, ArrayList<String> filters) {
        return null;
    }

    @Override
    public List<Tag> getTagsFromTo(int from, int to, int quantity) {
        return null;
    }

    @Override
    public ArrayList<Book> getFilteredTags(String field, ArrayList<String> filters) {
        return null;
    }*/
}

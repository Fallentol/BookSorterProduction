package essence;

/**
 * Created by New on 12/17/2016.
 */
public class Book {

    public int id;
    public String name;
    public String author;
    public String language;
    public String type;
    public String format;
    public String path;
    public String description;
    public int year;
    public int size;

    public Book(){

    }

    public Book(int id, String name, String author, String language, String type, String format, String path, String description, int year, int size) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.language = language;
        this.type = type;
        this.format = format;
        this.path = path;
        this.description = description;
        this.year = year;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public int setId(int id) {
        this.id = id;
        return id;
    }

    public String getAuthor() {
        return this.author;
    }

    public String setAuthor(String author) {
        this.author = author;
        return author;
    }

    public int getYear() {
        return year;
    }

    public int setYear(int year) {
        this.year = year;
        return year;
    }

    public String getName() {
        return this.name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public String setLanguage(String language) {
        this.language = language;
        return language;
    }

    public String getPath() {
        return path;
    }

    public String setPath(String path) {
        this.path = path;
        return path;
    }

    public String getType() {
        return type;
    }

    public String  setType(String type) {
        this.type = type;
        return type;
    }

    public String getFormat() {
        return format;
    }

    public String setFormat(String format) {
        this.format = format;
        return format;
    }

    public String getDescription() {
        return description;
    }

    public String setDescription(String description) {
        this.description = description;
        return description;
    }

    public int getSize() {
        return size;
    }

    public int setSize(int size) {
        this.size = size;
        return size;
    }

    public String toString() {
        return "Название книги '" + this.name + "', её автор " + this.author + ", написана в " + this.year + " г.";
    }


}

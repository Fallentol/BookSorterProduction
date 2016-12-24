package essence;

/**
 * Created by New on 12/17/2016.
 */
public class Book {

    public int id;
    public String author;
    public int year;
    public String name;
    public String language;
    public String path;
    public String type;
    public String format;
    public String description;
    public int size;

    public int getYear() {
        return year;
    }

    public String getLanguage() {
        return language;
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }

    public String getFormat() {
        return format;
    }

    public String getDescription() {
        return description;
    }

    public int getSize() {
        return size;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;

    }
    public String getAuthor() {
        return this.author;
    }

    public String toString() {
        return "Название книги '" + this.name + "', её автор " + author + " написана " + year + " г.";
    }


}

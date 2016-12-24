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

    public String toString() {
        return "Название книги '" + this.name + "', её автор " + author + " написана " + year + " г.";
    }


}

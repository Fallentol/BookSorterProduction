package essence;

public class Link {

    public int id;
    public int tag_id;
    public int book_id;

    public Link() {

    }

    public Link(int id, int tag_id, int book_id) {
        this.id = id;
        this.tag_id = tag_id;
        this.book_id = book_id;
    }

    public int getId() {
        return id;
    }

    public int setId(int id) {
        this.id = id;
        return id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public int setTag_id(int tag_id) {
        this.tag_id = tag_id;
        return tag_id;
    }
    public int getBook_id() {
        return book_id;
    }

    public int setBook_id(int book_id) {
        this.book_id = book_id;
        return book_id;
    }
}

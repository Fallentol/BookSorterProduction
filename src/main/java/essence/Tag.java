package essence;

/**
 * Created by New on 12/17/2016.
 */
public class Tag {

    public int id;
    public String name;
    public int parentId;

    public Tag() {

    }

    public Tag(int id, String name, int parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public int setId(int id) {
        this.id = id;
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public int getParentId() {
        return parentId;
    }

    public int setParentId(int parentId) {
        this.parentId = parentId;
        return parentId;
    }
}

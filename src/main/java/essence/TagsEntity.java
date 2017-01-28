package essence;

import javax.persistence.*;

/**
 * Created by New on 1/14/2017.
 */
@Entity
@Table(name = "tags", schema = "booksorterpro", catalog = "")
public class TagsEntity {

    public TagsEntity() {
    }

    public TagsEntity(String name, int parent) {
        this.tagName = name;
        this.tagParent = parent;
    }

    private int tagId;
    private String tagName;
    private Integer tagParent;
///////////////////////////////
   /* private TagsEntity tagParentObj;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tags")
    public TagsEntity getTagParentObj() {
        return tagParentObj;
    }

    public void setTagParentObj(TagsEntity par) {
        par.setTagParent(this);
        this.tagParentObj = tagParentObj;
    }*/
///////////////////////////////
    @Id
    @Column(name = "tag_id", nullable = false)
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "tagName", nullable = false, length = 255)
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Basic
    @Column(name = "tagParent", nullable = true)
    public Integer getTagParent() {
        return tagParent;
    }

    public void setTagParent(Integer tagParent) {
        this.tagParent = tagParent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagsEntity that = (TagsEntity) o;

        if (tagId != that.tagId) return false;
        if (tagName != null ? !tagName.equals(that.tagName) : that.tagName != null) return false;
        if (tagParent != null ? !tagParent.equals(that.tagParent) : that.tagParent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagId;
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        result = 31 * result + (tagParent != null ? tagParent.hashCode() : 0);
        return result;
    }
}

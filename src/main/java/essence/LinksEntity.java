package essence;

import javax.persistence.*;

/**
 * Created by New on 1/14/2017.
 */
@Entity
@Table(name = "links", schema = "booksorterpro", catalog = "")
public class LinksEntity {
    private int linkId;
    private int tagId;
    private int bookId;

    @Id
    @Column(name = "link_id", nullable = false)
    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    @Basic
    @Column(name = "tagId", nullable = false)
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "bookId", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinksEntity that = (LinksEntity) o;

        if (linkId != that.linkId) return false;
        if (tagId != that.tagId) return false;
        if (bookId != that.bookId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = linkId;
        result = 31 * result + tagId;
        result = 31 * result + bookId;
        return result;
    }
}

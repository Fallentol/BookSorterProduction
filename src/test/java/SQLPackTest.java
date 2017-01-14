import dataBaseUtils.SQLUtils;
import essence.Book;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class SQLPackTest {

    @Test
    public void testFileProcessor() {
        Book testBook = new Book(18, "Name", "Author", "ru", "book", "pdf", "Path", "test", 1997, 18);

        SQLUtils testObj = new SQLUtils();
        Book book = testObj.getBookFromId("2");
        assertNotNull("getBookFromId(\"2\") return NULL", testObj.getBookFromId("2"));
        assertTrue("insertNewBook() return -1", testObj.insertNewBook(testBook) != -1);
    }
}


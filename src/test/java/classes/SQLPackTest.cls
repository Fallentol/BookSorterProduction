import dataBaseUtils.SQLUtils;
import essence.Book;
import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class SQLPackTest {

    private static int insertedBookId;

    @Test
            public void testFileProcessor() {
    Book testBook = new Book(18, "Name", "Author", "ru", "book", "pdf", "Path", "test", 1997, 18);

    SQLUtils testObj = new SQLUtils();
    assertNotNull("getBookFromId(\"2\") return NULL", testObj.getBookFromId("2"));
    insertedBookId = testObj.insertNewBook(testBook);
    assertTrue("insertNewBook() return -1", insertedBookId != -1);
}

@AfterClass
public static void returnToStartConfiguration() {
SQLUtils testObj = new SQLUtils();
System.out.println("insertedBookId=" + insertedBookId);
testObj.deleteBook(String.valueOf(insertedBookId));
}
}
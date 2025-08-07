package test;

import org.YronJack.models.Book;
import org.YronJack.store.BookStore;
import org.junit.jupiter.api.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookStoreTest {

    private static final String TEST_FILE = "data/test_books.csv";

    @BeforeEach
    void setUp() throws Exception {
        // Set the CSV file to a test-specific file
        BookStore.setFileNameForTests(TEST_FILE);

        // Create an empty test file with headers
        File testFile = new File(TEST_FILE);
        if (testFile.exists()) {
            testFile.delete();
        }
        testFile.getParentFile().mkdirs();
        testFile.createNewFile();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(testFile))) {
            bw.write("isbn,title,category,author,quantity");
            bw.newLine();
        }
    }

    @AfterEach
    void tearDown() {
        // Delete the test file after each test
        File testFile = new File(TEST_FILE);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    void testLoadBooksEmptyFile() {
        // The list should be empty when the file has only headers
        List<Book> books = BookStore.loadBooks();
        assertTrue(books.isEmpty(), "Books list should be empty if file is empty");
    }

    @Test
    void testSaveAndLoadBook() {
        // Save a book and then verify it was correctly written and read back
        Book book = new Book("123-456", "Test Book", "Fiction", 5, "Author Name", false);
        BookStore.saveBook(book);

        List<Book> books = BookStore.loadBooks();
        assertFalse(books.isEmpty(), "Books list should not be empty after saving a book");
        assertEquals("123-456", books.get(0).getIsbn());
        assertEquals("Test Book", books.get(0).getTitle());
        assertEquals(5, books.get(0).getQuantity());
    }

    @Test
    void testExistsByISBN() {
        // Save a book and check if it can be found by its ISBN
        Book book = new Book("789-1011", "Another Book", "Non-Fiction", 3, "Author", false);
        BookStore.saveBook(book);

        BookStore store = new BookStore();
        assertTrue(store.existsByISBN("789-1011"), "Book should exist by its ISBN");
        assertFalse(store.existsByISBN("000-0000"), "Non-existing ISBN should return false");
    }
}


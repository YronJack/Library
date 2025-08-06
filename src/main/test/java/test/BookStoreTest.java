package org.YronJack.store;

import org.YronJack.models.Book;
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
        // Cambiar el archivo CSV a uno de test
        BookStore.setFileNameForTests(TEST_FILE);

        // Crear archivo test vacío con cabecera
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
        // Borrar archivo test
        File testFile = new File(TEST_FILE);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    void testLoadBooksEmptyFile() {
        List<Book> books = BookStore.loadBooks();
        assertTrue(books.isEmpty(), "Books list should be empty if file is empty");
    }

    @Test
    void testSaveAndLoadBook() {
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
        Book book = new Book("789-1011", "Another Book", "Non-Fiction", 3, "Author", false);
        BookStore.saveBook(book);

        assertTrue(BookStore.existsByISBN("789-1011"));
        assertFalse(BookStore.existsByISBN("000-0000"));
    }
}

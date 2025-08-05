//package test;
//import org.YronJack.*;
//import org.YronJack.models.Book;
//import org.YronJack.store.BookStore;
//import org.junit.jupiter.api.*;
//import java.io.*;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class BookStoreTest {
//
//    private static final String TEST_FILE = "data/test_books.csv";
//
//    @BeforeEach
//    void setUp() throws IOException {
//        // Crear archivo de prueba con encabezado
//        File file = new File(TEST_FILE);
//        file.getParentFile().mkdirs();
//        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
//            writer.println("isbn,title,category,author,quantity");
//            writer.println("12345,Test Book,Fiction,Author Name,3");
//        }
//        // Cambiar el nombre del archivo en BookStore usando reflexión
//        var field = BookStore.class.getDeclaredField("FILE_NAME");
//        field.setAccessible(true);
//        field.set(null, TEST_FILE);
//    }
//
//    @AfterEach
//    void tearDown() {
//        new File(TEST_FILE).delete();
//    }
//
//    @Test
//    void testLoadBooks() {
//        List<Book> books = BookStore.loadBooks();
//        assertEquals(1, books.size());
//        assertEquals("12345", books.get(0).getIsbn());
//    }
//
//    @Test
//    void testExistsByISBN() {
//        BookStore store = new BookStore();
//        assertTrue(store.existsByISBN("12345"));
//        assertFalse(store.existsByISBN("99999"));
//    }
//
//    @Test
//    void testSaveBook() {
//        Book newBook = new Book("67890", "Nuevo", "Drama", 2, "Otro Autor", false);
//        BookStore.saveBook(newBook);
//        List<Book> books = BookStore.loadBooks();
//        assertTrue(books.stream().anyMatch(b -> b.getIsbn().equals("67890")));
//    }
//}
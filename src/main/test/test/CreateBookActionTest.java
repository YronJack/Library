package test;

import org.YronJack.models.Hub;
import org.YronJack.utils.CreateBookAction;
import org.YronJack.store.BookStore;
import org.YronJack.models.Book;
import org.YronJack.enums.Category;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CreateBookActionTest {

        private Hub hub = new Hub();
        private File testFile; // <-- Añade esta línea
        List<Book> emptyBookList = new ArrayList<>();
        private BookStore bookStore;
        private final PrintStream originalOut = System.out;
        private ByteArrayOutputStream outContent;


    @BeforeEach
    void setUp() throws IOException {
        // declaras el lector
        // declaras los textos
        // limpias y repites











        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        testFile = File.createTempFile("books_test_", ".csv");
        BookStore.setFileNameForTests(testFile.getAbsolutePath());

        try (PrintWriter writer = new PrintWriter(new FileWriter(testFile))) {
            writer.println("isbn,title,category,author,quantity");
        }

        Hub.booksList.clear();
        hub.setBooksList(Hub.booksList);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        hub.setBooksList(new ArrayList<>());
        if (testFile != null && testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    void createBookWithValidDataCreatesBookSuccessfully() {

        String input = "9783161484100\nLibro de Prueba\nother\nAutor Prueba\n5\n";
        Scanner mockScanner = new Scanner(input);
        CreateBookAction.createBook(mockScanner,hub);
        String output = outContent.toString();
        assertTrue(output.contains("✅ Book created and saved successfully!"));
        assertTrue(output.contains("9783161484100"));
        assertTrue(output.contains("Libro de Prueba"));
        assertTrue(output.contains("Autor Prueba"));
        assertTrue(Hub.booksList.stream().anyMatch(book -> book.getIsbn().equals("9783161484100")));
    }

    @Test
    void createBookWithInvalidISBNShowsErrorAndAsksAgain() {
        String input = "123\n9783161484100\nLibro\nother\nAutor\n1\n\n\n\n\n\n\n";
        Scanner lechuga = new Scanner(input);
        CreateBookAction.createBook(lechuga,hub);
        String output = outContent.toString();
        assertTrue(output.contains("❌ ISBN must have exactly 13 digits."));
        assertTrue(output.contains("✅ Book created and saved successfully!"));
        assertTrue(Hub.booksList.stream().anyMatch(book -> book.getIsbn().equals("9783161484100")));
    }

    @Test
    void createBookWithDuplicateISBNShowsErrorAndAsksAgain() {
        // Pre-cargar un libro con el ISBN
        hub.getBooksList().add(new Book("9783161484100", "Existente", "other", 1, "Autor", true));
        String input = "9783161484100\n9783161484101\nLibro\nother\nAutor\n2\n";
        Scanner scanner = new Scanner(input);
        CreateBookAction.createBook(scanner, hub);
        String output = outContent.toString();
        assertTrue(output.contains("❌ This ISBN is already registered in the database"));
        assertTrue(output.contains("9783161484101"));
        assertTrue(Hub.booksList.stream().anyMatch(book -> book.getIsbn().equals("9783161484101")));
    }

    @Test
    void createBookWithInvalidCategoryShowsErrorAndAsksAgain() {
        String input = "9783161484100\nLibro\nINVALID\nother\nAutor\n1\n\n\n\n\n\n";
        Scanner scanner = new Scanner(input);
        CreateBookAction.createBook(scanner,hub);
        String output = outContent.toString();
        assertTrue(output.contains("❌ Invalid category, try again."));
        assertTrue(output.contains("✅ Book created and saved successfully!"));
    }

    @Test
    void createBookWithEmptyAuthorNameShowsErrorAndAsksAgain() {
        String input = "9783161484100\nLibro\nother\n\nAutor\n1\n\n\n\n\n\n";
        Scanner scanner = new Scanner(input);
        CreateBookAction.createBook(scanner,hub);
        String output = outContent.toString();
        assertTrue(output.contains("❌ Author name is required. Please enter a valid name."));
        assertTrue(output.contains("Autor"));
        assertTrue(output.contains("✅ Book created and saved successfully!"));
    }

    @Test
    void createBookWithInvalidQuantityShowsErrorAndAsksAgain() {
        String input = "9783161484100\nLibro\nother\nAutor\n-1\nabc\n3\n\n\n\n\n\n";
        Scanner scanner = new Scanner(input);
        CreateBookAction.createBook(scanner,hub);
        String output = outContent.toString();
        assertTrue(output.contains("❌ Invalid quantity, positive integer needed."));
        assertTrue(output.contains("✅ Book created and saved successfully!"));
        assertTrue(Hub.booksList.stream().anyMatch(book -> book.getIsbn().equals("9783161484100")));
    }
}
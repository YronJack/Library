package test;

import org.YronJack.models.Author;
import org.YronJack.models.Book;
import org.YronJack.models.Hub;
import org.YronJack.utils.SearchActions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SearchActionsTest {

    private Hub hub;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        hub = new Hub();

        Book b1 = new Book();
        b1.setIsbn("111-AAA");
        b1.setTitle("Java Basics");
        b1.setCategory("Programming");
        b1.setQuantity(5);
        b1.setAuthor(new Author("John Doe"));

        Book b2 = new Book();
        b2.setIsbn("222-BBB");
        b2.setTitle("Python Advanced");
        b2.setCategory("Programming");
        b2.setQuantity(3);
        b2.setAuthor(new Author("Jane Smith"));

        Book b3 = new Book();
        b3.setIsbn("333-CCC");
        b3.setTitle("Cooking Guide");
        b3.setCategory("Cooking");
        b3.setQuantity(10);
        b3.setAuthor(new Author("Chef Mike"));

        hub.booksList = Arrays.asList(b1, b2, b3);

        // Redirects console output to ByteArrayOutputStream for assertion
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testSearchByTitle_Found() {
        // Should find a book by a keyword in the title
        SearchActions.searchByTitle("Java", hub);
        String output = outContent.toString();
        assertTrue(output.contains("Java Basics")); // Expected title found
        assertFalse(output.contains("Python Advanced")); // Irrelevant title should not appear
    }

    @Test
    void testSearchByTitle_NotFound() {
        // Should return warning when no book title matches
        SearchActions.searchByTitle("C#", hub);
        String output = outContent.toString();
        assertTrue(output.contains("⚠ No books found.")); // Appropriate warning message
    }

    @Test
    void testSearchByAuthor_Found() {
        // Should find a book written by an author with matching name
        SearchActions.searchByAuthor("Jane", hub);
        String output = outContent.toString();
        assertTrue(output.contains("Python Advanced")); // Book by Jane Smith found
        assertFalse(output.contains("Java Basics")); // Book by another author not included
    }

    @Test
    void testSearchByAuthor_NotFound() {
        // Should return warning when no author matches
        SearchActions.searchByAuthor("Nonexistent", hub);
        String output = outContent.toString();
        assertTrue(output.contains("⚠ No books found.")); // Expected no match
    }

    @Test
    void testSearchByCategory_Found() {
        // Should list books within the requested category
        SearchActions.searchByCategory("Cooking", hub);
        String output = outContent.toString();
        assertTrue(output.contains("Cooking Guide")); // Book in Cooking category shown
        assertFalse(output.contains("Java Basics")); // Book in another category not shown
    }

    @Test
    void testSearchByCategory_NotFound() {
        // Should return warning for a non-existing category
        SearchActions.searchByCategory("History", hub);
        String output = outContent.toString();
        assertTrue(output.contains("⚠ No books found.")); // Warning displayed as expected
    }
}

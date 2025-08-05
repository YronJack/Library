package org.YronJack.utils;

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

        // Capturamos salida de consola para validar impresión
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testSearchByTitle_Found() {
        SearchActions.searchByTitle("Java", hub);
        String output = outContent.toString();
        assertTrue(output.contains("Java Basics"));
        assertFalse(output.contains("Python Advanced"));
    }

    @Test
    void testSearchByTitle_NotFound() {
        SearchActions.searchByTitle("C#", hub);
        String output = outContent.toString();
        assertTrue(output.contains("⚠ No books found."));
    }

    @Test
    void testSearchByAuthor_Found() {
        SearchActions.searchByAuthor("Jane", hub);
        String output = outContent.toString();
        assertTrue(output.contains("Python Advanced"));
        assertFalse(output.contains("Java Basics"));
    }

    @Test
    void testSearchByAuthor_NotFound() {
        SearchActions.searchByAuthor("Nonexistent", hub);
        String output = outContent.toString();
        assertTrue(output.contains("⚠ No books found."));
    }

    @Test
    void testSearchByCategory_Found() {
        SearchActions.searchByCategory("Cooking", hub);
        String output = outContent.toString();
        assertTrue(output.contains("Cooking Guide"));
        assertFalse(output.contains("Java Basics"));
    }

    @Test
    void testSearchByCategory_NotFound() {
        SearchActions.searchByCategory("History", hub);
        String output = outContent.toString();
        assertTrue(output.contains("⚠ No books found."));
    }
}

package test;

import org.YronJack.enums.Category;
import org.YronJack.store.BookStore;
import org.YronJack.utils.CreateBookAction;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class CreateBookActionTest {

    // Test for validateISBN13 (throws IllegalArgumentException if invalid)
    @Test
    void testValidateISBN13_ValidAndInvalid() throws Exception {
        Method validateISBN13 = CreateBookAction.class.getDeclaredMethod("validateISBN13", String.class);
        validateISBN13.setAccessible(true);

        // Valid ISBN should not throw exception
        assertDoesNotThrow(() -> validateISBN13.invoke(null, "9780306406157"));

        // ISBN with fewer digits throws exception
        Exception e1 = assertThrows(InvocationTargetException.class, () -> validateISBN13.invoke(null, "978030640615"));
        assertTrue(e1.getCause() instanceof IllegalArgumentException);

        // ISBN not starting with 978 or 979
        Exception e2 = assertThrows(InvocationTargetException.class, () -> validateISBN13.invoke(null, "9770306406157"));
        assertTrue(e2.getCause() instanceof IllegalArgumentException);

        // ISBN with invalid checksum
        Exception e3 = assertThrows(InvocationTargetException.class, () -> validateISBN13.invoke(null, "9780306406158"));
        assertTrue(e3.getCause() instanceof IllegalArgumentException);
    }

    // Test for askValidISBN
    // FakeBookStore that always says no ISBN exists to ease testing
    static class FakeBookStore extends BookStore {
        @Override
        public boolean existsByISBN(String isbn) {
            return false;
        }
    }

    @Test
    void testAskValidISBN() {
        String input = String.join("\n",
                "1111111111111",  // Invalid ISBN (will fail validation)
                "9781234567897"   // Valid ISBN
        );

        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        BookStore store = new FakeBookStore();

        // Since askValidISBN is private, I assume you made it package-private or public to test it.
        // Otherwise, you will need to change its visibility to test it directly.

        String isbn = CreateBookAction.askValidISBN(scanner, store);

        assertEquals("9781234567897", isbn);
    }

    // Test for askTitle
    @Test
    void testAskTitle() throws Exception {
        Method askTitle = CreateBookAction.class.getDeclaredMethod("askTitle", Scanner.class);
        askTitle.setAccessible(true);

        String input = "Some Book Title\n";
        Scanner scanner = new Scanner(input);

        String title = (String) askTitle.invoke(null, scanner);
        assertEquals("Some Book Title", title);
    }

    // Test for askCategory
    @Test
    void testAskCategory() throws Exception {
        Method askCategory = CreateBookAction.class.getDeclaredMethod("askCategory", Scanner.class);
        askCategory.setAccessible(true);

        // First invalid input, second valid
        String input = String.join("\n", "INVALID", "FICTION");
        Scanner scanner = new Scanner(input);

        Category category = (Category) askCategory.invoke(null, scanner);
        assertEquals(Category.FICTION, category);
    }

    // Test for askAuthorName
    @Test
    void testAskAuthorName() throws Exception {
        Method askAuthorName = CreateBookAction.class.getDeclaredMethod("askAuthorName", Scanner.class);
        askAuthorName.setAccessible(true);

        // First empty input, second valid
        String input = String.join("\n", "", "John Doe");
        Scanner scanner = new Scanner(input);

        String author = (String) askAuthorName.invoke(null, scanner);
        assertEquals("John Doe", author);
    }

    // Test for askQuantity
    @Test
    void testAskQuantity() throws Exception {
        Method askQuantity = CreateBookAction.class.getDeclaredMethod("askQuantity", Scanner.class);
        askQuantity.setAccessible(true);

        // First invalid input, second valid
        String input = String.join("\n", "-5", "10");
        Scanner scanner = new Scanner(input);

        int quantity = (int) askQuantity.invoke(null, scanner);
        assertEquals(10, quantity);
    }
}
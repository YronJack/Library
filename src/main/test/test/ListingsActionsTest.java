package test;

import org.YronJack.models.*;
import org.YronJack.utils.ListingActions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ListingsActionsTest {
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

        hub.booksList = new ArrayList<>(Arrays.asList(b1, b2, b3));

        Student s1 = new Student("paco");
        Student s2 = new Student("daniel");
        Student s3 = new Student("julia");

        hub.studentsList = new ArrayList<>(Arrays.asList(s1, s2, s3));

        Issue i1 = new Issue();
        i1.setIssueBook(b1);
        i1.setIssueStudent(s1);
        i1.setIssueDate(LocalDate.now());
        i1.setReturnDate(LocalDate.now());

        Issue i2 = new Issue();
        i2.setIssueBook(b2);
        i2.setIssueStudent(s2);
        i2.setIssueDate(LocalDate.now());
        i2.setReturnDate(LocalDate.now());

        Issue i3 = new Issue();
        i3.setIssueBook(b3);
        i3.setIssueStudent(s3);
        i3.setIssueDate(LocalDate.now());
        i3.setReturnDate(LocalDate.now().plusDays(1));

        Issue i4 = new Issue();
        i4.setIssueBook(b1);
        i4.setIssueStudent(s1);
        i4.setIssueDate(LocalDate.now());
        i4.setReturnDate(LocalDate.now());

        Issue i5 = new Issue();
        i5.setIssueBook(b2);
        i5.setIssueStudent(s1);
        i5.setIssueDate(LocalDate.now());
        i5.setReturnDate(LocalDate.now());

        hub.issuesList = new ArrayList<>(Arrays.asList(i1, i2, i3, i4, i5));

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void listAllBooksShowsAllBooks() {
        ListingActions.listAllBooks(hub);
        String output = outContent.toString();
        assertTrue(output.contains("Java Basics"));
        assertTrue(output.contains("Python Advanced"));
        assertTrue(output.contains("Cooking Guide"));
    }

    @Test
    void listAllBooksWhenNoBooksAvailable() {
        hub.booksList.clear();
        ListingActions.listAllBooks(hub);
        String output = outContent.toString();
        assertTrue(output.contains("No books available."));
    }

    @Test
    void listBooksByUsnWithValidUsn() {
        Scanner mockScanner = new Scanner(hub.studentsList.get(0).getUsn() + "\n");
        ListingActions.listBooksByUsn(mockScanner, hub);
        String output = outContent.toString();
        assertTrue(output.contains("Book Title"));
        assertTrue(output.contains(hub.studentsList.get(0).getName()));
    }

    @Test
    void listBooksByUsnWithInvalidUsn() {
        Scanner mockScanner = new Scanner("invalid_usn\n");
        ListingActions.listBooksByUsn(mockScanner, hub);
        String output = outContent.toString();
        assertTrue(output.contains("No books found for student with USN invalid_usn."));
    }

    @Test
    void listBooksByUsnWithEmptyUsn() {
        Scanner mockScanner = new Scanner("\n");
        ListingActions.listBooksByUsn(mockScanner, hub);
        String output = outContent.toString();
        assertTrue(output.contains("No books found for student with USN ."));
    }

    @Test
    void listAllIssuedBooksWhenNoIssuesExist() {
        hub.issuesList.clear();
        ListingActions.listAllIssuedBooks(hub);
        String output = outContent.toString();
        assertTrue(output.contains("No books have been issued."));
    }

    @Test
    void listAllIssuedBooksWhenIssuesExist() {
        ListingActions.listAllIssuedBooks(hub);
        String output = outContent.toString();
        assertTrue(output.contains("Java Basics"));
        assertTrue(output.contains("Python Advanced"));
        assertTrue(output.contains("Cooking Guide"));
        assertTrue(output.contains("Book ISBN"));
    }

    @Test
    void listBooksByCategoryWithValidCategory() {
        Scanner mockScanner = new Scanner("Programming\n");
        ListingActions.listBooksByCategory(mockScanner, hub);
        String output = outContent.toString();
        assertTrue(output.contains("Java Basics"));
        assertTrue(output.contains("Python Advanced"));
        assertFalse(output.contains("Cooking Guide"));
    }

    @Test
    void listBooksByCategoryWithInvalidCategory() {
        Scanner mockScanner = new Scanner("Nonexistent\n");
        ListingActions.listBooksByCategory(mockScanner, hub);
        String output = outContent.toString();
        assertTrue(output.contains("No books found in category 'Nonexistent'."));
    }

    @Test
    void listBooksByCategoryWithEmptyCategory() {
        Scanner mockScanner = new Scanner("\n");
        ListingActions.listBooksByCategory(mockScanner, hub);
        String output = outContent.toString();
        assertTrue(output.contains("No books found in category ''."));
    }

    @Test
    void listBooksNeverIssuedWhenAllBooksIssued() {
        ListingActions.listBooksNeverIssued(hub);
        String output = outContent.toString();
        assertTrue(output.contains("All books have been issued at least once."));
    }

    @Test
    void listBooksNeverIssuedWhenSomeBooksNeverIssued() {
        Book newBook = new Book();
        newBook.setIsbn("444-DDD");
        newBook.setTitle("New Book");
        newBook.setCategory("Fiction");
        newBook.setQuantity(2);
        hub.booksList.add(newBook);

        ListingActions.listBooksNeverIssued(hub);
        String output = outContent.toString();
        assertTrue(output.contains("New Book"));
        assertFalse(output.contains("All books have been issued at least once."));
    }

    @Test
    void listAllStudentsWhenNoStudentsRegistered() {
        hub.studentsList.clear();
        ListingActions.listAllStudents(hub);
        String output = outContent.toString();
        assertTrue(output.contains("No students registered."));
    }

    @Test
    void listAllStudentsWhenValidStudentsExist() {
        ListingActions.listAllStudents(hub);
        String output = outContent.toString();
        assertTrue(output.contains("paco"));
        assertTrue(output.contains("daniel"));
        assertTrue(output.contains("julia"));
    }

    @Test
    void listAllStudentsSkipsUnknownStudents() {
        Student unknown = new Student("Unknown");
        hub.studentsList.add(unknown);
        ListingActions.listAllStudents(hub);
        String output = outContent.toString();
        assertFalse(output.contains("Unknown"));
    }

    @Test
    void listBooksReturnToday_showsBooksToReturnToday() {

        Book book = new Book("1234567890123", "Libro Hoy", "PROGRAMMING", 1, "Autor", true);
        Student student = new Student("Estudiante");
        Issue issue = new Issue(book, student, LocalDate.now().minusDays(7), LocalDate.now());
        hub.booksList.add(book);
        hub.studentsList.add(student);
        hub.issuesList.add(issue);

        ListingActions.listBooksReturnToday(hub);

        String output = outContent.toString();
        assertTrue(output.contains("Libro Hoy"));
        assertTrue(output.contains("Estudiante"));
        assertTrue(output.contains(LocalDate.now().toString()));
    }

    @Test
    void listBooksReturnToday_showsNoBooksMessage() {
        Book book = new Book("1234567890123", "Libro Hoy", "PROGRAMMING", 1, "Autor", true);
        Student student = new Student("Estudiante");
        Issue issue = new Issue(book, student, LocalDate.now(), LocalDate.now().plusDays(7));
        hub.booksList.add(book);
        hub.studentsList.add(student);
        hub.issuesList.add(issue);

        ListingActions.listBooksReturnToday(hub);
        String output = outContent.toString();
        assertTrue(output.contains("No books to return today."));
    }
}
//package test;
//
//import org.YronJack.models.Book;
//import org.YronJack.models.Author;
//import org.YronJack.models.Hub;
//import org.YronJack.utils.ListingActions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ListingActionsTest {
//
//    private Hub hub;
//    private ByteArrayOutputStream outContent;
//
//    @BeforeEach
//    void setUp() {
//        hub = new Hub();
//        // List with 2 books
//        Book b1 = new Book();
//        b1.setIsbn("111-AAA");
//        b1.setTitle("Java Basics");
//        b1.setCategory("Programming");
//        b1.setQuantity(5);
//        b1.setAuthor(new Author("John Doe"));
//
//        Book b2 = new Book();
//        b2.setIsbn("222-BBB");
//        b2.setTitle("Python Advanced");
//        b2.setCategory("Programming");
//        b2.setQuantity(3);
//        b2.setAuthor(new Author("Jane Smith"));
//
//        hub.getBooksList().addAll(Arrays.asList(b1, b2));
//
//        outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//    }
//
//    @Test
//    void testListAllBooks_WithBooks() {
//        ListingActions.listAllBooks(hub);
//        String output = outContent.toString();
//        assertTrue(output.contains("Java Basics"));
//        assertTrue(output.contains("Python Advanced"));
//    }
//
//    @Test
//    void testListAllBooks_EmptyList() {
//        hub.getBooksList().clear();
//        ListingActions.listAllBooks(hub);
//        String output = outContent.toString();
//        assertTrue(output.contains("No books available."));
//    }
//
//    @Test
//    void testListBooksByCategory_Found() {
//        ListingActions.listBooksByCategory(
//                new java.util.Scanner("Programming\n"), hub);
//        String output = outContent.toString();
//        assertTrue(output.contains("Java Basics"));
//        assertTrue(output.contains("Python Advanced"));
//    }
//
//    @Test
//    void testListBooksByCategory_NotFound() {
//        ListingActions.listBooksByCategory(
//                new java.util.Scanner("History\n"), hub);
//        String output = outContent.toString();
//        assertTrue(output.contains("No books found in category"));
//    }
//}


package test;

import org.YronJack.models.*;
import org.YronJack.utils.IssueActions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IssueActionsTest {
    private Hub hub;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        hub = new Hub();

        // Listas mutables
        hub.booksList = new ArrayList<>();
        hub.studentsList = new ArrayList<>();
        hub.issuesList = new ArrayList<>();

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

        hub.booksList.add(b1);
        hub.booksList.add(b2);

        Student s1 = new Student("paco");
        hub.studentsList.add(s1);

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void issueBookToStudentWithValidData() {
        Scanner mockScanner = new Scanner("paco\n111-AAA\n");
        IssueActions.issueBookToStudent(mockScanner, hub);
        String output = outContent.toString();
        assertTrue(output.contains("✅ Issue created successfully:"));
        assertTrue(output.contains("Java Basics"));
        assertTrue(output.contains("paco"));
        assertEquals(4, hub.booksList.get(0).getQuantity());
        assertEquals(1, hub.issuesList.size());
    }

    @Test
    void issueBookToStudentWithNonExistentBook() {
        Scanner mockScanner = new Scanner("paco\n999-ZZZ\n");
        IssueActions.issueBookToStudent(mockScanner, hub);
        String output = outContent.toString();
        assertTrue(output.contains("❌ Book with ISBN 999-ZZZ not found."));
        assertEquals(0, hub.issuesList.size());
    }

    @Test
    void issueBookToStudentWithNoAvailableCopies() {
        hub.booksList.get(0).setQuantity(0);
        Scanner mockScanner = new Scanner("paco\n111-AAA\n");
        IssueActions.issueBookToStudent(mockScanner, hub);
        String output = outContent.toString();
        assertTrue(output.contains("⚠ No copies available for this book."));
        assertEquals(0, hub.issuesList.size());
    }

    @Test
    void issueBookToStudentWithNewStudent() {
        Scanner mockScanner = new Scanner("newStudent\n111-AAA\n");
        IssueActions.issueBookToStudent(mockScanner, hub);
        String output = outContent.toString();
        assertTrue(output.contains("✅ Created new student: newStudent"));
        assertTrue(output.contains("✅ Issue created successfully:"));
        assertTrue(output.contains("Java Basics"));
        assertTrue(output.contains("newStudent"));
        assertEquals(2, hub.studentsList.size());
        assertEquals(1, hub.issuesList.size());
    }

    @Test
    void issueBookToStudentWithEmptyName() {
        Scanner mockScanner = new Scanner("\n111-AAA\n");
        IssueActions.issueBookToStudent(mockScanner, hub);
        String output = outContent.toString();
        assertTrue(output.contains("✅ Created new student: "));
        assertTrue(output.contains("✅ Issue created successfully:"));
        assertEquals(2, hub.studentsList.size());
        assertEquals(1, hub.issuesList.size());
    }

    @Test
    void issueBookToStudentWithEmptyIsbn() {
        Scanner mockScanner = new Scanner("paco\n\n");
        IssueActions.issueBookToStudent(mockScanner, hub);
        String output = outContent.toString();
        assertTrue(output.contains("❌ Book with ISBN  not found."));
        assertEquals(0, hub.issuesList.size());
    }
}
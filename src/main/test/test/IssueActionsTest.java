package test;

import org.YronJack.models.*;
import org.YronJack.utils.IssueActions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IssueActionsTest {
    private Hub hub;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        hub = new Hub();

        // Use mutable lists for testing
        hub.booksList = new ArrayList<>();
        hub.studentsList = new ArrayList<>();
        hub.issuesList = new ArrayList<>();

        // Create two books with known data
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

        // Create one student
        Student s1 = new Student("paco");
        hub.studentsList.add(s1);

        // Redirect System.out to capture printed output for assertions
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void issueBookToStudentWithValidData() {
        Scanner mockScanner = new Scanner("paco\n111-AAA\n");
        IssueActions.issueBookToStudent(mockScanner, hub);
        String output = outContent.toString();

        System.out.println("Salida capturada:\n" + output); // <- Agrega esta línea para ver la salida real en consola

        assertTrue(output.contains("✅ Issue created successfully:"), "No se encontró mensaje de issue creado");
        // Ajusta o comenta esta línea si no se imprime el título:
        assertTrue(output.contains("Java Basics"), "No se encontró el título del libro en la salida");
        assertTrue(output.contains("paco"), "No se encontró el nombre del estudiante en la salida");
        assertEquals(4, hub.booksList.get(0).getQuantity(), "Cantidad del libro no decrementada");
        assertEquals(1, hub.issuesList.size(), "No se añadió el issue a la lista");

        mockScanner.close();
    }

    @Test
    void issueBookToStudentWithNonExistentBook() {
        Scanner mockScanner = new Scanner("paco\n999-ZZZ\n");
        IssueActions.issueBookToStudent(mockScanner, hub);
        String output = outContent.toString();

        assertTrue(output.contains("❌ Book with ISBN 999-ZZZ not found."), "No se encontró mensaje de libro no existente");
        assertEquals(0, hub.issuesList.size(), "No debería haberse añadido ningún issue");

        mockScanner.close();
    }

    @Test
    void issueBookToStudentWithNoAvailableCopies() {
        hub.booksList.get(0).setQuantity(0);
        Scanner mockScanner = new Scanner("paco\n111-AAA\n");
        IssueActions.issueBookToStudent(mockScanner, hub);
        String output = outContent.toString();

        assertTrue(output.contains("⚠ No copies available for this book."), "No se encontró mensaje de libro sin copias");
        assertEquals(0, hub.issuesList.size(), "No debería haberse añadido ningún issue");

        mockScanner.close();
    }

    @Test
    void issueBookToStudentWithNewStudent() {
        Scanner mockScanner = new Scanner("newStudent\n111-AAA\n");
        IssueActions.issueBookToStudent(mockScanner, hub);
        String output = outContent.toString();

        assertTrue(output.contains("✅ Created new student: newStudent"), "No se encontró mensaje de creación de estudiante");
        assertTrue(output.contains("✅ Issue created successfully:"), "No se encontró mensaje de issue creado");
        assertTrue(output.contains("Java Basics"), "No se encontró el título del libro en la salida");
        assertTrue(output.contains("newStudent"), "No se encontró el nombre del nuevo estudiante en la salida");
        assertEquals(2, hub.studentsList.size(), "No se añadió el nuevo estudiante a la lista");
        assertEquals(1, hub.issuesList.size(), "No se añadió el issue a la lista");

        mockScanner.close();
    }

    @Test
    void issueBookToStudentWithEmptyName() {
        Scanner mockScanner = new Scanner("\n111-AAA\n");
        IssueActions.issueBookToStudent(mockScanner, hub);
        String output = outContent.toString();

        assertTrue(output.contains("✅ Created new student: "), "No se encontró mensaje de creación de estudiante con nombre vacío");
        assertTrue(output.contains("✅ Issue created successfully:"), "No se encontró mensaje de issue creado");
        assertEquals(2, hub.studentsList.size(), "No se añadió el estudiante con nombre vacío a la lista");
        assertEquals(1, hub.issuesList.size(), "No se añadió el issue a la lista");

        mockScanner.close();
    }

    @Test
    void issueBookToStudentWithEmptyIsbn() {
        Scanner mockScanner = new Scanner("paco\n\n");
        IssueActions.issueBookToStudent(mockScanner, hub);
        String output = outContent.toString();

        assertTrue(output.contains("❌ Book with ISBN  not found."), "No se encontró mensaje de libro con ISBN vacío");
        assertEquals(0, hub.issuesList.size(), "No debería haberse añadido ningún issue");

        mockScanner.close();
    }
}

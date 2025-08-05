package org.YronJack.store;

import org.YronJack.models.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IssueStoreTest {

    private static final String TEST_ISSUES_FILE = "data/test_issues.csv";
    private Hub hub;

    @BeforeEach
    void setUp() throws IOException {
        // Limpiar o crear archivo de prueba
        File file = new File(TEST_ISSUES_FILE);
        if (file.exists()) file.delete();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("issueId,usn,isbn,issueDate,returnDate");
            bw.newLine();
        }

        hub = new Hub();
        hub.getStudentsList().clear();
        hub.getBooksList().clear();

        // Añadir datos de prueba para Hub
        hub.getStudentsList().add(new Student("S001", "Alice"));
        hub.getStudentsList().add(new Student("S002", "Bob"));

        hub.getBooksList().add(new Book("111-111", "Book One", "Fiction", 2, "Author A", false));
        hub.getBooksList().add(new Book("222-222", "Book Two", "Non-Fiction", 1, "Author B", false));

        // Cambiar ruta del archivo para IssueStore (hay que implementar esto en IssueStore)
        IssueStore.setIssuesFileForTests(TEST_ISSUES_FILE);
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_ISSUES_FILE);
        if (file.exists()) file.delete();
    }

    @Test
    void testGetNextIssueId() {
        int id1 = IssueStore.getNextIssueId();
        int id2 = IssueStore.getNextIssueId();
        assertEquals(id1 + 1, id2);
    }

    @Test
    void testSaveAndLoadIssue() throws IOException {
        Student student = hub.getStudentsList().get(0);
        Book book = hub.getBooksList().get(0);

        Issue issue = new Issue(IssueStore.getNextIssueId(), LocalDate.now(), LocalDate.now().plusDays(7), student, book);

        IssueStore.saveIssue(issue);

        List<Issue> loadedIssues = IssueStore.loadIssues(hub);

        assertFalse(loadedIssues.isEmpty());
        Issue loaded = loadedIssues.get(0);

        assertEquals(issue.getIssueId(), loaded.getIssueId());
        assertEquals(issue.getIssueStudent().getUsn(), loaded.getIssueStudent().getUsn());
        assertEquals(issue.getIssueBook().getIsbn(), loaded.getIssueBook().getIsbn());
        assertEquals(issue.getIssueDate(), loaded.getIssueDate());
        assertEquals(issue.getReturnDate(), loaded.getReturnDate());
    }

    @Test
    void testLoadIssuesWithUnknownStudentAndBook() throws IOException {
        // Añadir una línea con un estudiante y libro que no están en el Hub
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TEST_ISSUES_FILE, true))) {
            bw.write("1,UNKNOWN_USN,UNKNOWN_ISBN,2025-08-05,2025-08-12");
            bw.newLine();
        }

        List<Issue> issues = IssueStore.loadIssues(hub);

        assertFalse(issues.isEmpty());

        Issue issue = issues.stream()
                .filter(i -> i.getIssueStudent().getUsn().equals("UNKNOWN_USN"))
                .findFirst()
                .orElse(null);

        assertNotNull(issue);
        assertEquals("UNKNOWN_ISBN", issue.getIssueBook().getIsbn());

        // También deberían haberse agregado al hub
        assertTrue(hub.getStudentsList().stream().anyMatch(s -> s.getUsn().equals("UNKNOWN_USN")));
        assertTrue(hub.getBooksList().stream().anyMatch(b -> b.getIsbn().equals("UNKNOWN_ISBN")));
    }
}


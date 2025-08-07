package test;

import org.YronJack.models.*;
import org.YronJack.store.IssueStore;
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
        // Create or clear the test CSV file
        File file = new File(TEST_ISSUES_FILE);
        if (file.exists()) file.delete();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("issueId,usn,isbn,issueDate,returnDate");
            bw.newLine();
        }

        // Initialize hub and clear existing lists
        hub = new Hub();
        hub.getStudentsList().clear();
        hub.getBooksList().clear();

        // Add sample students to the hub
        hub.getStudentsList().add(new Student("S001", "Alice"));
        hub.getStudentsList().add(new Student("S002", "Bob"));

        // Add sample books to the hub
        hub.getBooksList().add(new Book("111-111", "Book One", "Fiction", 2, "Author A", false));
        hub.getBooksList().add(new Book("222-222", "Book Two", "Non-Fiction", 1, "Author B", false));

        // Set the test file for IssueStore
        IssueStore.setIssuesFileForTests(TEST_ISSUES_FILE);
    }

    @AfterEach
    void tearDown() {
        // Delete the test CSV file after each test
        File file = new File(TEST_ISSUES_FILE);
        if (file.exists()) file.delete();
    }

    @Test
    void testGetNextIssueId() {
        // Check if IssueStore correctly increments IDs
        int id1 = IssueStore.getNextIssueId();
        int id2 = IssueStore.getNextIssueId();
        assertEquals(id1 + 1, id2);
    }

    @Test
    void testSaveAndLoadIssue() throws IOException {
        // Create and save an issue, then reload it and validate the data
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
        // Add an issue row to the CSV with unknown student and book
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TEST_ISSUES_FILE, true))) {
            bw.write("1,UNKNOWN_USN,UNKNOWN_ISBN,2025-08-05,2025-08-12");
            bw.newLine();
        }

        List<Issue> issues = IssueStore.loadIssues(hub);

        assertFalse(issues.isEmpty());

        // Validate that the unknown student and book were created and loaded correctly
        Issue issue = issues.stream()
                .filter(i -> i.getIssueStudent().getUsn().equals("UNKNOWN_USN"))
                .findFirst()
                .orElse(null);

        assertNotNull(issue);
        assertEquals("UNKNOWN_ISBN", issue.getIssueBook().getIsbn());

        // Ensure the unknown student and book were added to the hub
        assertTrue(hub.getStudentsList().stream().anyMatch(s -> s.getUsn().equals("UNKNOWN_USN")));
        assertTrue(hub.getBooksList().stream().anyMatch(b -> b.getIsbn().equals("UNKNOWN_ISBN")));
    }
}

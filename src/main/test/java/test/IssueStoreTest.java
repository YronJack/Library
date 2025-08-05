//package test;
//
//import org.YronJack.models.Book;
//import org.YronJack.models.Issue;
//import org.YronJack.models.Student;
//import org.YronJack.store.IssueStore;
//import org.junit.jupiter.api.*;
//import java.io.*;
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class IssueStoreTest {
//
//    private static final String TEST_FILE = "data/test_issues.csv";
//
//    @BeforeEach
//    void setUp() throws IOException {
//        // Crear archivo de prueba con encabezado
//        File file = new File(TEST_FILE);
//        file.getParentFile().mkdirs();
//        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
//            writer.println("issueId,usn,isbn,issueDate,returnDate");
//            writer.println("1,STU123,ISBN123,2023-01-01,2023-01-15");
//        }
//        // Cambiar el nombre del archivo en IssueStore usando reflexión
//        var field = IssueStore.class.getDeclaredField("ISSUES_CSV");
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
//    void testLoadIssues() {
//        List<Issue> issues = IssueStore.loadIssues();
//        assertEquals(1, issues.size());
//        assertEquals(1, issues.get(0).getIssueId());
//        assertEquals("STU123", issues.get(0).getIssueStudent().getUsn());
//    }
//
//    @Test
//    void testSaveIssue() throws IOException {
//        Issue newIssue = new Issue(2, LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 15),
//                new Student("STU456", "John Doe"), new Book("ISBN456", "Test Book", "Fiction", 1, "Author", false));
//        IssueStore.saveIssue(newIssue);
//
//        List<Issue> issues = IssueStore.loadIssues();
//        assertEquals(2, issues.size());
//        assertTrue(issues.stream().anyMatch(issue -> issue.getIssueId() == 2));
//    }
//
//    @Test
//    void testGetNextIssueId() {
//        int nextId = IssueStore.getNextIssueId();
//        assertEquals(2, nextId); // El archivo de prueba tiene un issue con ID 1
//    }
//}
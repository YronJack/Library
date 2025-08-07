package test;

import org.YronJack.models.Student;
import org.YronJack.store.StudentStore;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentStoreTest {

    private static final String TEST_STUDENTS_FILE = "data/test_students.csv";
    private StudentStore store;

    @BeforeEach
    void setUp() throws IOException {
        // Clean up or create the test file
        File file = new File(TEST_STUDENTS_FILE);
        if (file.exists()) file.delete();

        StudentStore.setFileNameForTests(TEST_STUDENTS_FILE);
        store = new StudentStore();
    }

    @AfterEach
    void tearDown() {
        // Delete the test file after each test
        File file = new File(TEST_STUDENTS_FILE);
        if (file.exists()) file.delete();
    }

    @Test
    void testLoadStudentsFromEmptyFile() {
        // Should return an empty list if the file contains no student data
        List<Student> students = store.loadStudents();
        assertTrue(students.isEmpty(), "The list should be empty if no data is present");
    }

    @Test
    void testSaveAndLoadStudent() {
        // Should save a student and load it back correctly
        Student student = new Student("S001", "Alice");
        StudentStore.saveStudent(student);

        List<Student> students = store.loadStudents();
        assertEquals(1, students.size()); // Only one student should be loaded
        assertEquals("S001", students.get(0).getUsn()); // Check USN
        assertEquals("Alice", students.get(0).getName()); // Check name
    }

    @Test
    void testFileIsCreatedIfNotExists() {
        // The CSV file should be automatically created if it does not exist
        File file = new File(TEST_STUDENTS_FILE);
        assertTrue(file.exists(), "The CSV file should be automatically created");
    }
}

package org.YronJack.store;

import org.YronJack.models.Student;
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
        // Limpiar o crear archivo de prueba
        File file = new File(TEST_STUDENTS_FILE);
        if (file.exists()) file.delete();

        StudentStore.setFileNameForTests(TEST_STUDENTS_FILE);
        store = new StudentStore();
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_STUDENTS_FILE);
        if (file.exists()) file.delete();
    }

    @Test
    void testLoadStudentsFromEmptyFile() {
        List<Student> students = store.loadStudents();
        assertTrue(students.isEmpty(), "La lista debería estar vacía si no hay datos");
    }

    @Test
    void testSaveAndLoadStudent() {
        Student student = new Student("S001", "Alice");
        StudentStore.saveStudent(student);

        List<Student> students = store.loadStudents();
        assertEquals(1, students.size());
        assertEquals("S001", students.get(0).getUsn());
        assertEquals("Alice", students.get(0).getName());
    }

    @Test
    void testFileIsCreatedIfNotExists() {
        File file = new File(TEST_STUDENTS_FILE);
        assertTrue(file.exists(), "El archivo CSV debería crearse automáticamente");
    }
}

//package test;
//
//import org.YronJack.actions.IssueAction;
//import org.YronJack.models.Book;
//import org.YronJack.models.Issue;
//import org.YronJack.models.Student;
//import org.YronJack.store.IssueStore;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito-core;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class IssueActionTest {
//
//    @Test
//    void testExecuteCreatesAndSavesIssue() {
//        // Mock de IssueStore
//        IssueStore issueStore = Mockito.mock(IssueStore.class);
//
//        // Datos de prueba
//        Student student = new Student("STU123", "Juan Pérez");
//        Book book = new Book("ISBN123", "Libro Prueba", "Ficción", 1, "Autor", false);
//        LocalDate issueDate = LocalDate.of(2024, 6, 1);
//        LocalDate returnDate = LocalDate.of(2024, 6, 15);
//
//        // Crear acción
//        IssueAction action = new IssueAction(1, student, book, issueDate, returnDate, issueStore);
//
//        // Ejecutar acción
//        Issue issue = action.execute();
//
//        // Verificar que la incidencia se creó correctamente
//        assertNotNull(issue);
//        assertEquals(1, issue.getIssueId());
//        assertEquals(student, issue.getIssueStudent());
//        assertEquals(book, issue.getIssueBook());
//        assertEquals(issueDate, issue.getIssueDate());
//        assertEquals(returnDate, issue.getReturnDate());
//
//        // Verificar que se llamó a saveIssue
//        Mockito.verify(issueStore).saveIssue(issue);
//    }
//}

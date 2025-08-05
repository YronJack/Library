//package test;
//
//import org.YronJack.utils.CreateBookAction;
//import org.YronJack.models.Book;
//import org.YronJack.store.BookStore;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito-core;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CreateBookActionTest {
//
//    @Test
//    void testExecuteCreatesAndSavesBook() {
//        // Mock de BookStore
//        BookStore bookStore = Mockito.mock(BookStore.class);
//
//        // Crear acción con datos de prueba
//        CreateBookAction action = new CreateBookAction("12345", "Libro Prueba", "Ficción", "Autor", 5, bookStore);
//
//        // Ejecutar acción
//        Book book = action.execute();
//
//        // Verificar que el libro se creó correctamente
//        assertNotNull(book);
//        assertEquals("12345", book.getIsbn());
//        assertEquals("Libro Prueba", book.getTitle());
//        assertEquals("Ficción", book.getCategory());
//        assertEquals("Autor", book.getAuthor());
//        assertEquals(5, book.getQuantity());
//
//        // Verificar que se llamó a saveBook
//        Mockito.verify(bookStore).saveBook(book);
//    }
//}

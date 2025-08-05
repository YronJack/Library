//package test;
//
//import org.YronJack.utils.SearchActions;
//import org.YronJack.models.Book;
//import org.YronJack.store.BookStore;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito-core;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class SearchActionTest {
//
//    @Test
//    void testExecuteSearchesBooksByTitle() {
//        // Mock de BookStore
//        BookStore bookStore = Mockito.mock(BookStore.class);
//
//        // Libros de prueba
//        Book book1 = new Book("ISBN1", "El Quijote", "Novela", 2, "Cervantes", false);
//        Book book2 = new Book("ISBN2", "Cien Años de Soledad", "Novela", 1, "García Márquez", false);
//
//        // Simular búsqueda por título
//        Mockito.when(SearchActions.searchByTitle("Quijote")).thenReturn(Arrays.asList(book1));
//
//        // Crear acción de búsqueda
//        SearchActions action = new SearchActions("Quijote", bookStore);
//
//        // Ejecutar acción
//        List<Book> result = action.execute();
//
//        // Verificar resultados
//        assertEquals(1, result.size());
//        assertEquals("El Quijote", result.get(0).getTitle());
//
//        // Verificar que se llamó al método correcto
//        Mockito.verify(bookStore).searchByTitle("Quijote");
//    }
//}

package org.YronJack.utils;

import org.YronJack.models.Book;
import org.YronJack.models.Hub;

import java.util.List;
import java.util.stream.Collectors;

public class SearchActions {

    private static String truncate(String text, int maxLength) {
        if (text == null) return "N/A";
        if (text.length() <= maxLength) return text;
        return text.substring(0, maxLength - 3) + "...";
    }

    public static void searchByTitle(String title, Hub hub) {
        List<Book> results = hub.booksList.stream()
                .filter(book -> book.getTitle() != null && book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());

        showResults(results);
    }

    public static void searchByAuthor(String authorName, Hub hub) {
        List<Book> results = hub.booksList.stream()
                .filter(book -> book.getAuthor() != null &&
                        book.getAuthor().getName() != null &&
                        book.getAuthor().getName().toLowerCase().contains(authorName.toLowerCase()))
                .collect(Collectors.toList());

        showResults(results);
    }

    public static void searchByCategory(String category, Hub hub) {
        List<Book> results = hub.booksList.stream()
                .filter(book -> book.getCategory() != null &&
                        book.getCategory().toLowerCase().contains(category.toLowerCase()))
                .collect(Collectors.toList());

        showResults(results);
    }

    private static void showResults(List<Book> results) {
        if (results.isEmpty()) {
            System.out.println("⚠ No books found.");
            return;
        }

        String top =    "╔════════════════════╦════════════════════════════════╦═════════════════╦═══════════════════════════╦══════════════╗";
        String header = "╠════════════════════╬════════════════════════════════╬═════════════════╬═══════════════════════════╬══════════════╣";
        String bottom = "╚════════════════════╩════════════════════════════════╩═════════════════╩═══════════════════════════╩══════════════╝";

        System.out.println();
        System.out.println(top);
        System.out.printf("║ %-18s ║ %-30s ║ %-15s ║ %-25s ║ %-12s ║\n",
                "ISBN", "Title", "Category", "Author", "Quantity");
        System.out.println(header);

        for (Book book : results) {
            String authorName = (book.getAuthor() != null && book.getAuthor().getName() != null) ?
                    truncate(book.getAuthor().getName(), 25) : "Unknown";

            System.out.printf("║ %-18s ║ %-30s ║ %-15s ║ %-25s ║ %-12d ║\n",
                    truncate(book.getIsbn(), 18),
                    truncate(book.getTitle(), 30),
                    truncate(book.getCategory(), 15),
                    authorName,
                    book.getQuantity());
        }

        System.out.println(bottom);
    }
}




//package org.YronJack.utils;
//
//import org.YronJack.models.Book;
//import org.YronJack.models.Hub;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class SearchActions {
//
//    public static void searchByTitle(String title, Object hub) {
//        List<Book> books = Hub.booksList;
//        List<Book> results = books.stream()
//                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
//                .collect(Collectors.toList());
//
//        showResults(results);
//    }
//
//    public static void searchByAuthor(String authorName, Object hub) {
//        List<Book> books = Hub.booksList;
//        List<Book> results = books.stream()
//                .filter(book -> book.getAuthor() != null &&
//                        book.getAuthor().getName().toLowerCase().contains(authorName.toLowerCase()))
//                .collect(Collectors.toList());
//
//        showResults(results);
//    }
//
//    public static void searchByCategory(String category, Object hub) {
//        List<Book> books = Hub.booksList;
//        List<Book> results = books.stream()
//                .filter(book -> book.getCategory().toLowerCase().contains(category.toLowerCase()))
//                .collect(Collectors.toList());
//
//        showResults(results);
//    }
//
//    private static void showResults(List<Book> results) {
//        if (results.isEmpty()) {
//            System.out.println("⚠ No books found.");
//            return;
//        }
//
//        System.out.println("\n📚 Search Results:");
//
//        System.out.printf("%-15s | %-30s | %-20s | %-20s | %-8s%n",
//                "ISBN", "Title", "Category", "Author", "Quantity");
//        System.out.println("------------------------------------------------------------------------------------------------------");
//
//        for (Book book : results) {
//            System.out.printf("%-15s | %-30s | %-20s | %-20s | %-8d%n",
//                    book.getIsbn(),
//                    book.getTitle(),
//                    book.getCategory(),
//                    (book.getAuthor() != null ? book.getAuthor().getName() : "Unknown"),
//                    book.getQuantity()
//            );
//        }
//    }
//}

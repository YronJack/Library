package org.YronJack.utils;

import org.YronJack.models.Book;
import org.YronJack.models.Hub;

import java.util.List;
import java.util.stream.Collectors;

public class SearchActions {

    public static void searchByTitle(String title, Object hub) {
        List<Book> books = Hub.booksList;
        List<Book> results = books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());

        showResults(results);
    }

    public static void searchByAuthor(String authorName, Object hub) {
        List<Book> books = Hub.booksList;
        List<Book> results = books.stream()
                .filter(book -> book.getAuthor() != null &&
                        book.getAuthor().getName().toLowerCase().contains(authorName.toLowerCase()))
                .collect(Collectors.toList());

        showResults(results);
    }

    public static void searchByCategory(String category, Object hub) {
        List<Book> books = Hub.booksList;
        List<Book> results = books.stream()
                .filter(book -> book.getCategory().toLowerCase().contains(category.toLowerCase()))
                .collect(Collectors.toList());

        showResults(results);
    }

    private static void showResults(List<Book> results) {
        if (results.isEmpty()) {
            System.out.println("⚠ No books found.");
            return;
        }

        System.out.println("\n📚 Search Results:");

        System.out.printf("%-15s | %-30s | %-20s | %-20s | %-8s%n",
                "ISBN", "Title", "Category", "Author", "Quantity");
        System.out.println("------------------------------------------------------------------------------------------------------");

        for (Book book : results) {
            System.out.printf("%-15s | %-30s | %-20s | %-20s | %-8d%n",
                    book.getIsbn(),
                    book.getTitle(),
                    book.getCategory(),
                    (book.getAuthor() != null ? book.getAuthor().getName() : "Unknown"),
                    book.getQuantity()
            );
        }
    }
}



//package org.YronJack.utils;
//
//import org.YronJack.models.Book;
//import org.YronJack.models.Hub;
//
//
//public class SearchActions {
//
//
//
//    public static Book searchByAuthor(String author, Hub patata) {
//       for(Book bookOnList : patata.booksList){
//           while(author.equals(bookOnList.getAuthor().getName())){
//               System.out.println(bookOnList.getInfo());
//               return bookOnList;
//           }
//       } return null;
//    }
//
//    public static Book searchByCategory(String category, Hub patata) {
//        for (Book bookOnList : patata.booksList) {
//            if (bookOnList.getCategory().equals(category)) {
//                System.out.println(bookOnList.getInfo());
//                return bookOnList;
//            }
//        }
//        return null;
//    }
//
//
//
//    public static  Book searchByTitle(String title, Hub patata) {
//
//        for (Book bookOnList : patata.booksList) {
//            if (bookOnList.getTitle().equals(title)) {
//                System.out.println(bookOnList.getInfo());
//                return bookOnList;
//            }
//        }
//        return null;
//    }
//}

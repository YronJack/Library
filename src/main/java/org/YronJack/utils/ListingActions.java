package org.YronJack.utils;

import org.YronJack.models.Book;
import org.YronJack.models.Hub;
import org.YronJack.models.Issue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListingActions {

    public static void listAllBooks(Hub hub) {
        // Same logic
        if (hub.booksList.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-20s | %-40s | %-15s | %-12s | %-25s | %-30s ║\n",
                "Book ISBN", "Book Title", "Category", "No of Books", "Author name", "Author mail");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        for (Book book : hub.booksList) {
            String authorName = (book.getAuthor() != null) ? book.getAuthor().getName() : "N/A";
            String authorMail = (book.getAuthor() != null && book.getAuthor().getEmail() != null) ? book.getAuthor().getEmail() : "N/A";

            System.out.printf("║ %-20s | %-40s | %-15s | %-12d | %-25s | %-30s ║\n",
                    book.getIsbn(),
                    book.getTitle(),
                    book.getCategory(),
                    book.getQuantity(),
                    authorName,
                    authorMail);
        }
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    public static void listBooksByUsn(Scanner scanner, Hub hub) {
        // Same logic
        System.out.print("Enter USN: ");
        String usn = scanner.nextLine().trim();

        boolean found = false;
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-40s | %-25s | %-35s ║\n",
                "Book Title", "Student Name", "Return date");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════════════════════════╣");

        for (Issue issue : hub.issuesList) {
            if (issue.getIssueStudent() != null && issue.getIssueStudent().getUsn().equals(usn)) {
                System.out.printf("║ %-40s | %-25s | %-35s ║\n",
                        issue.getIssueBook().getTitle(),
                        issue.getIssueStudent().getName(),
                        issue.getReturnDate());
                found = true;
            }
        }
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════╝");

        if (!found) {
            System.out.println("No books found for student with USN " + usn + ".");
        }
    }

    public static void listBooksToReturnToday(Hub hub) {
        // Same logic
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        boolean found = false;
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-40s | %-25s | %-35s ║\n",
                "Book Title", "Student Name", "Return date");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════════════════════════╣");

        for (Issue issue : hub.issuesList) {
            if (issue.getReturnDate() != null) {
                try {
                    LocalDate returnDate = LocalDate.parse(issue.getReturnDate(), formatter);
                    if (returnDate.isEqual(today)) {
                        System.out.printf("║ %-40s | %-25s | %-35s ║\n",
                                issue.getIssueBook().getTitle(),
                                issue.getIssueStudent().getName(),
                                issue.getReturnDate());
                        found = true;
                    }
                } catch (Exception e) {
                    System.err.println("❗ Error parsing date: " + issue.getReturnDate());
                }
            }
        }
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════╝");

        if (!found) {
            System.out.println("No books are due to be returned today.");
        }
    }

    public static void listAllIssuedBooks(Hub hub) {
        if (hub.issuesList.isEmpty()) {
            System.out.println("No books have been issued.");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-20s | %-40s | %-25s | %-15s | %-15s ║\n",
                "Book ISBN", "Book Title", "Student Name", "Issue Date", "Return Date");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        for (Issue issue : hub.issuesList) {
            System.out.printf("║ %-20s | %-40s | %-25s | %-15s | %-15s ║\n",
                    issue.getIssueBook().getIsbn(),
                    issue.getIssueBook().getTitle(),
                    issue.getIssueStudent().getName(),
                    issue.getIssueDate(),
                    issue.getReturnDate());
        }
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    public static void listBooksByCategory(Scanner scanner, Hub hub) {
        System.out.print("Enter category: ");
        String category = scanner.nextLine().trim();

        List<Book> booksInCategory = hub.booksList.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());

        if (booksInCategory.isEmpty()) {
            System.out.println("No books found in category '" + category + "'.");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-20s | %-40s | %-15s | %-12s ║\n",
                "Book ISBN", "Book Title", "Category", "No of Books");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════════════════════════╣");

        for (Book book : booksInCategory) {
            System.out.printf("║ %-20s | %-40s | %-15s | %-12d ║\n",
                    book.getIsbn(),
                    book.getTitle(),
                    book.getCategory(),
                    book.getQuantity());
        }
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    public static void listBooksNeverIssued(Hub hub) {
        List<String> issuedIsbns = hub.issuesList.stream()
                .map(issue -> issue.getIssueBook().getIsbn())
                .collect(Collectors.toList());

        List<Book> neverIssuedBooks = new ArrayList<>();
        for (Book book : hub.booksList) {
            if (!issuedIsbns.contains(book.getIsbn())) {
                neverIssuedBooks.add(book);
            }
        }

        if (neverIssuedBooks.isEmpty()) {
            System.out.println("All books have been issued at least once.");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-20s | %-40s | %-15s | %-12s ║\n",
                "Book ISBN", "Book Title", "Category", "No of Books");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════════════════════════╣");

        for (Book book : neverIssuedBooks) {
            System.out.printf("║ %-20s | %-40s | %-15s | %-12d ║\n",
                    book.getIsbn(),
                    book.getTitle(),
                    book.getCategory(),
                    book.getQuantity());
        }
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    public static void listOverdueStudents(Hub hub) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean found = false;

        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-25s | %-40s | %-20s | %-15s ║\n",
                "Student Name", "Book Title", "Book ISBN", "Overdue Date");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        for (Issue issue : hub.issuesList) {
            if (issue.getReturnDate() != null) {
                try {
                    LocalDate returnDate = LocalDate.parse(issue.getReturnDate(), formatter);
                    if (returnDate.isBefore(today)) {
                        System.out.printf("║ %-25s | %-40s | %-20s | %-15s ║\n",
                                issue.getIssueStudent().getName(),
                                issue.getIssueBook().getTitle(),
                                issue.getIssueBook().getIsbn(),
                                issue.getReturnDate());
                        found = true;
                    }
                } catch (Exception e) {
                    System.err.println("❗ Error parsing date for issue: " + issue.getReturnDate());
                }
            }
        }
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");

        if (!found) {
            System.out.println("No overdue books found.");
        }
    }
}
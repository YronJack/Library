package org.YronJack.menus;

import org.YronJack.models.Book;
import org.YronJack.models.Hub;
import org.YronJack.models.Issue;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListMenu {
    public static void listMenu(Scanner scanner, Hub patata) {
        int option = -1;

        do {
            System.out.println("\n\n ┌──────────────────────────────────────────────────┐");
            System.out.println(" │                 Listings Menu                    │");
            System.out.println(" ├──────────────────────────────────────────────────┤");
            System.out.println(" │ 1. List all books                                │");
            System.out.println(" │ 2. List books by student                         │");
            System.out.println(" │ 3. List books to be returned today               │");
            System.out.println(" │ 4. List all issued books                         │");
            System.out.println(" │ 5. List all books by a specific category         │");
            System.out.println(" │ 6. List books that have never been issued        │");
            System.out.println(" │ 7. List overdue students                         │");
            System.out.println(" │ 8. Back to Main Menu                             │");
            System.out.println(" └──────────────────────────────────────────────────┘");

            boolean validInput = false;

            while (!validInput) {
                System.out.print("Select an option: ");
                try {
                    option = Integer.parseInt(scanner.nextLine());
                    if (option >= 1 && option <= 8) {
                        validInput = true;
                    } else {
                        System.out.println("❗ Invalid option. Please enter a number between 1 and 8.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❗ Invalid input. Please enter a number.");
                }
            }

            switch (option) {
                case 1:
                    listAllBooks(patata);
                    break;
                case 2:
                    listBooksByUsn(scanner, patata);
                    break;
                case 3:
                    listBooksToReturnToday(patata);
                    break;
                case 4:
                    listAllIssuedBooks(patata);
                    break;
                case 5:
                    listBooksByCategory(scanner, patata);
                    break;
                case 6:
                    listBooksNeverIssued(patata);
                    break;
                case 7:
                    listOverdueStudents(patata);
                    break;
                case 8:
                    System.out.println("👋 Returning to Main Menu.");
                    break;
            }
        } while (option != 8);
    }

    private static void listAllBooks(Hub hub) {
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

    private static void listBooksByUsn(Scanner scanner, Hub hub) {
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

    private static void listBooksToReturnToday(Hub hub) {
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

    private static void listAllIssuedBooks(Hub hub) {
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

    private static void listBooksByCategory(Scanner scanner, Hub hub) {
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

    private static void listBooksNeverIssued(Hub hub) {
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

    private static void listOverdueStudents(Hub hub) {
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
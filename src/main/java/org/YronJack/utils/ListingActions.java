package org.YronJack.utils;

import org.YronJack.models.Book;
import org.YronJack.models.Hub;
import org.YronJack.models.Issue;
import org.YronJack.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListingActions {

    private static String truncate(String text, int maxLength) {
        if (text == null) return "N/A";
        if (text.length() <= maxLength) return text;
        return text.substring(0, maxLength - 3) + "...";
    }

    public static void listAllBooks(Hub hub) {
        if (hub.booksList.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        String top       = "╔════════════════════╦════════════════════════════════╦═════════════════╦══════════════╦═══════════════════════════╦════════════════════════════╗";
        String headerSep = "╠════════════════════╬════════════════════════════════╬═════════════════╬══════════════╬═══════════════════════════╬════════════════════════════╣";
        String bottom    = "╚════════════════════╩════════════════════════════════╩═════════════════╩══════════════╩═══════════════════════════╩════════════════════════════╝";

        System.out.println();
        System.out.println(top);
        System.out.printf("║ %-18s ║ %-30s ║ %-15s ║ %-12s ║ %-25s ║ %-26s ║\n",
                "Book ISBN", "Book Title", "Category", "No of Books", "Author name", "Author mail");
        System.out.println(headerSep);

        for (Book book : hub.booksList) {
            String authorName = (book.getAuthor() != null) ? book.getAuthor().getName() : "N/A";
            String authorMail = (book.getAuthor() != null && book.getAuthor().getEmail() != null) ? book.getAuthor().getEmail() : "N/A";

            System.out.printf("║ %-18s ║ %-30s ║ %-15s ║ %-12d ║ %-25s ║ %-26s ║\n",
                    book.getIsbn(),
                    book.getTitle(),
                    book.getCategory(),
                    book.getQuantity(),
                    authorName,
                    authorMail);
        }
        System.out.println(bottom);
    }

    public static void listBooksByUsn(Scanner scanner, Hub hub) {
        System.out.print("Enter USN: ");
        String usn = scanner.nextLine().trim();

        boolean found = false;

        String top       = "╔" + "═".repeat(47) + "╦" + "═".repeat(27) + "╦" + "═".repeat(20) + "╗";
        String headerSep = "╠" + "═".repeat(47) + "╬" + "═".repeat(27) + "╬" + "═".repeat(20) + "╣";
        String bottom    = "╚" + "═".repeat(47) + "╩" + "═".repeat(27) + "╩" + "═".repeat(20) + "╝";

        System.out.println();
        System.out.println(top);
        System.out.printf("║ %-45s ║ %-25s ║ %-18s ║\n", "Book Title", "Student Name", "Return date");
        System.out.println(headerSep);

        for (Issue issue : hub.issuesList) {
            if (issue.getIssueStudent() != null && issue.getIssueStudent().getUsn().equals(usn)) {
                String bookTitle = truncate(issue.getIssueBook().getTitle(), 45);
                String studentName = truncate(issue.getIssueStudent().getName(), 25);
                String returnDate = (issue.getReturnDate() != null) ? truncate(issue.getReturnDate().toString(), 18) : "N/A";

                System.out.printf("║ %-45s ║ %-25s ║ %-18s ║\n",
                        bookTitle,
                        studentName,
                        returnDate);
                found = true;
            }
        }

        System.out.println(bottom);

        if (!found) {
            System.out.println("No books found for student with USN " + usn + ".");
        }
    }

    public static void listAllIssuedBooks(Hub hub) {
        if (hub.issuesList.isEmpty()) {
            System.out.println("No books have been issued.");
            return;
        }

        String top       = "╔════════════════════╦════════════════════════════════╦═════════════════════════════╦════════════════╦════════════════╗";
        String headerSep = "╠════════════════════╬════════════════════════════════╬═════════════════════════════╬════════════════╬════════════════╣";
        String bottom    = "╚════════════════════╩════════════════════════════════╩═════════════════════════════╩════════════════╩════════════════╝";

        System.out.println();
        System.out.println(top);
        System.out.printf("║ %-18s ║ %-30s ║ %-27s ║ %-14s ║ %-14s ║\n",
                "Book ISBN", "Book Title", "Student Name", "Issue Date", "Return Date");
        System.out.println(headerSep);

        for (Issue issue : hub.issuesList) {
            System.out.printf("║ %-18s ║ %-30s ║ %-27s ║ %-14s ║ %-14s ║\n",
                    issue.getIssueBook().getIsbn(),
                    issue.getIssueBook().getTitle(),
                    issue.getIssueStudent().getName(),
                    issue.getIssueDate(),
                    issue.getReturnDate());
        }

        System.out.println(bottom);
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

        String top       = "╔════════════════════╦════════════════════════════════╦═════════════════╦══════════════╗";
        String headerSep = "╠════════════════════╬════════════════════════════════╬═════════════════╬══════════════╣";
        String bottom    = "╚════════════════════╩════════════════════════════════╩═════════════════╩══════════════╝";

        System.out.println();
        System.out.println(top);
        System.out.printf("║ %-18s ║ %-30s ║ %-15s ║ %-12s ║\n",
                "Book ISBN", "Book Title", "Category", "No of Books");
        System.out.println(headerSep);

        for (Book book : booksInCategory) {
            System.out.printf("║ %-18s ║ %-30s ║ %-15s ║ %-12d ║\n",
                    book.getIsbn(),
                    book.getTitle(),
                    book.getCategory(),
                    book.getQuantity());
        }
        System.out.println(bottom);
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

        String top       = "╔════════════════════╦════════════════════════════════╦═════════════════╦══════════════╗";
        String headerSep = "╠════════════════════╬════════════════════════════════╬═════════════════╬══════════════╣";
        String bottom    = "╚════════════════════╩════════════════════════════════╩═════════════════╩══════════════╝";

        System.out.println();
        System.out.println(top);
        System.out.printf("║ %-18s ║ %-30s ║ %-15s ║ %-12s ║\n",
                "Book ISBN", "Book Title", "Category", "No of Books");
        System.out.println(headerSep);

        for (Book book : neverIssuedBooks) {
            System.out.printf("║ %-18s ║ %-30s ║ %-15s ║ %-12d ║\n",
                    book.getIsbn(),
                    book.getTitle(),
                    book.getCategory(),
                    book.getQuantity());
        }
        System.out.println(bottom);
    }

    public static void listAllStudents(Hub hub) {
        List<Student> validStudents = hub.studentsList.stream()
                .filter(s -> s.getName() != null && !s.getName().equalsIgnoreCase("Unknown"))
                .collect(Collectors.toList());

        if (validStudents.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }

        String top       = "╔═════════════════╦═════════════════════════╗";
        String headerSep = "╠═════════════════╬═════════════════════════╣";
        String bottom    = "╚═════════════════╩═════════════════════════╝";

        System.out.println(top);
        System.out.printf("║ %-15s ║ %-23s ║\n", "USN", "Student Name");
        System.out.println(headerSep);

        for (Student student : validStudents) {
            System.out.printf("║ %-15s ║ %-23s ║\n",
                    student.getUsn(),
                    student.getName());
        }

        System.out.println(bottom);
    }
}
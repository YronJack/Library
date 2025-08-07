package org.YronJack.utils;

import org.YronJack.models.Hub;
import org.YronJack.models.Issue;
import org.YronJack.models.Student;
import org.YronJack.models.Book;
import org.YronJack.store.IssueStore;
import org.YronJack.store.StudentStore;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class IssueActions {
    private static String promptForName(Scanner scanner) {
        System.out.print("Enter name: ");
        return scanner.nextLine().trim();
    }

    private static String promptForIsbn(Scanner scanner) {
        System.out.print("Enter book ISBN: ");
        return scanner.nextLine().trim();
    }

    private static String cleanIsbn(String isbn) {
        return isbn.replace("-", "").trim();
    }

    private static Optional<Book> findBookByIsbn(Hub hub, String cleanIsbn) {
        return hub.getBooksList().stream()
                .filter(b -> b.getIsbn().replace("-", "").trim().equalsIgnoreCase(cleanIsbn))
                .findFirst();
    }

    private static boolean isBookAvailable(Book book) {
        return book.getQuantity() > 0;
    }

    private static Student findOrCreateStudent(Hub hub, String name) {
        Optional<Student> studentOpt = hub.getStudentsList().stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst();

        if (studentOpt.isEmpty()) {
            List<Student> allStudentsFromList = hub.studentsList;
            studentOpt = allStudentsFromList.stream()
                    .filter(s -> s.getName().equalsIgnoreCase(name))
                    .findFirst();

            studentOpt.ifPresent(student -> hub.getStudentsList().add(student));
        }

        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            return student;
        } else {
            Student student = new Student(name);
            hub.getStudentsList().add(student);
            StudentStore.saveStudent(student);
            System.out.println("\n✅ Created new student: " + student.getName() + " (USN: " + student.getUsn() + ")");
            return student;
        }
    }

    public static void issueBookToStudent(Scanner scanner, Hub hub) {
        String name = promptForName(scanner);
        String isbnInput = promptForIsbn(scanner);
        String cleanIsbnInput = cleanIsbn(isbnInput);

        Optional<Book> bookOpt = findBookByIsbn(hub, cleanIsbnInput);
        if (bookOpt.isEmpty()) {
            System.out.println("❌ Book with ISBN " + isbnInput + " not found.");
            return;
        }

        Book book = bookOpt.get();
        if (!isBookAvailable(book)) {
            System.out.println("⚠ No copies available for this book.");
            return;
        }

        Student student = findOrCreateStudent(hub, name);

        Issue newIssue = createNewIssue(student, book);
        hub.getIssuesList().add(newIssue);

        printIssueCreatedMessage(newIssue);

        try {
            IssueStore.saveIssue(newIssue);
        } catch (IOException e) {
            System.out.println("❌ Error saving issue to loans.csv: " + e.getMessage());
        }

        updateBookQuantity(book, hub, cleanIsbnInput);
    }

    private static Issue createNewIssue(Student student, Book book) {
        LocalDate issueDate = LocalDate.now();
        LocalDate returnDate = issueDate.plusDays(7);
        int newIssueId = IssueStore.getNextIssueId();

        return new Issue(
                newIssueId,
                issueDate,
                returnDate,
                student,
                book
        );
    }

    private static void updateBookQuantity(Book book, Hub hub, String cleanIsbnInput) {
        int updatedQuantity = book.getQuantity() - 1;
        book.setQuantity(updatedQuantity);

        Optional<Book> bookFromList = hub.booksList.stream()
                .filter(b -> b.getIsbn().replace("-", "").trim().equalsIgnoreCase(cleanIsbnInput))
                .findFirst();
    }

    private static void printIssueCreatedMessage(Issue issue) {
        String top = "╔════════════╦═══════════════╦════════════════════╦════════════════════╦════════════════════╦════════════════════╗";
        String headerSep = "╠════════════╬═══════════════╬════════════════════╬════════════════════╬════════════════════╬════════════════════╣";
        String bottom = "╚════════════╩═══════════════╩════════════════════╩════════════════════╩════════════════════╩════════════════════╝";

        System.out.println();
        System.out.println("✅ Issue created successfully:");
        System.out.println(top);
        System.out.printf("║ %-10s ║ %-13s ║ %-18s ║ %-18s ║ %-18s ║ %-18s ║\n",
                "Issue ID", "Student USN", "Student Name", "Book ISBN", "Book Title", "Return Date");
        System.out.println(headerSep);
        System.out.printf("║ %-10d ║ %-13s ║ %-18s ║ %-18s ║ %-18s ║ %-18s ║\n",
                issue.getIssueId(),
                issue.getIssueStudent().getUsn(),
                issue.getIssueStudent().getName(),
                issue.getIssueBook().getIsbn(),
                issue.getIssueBook().getTitle(),
                issue.getReturnDate().toString());
        System.out.println(bottom);
        System.out.println();
    }

}





//package org.YronJack.utils;
//
//import org.YronJack.models.Hub;
//import org.YronJack.models.Issue;
//import org.YronJack.models.Student;
//import org.YronJack.models.Book;
//import org.YronJack.store.IssueStore;
//import org.YronJack.store.StudentStore;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.util.*;
//
//public class IssueActions {
//    private static String promptForName(Scanner scanner) {
//        System.out.print("Enter name: ");
//        return scanner.nextLine().trim();
//    }
//
//    private static String promptForIsbn(Scanner scanner) {
//        System.out.print("Enter book ISBN: ");
//        return scanner.nextLine().trim();
//    }
//
//    private static String cleanIsbn(String isbn) {
//        return isbn.replace("-", "").trim();
//    }
//
//
//    private static Optional<Book> findBookByIsbn(Hub hub, String cleanIsbn) {
//        return hub.getBooksList().stream()
//                .filter(b -> b.getIsbn().replace("-", "").trim().equalsIgnoreCase(cleanIsbn))
//                .findFirst();
//    }
//
//    private static boolean isBookAvailable(Book book) {
//        return book.getQuantity() > 0;
//    }
//
//    private static Student findOrCreateStudent(Hub hub, String name) {
//        Optional<Student> studentOpt = hub.getStudentsList().stream()
//                .filter(s -> s.getName().equalsIgnoreCase(name))
//                .findFirst();
//
//        if (studentOpt.isEmpty()) {
//            List<Student> allStudentsFromList = hub.studentsList;
//            studentOpt = allStudentsFromList.stream()
//                    .filter(s -> s.getName().equalsIgnoreCase(name))
//                    .findFirst();
//
//            studentOpt.ifPresent(student -> hub.getStudentsList().add(student));
//        }
//
//        if (studentOpt.isPresent()) {
//            Student student = studentOpt.get();
//            return student;
//        } else {
//            Student student = new Student(name);
//            hub.getStudentsList().add(student);
//            StudentStore.saveStudent(student);
//            System.out.println("✅ Created new student: " + student.getName() + " (USN: " + student.getUsn() + ")");
//            return student;
//        }
//    }
//
//    public static void issueBookToStudent(Scanner scanner, Hub hub) {
//        String name = promptForName(scanner);
//        String isbnInput = promptForIsbn(scanner);
//        String cleanIsbnInput = cleanIsbn(isbnInput);
//
//        Optional<Book> bookOpt = findBookByIsbn(hub, cleanIsbnInput);
//        if (bookOpt.isEmpty()) {
//            System.out.println("❌ Book with ISBN " + isbnInput + " not found.");
//            return;
//        }
//
//        Book book = bookOpt.get();
//        if (!isBookAvailable(book)) {
//            System.out.println("⚠ No copies available for this book.");
//            return;
//        }
//
//        Student student = findOrCreateStudent(hub, name);
//
//        Issue newIssue = createNewIssue(student, book);
//        hub.getIssuesList().add(newIssue);
//
//        try {
//            IssueStore.saveIssue(newIssue);
//        } catch (IOException e) {
//            System.out.println("❌ Error saving issue to loans.csv: " + e.getMessage());
//        }
//
//        updateBookQuantity(book, hub, cleanIsbnInput);
//    }
//
//    private static Issue createNewIssue(Student student, Book book) {
//        LocalDate issueDate = LocalDate.now();
//        LocalDate returnDate = issueDate.plusDays(7);
//        int newIssueId = IssueStore.getNextIssueId();
//
//        return new Issue(
//                newIssueId,
//                issueDate,
//                returnDate,
//                student,
//                book
//        );
//    }
//
//    private static void updateBookQuantity(Book book, Hub hub, String cleanIsbnInput) {
//        int updatedQuantity = book.getQuantity() - 1;
//        book.setQuantity(updatedQuantity);
//
//        Optional<Book> bookFromList = hub.booksList.stream()
//                .filter(b -> b.getIsbn().replace("-", "").trim().equalsIgnoreCase(cleanIsbnInput))
//                .findFirst();
//    }
//}
//

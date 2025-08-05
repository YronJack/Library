package org.YronJack.utils;

import org.YronJack.models.Hub;
import org.YronJack.models.Issue;
import org.YronJack.models.Student;
import org.YronJack.models.Book;
import org.YronJack.store.BookStore;
import org.YronJack.store.IssueStore;
import org.YronJack.store.StudentStore;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class IssueActions {

    private static final BookStore bookStore = new BookStore();
    private static final StudentStore studentStore = new StudentStore();

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

        try {
            IssueStore.saveIssue(newIssue);
        } catch (IOException e) {
            System.out.println("❌ Error saving issue to loans.csv: " + e.getMessage());
        }

        updateBookQuantity(book, hub, cleanIsbnInput);
    }


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
            List<Student> allStudentsFromCsv = studentStore.loadStudents();
            studentOpt = allStudentsFromCsv.stream()
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
            studentStore.saveStudent(student);
            System.out.println("✅ Created new student: " + student.getName() + " (USN: " + student.getUsn() + ")");
            return student;
        }
    }

    private static Issue createNewIssue(Student student, Book book) {
        Date issuedDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(issuedDate);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date returnDate = cal.getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int newIssueId = IssueStore.getNextIssueId();

        return new Issue(
                newIssueId,
                simpleDateFormat.format(issuedDate),
                simpleDateFormat.format(returnDate),
                student,
                book
        );
    }

    private static void updateBookQuantity(Book book, Hub hub, String cleanIsbnInput) {
        int updatedQuantity = book.getQuantity() - 1;
        book.setQuantity(updatedQuantity);

        Optional<Book> bookFromCsvOpt = bookStore.loadBooks().stream()
                .filter(b -> b.getIsbn().replace("-", "").trim().equalsIgnoreCase(cleanIsbnInput))
                .findFirst();

        if (bookFromCsvOpt.isPresent()) {
            bookStore.updateBookQuantity(bookFromCsvOpt.get().getIsbn(), updatedQuantity);
        } else {
            System.out.println("⚠ Could not find book in CSV to update quantity.");
        }
    }
}


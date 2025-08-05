package org.YronJack.store;

import org.YronJack.models.Book;
import org.YronJack.models.Hub;
import org.YronJack.models.Issue;
import org.YronJack.models.Student;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class IssueStore {

    private static final String ISSUES_CSV = "data/issues.csv";
    private static int lastIssueId = 0;

    private static String normalizeIsbn(String isbn) {
        return isbn == null ? "" : isbn.replace("-", "").trim();
    }

    static {
        try {
            Path path = Paths.get(ISSUES_CSV);
            if (Files.exists(path)) {
                List<String> lines = Files.readAllLines(path);
                if (lines.size() > 1) {
                    int maxId = 0;
                    for (int i = 1; i < lines.size(); i++) {
                        String[] parts = lines.get(i).split(",");
                        try {
                            int id = Integer.parseInt(parts[0].trim());
                            if (id > maxId) maxId = id;
                        } catch (NumberFormatException ignored) {}
                    }
                    lastIssueId = maxId;
                }
            } else {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(ISSUES_CSV))) {
                    bw.write("issueId,usn,isbn,issueDate,returnDate");
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error initializing issues.csv: " + e.getMessage());
        }
    }

    public static void saveIssue(Issue issue) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ISSUES_CSV, true))) {
            String line = String.format("%d,%s,%s,%s,%s",
                    issue.getIssueId(),
                    issue.getIssueStudent().getUsn(),
                    issue.getIssueBook().getIsbn(),
                    issue.getIssueDate(),
                    issue.getReturnDate());
            bw.write(line);
            bw.newLine();
        }

        System.out.printf("✅ Issue saved: ID %d | Student %s | Book %s%n",
                issue.getIssueId(),
                issue.getIssueStudent().getUsn(),
                issue.getIssueBook().getIsbn());
    }

    public static int getNextIssueId() {
        return ++lastIssueId;
    }

    public static List<Issue> loadIssues(Hub hub) {
        List<Issue> issues = new ArrayList<>();
        File file = new File(ISSUES_CSV);
        if (!file.exists()) return issues;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) continue;

                try {
                    int issueId = Integer.parseInt(parts[0].trim());
                    String usn = parts[1].trim();
                    String isbn = parts[2].trim();
                    LocalDate issueDate = LocalDate.parse(parts[3].trim());
                    LocalDate returnDate = LocalDate.parse(parts[4].trim());

                    Student student = hub.getStudentsList().stream()
                            .filter(s -> s.getUsn().equals(usn))
                            .findFirst()
                            .orElseGet(() -> {
                                Student newStudent = new Student(usn, "Unknown");
                                hub.getStudentsList().add(newStudent);
                                return newStudent;
                            });


                    Book book = hub.getBooksList().stream()
                            .filter(b -> normalizeIsbn(b.getIsbn()).equalsIgnoreCase(normalizeIsbn(isbn)))
                            .findFirst()
                            .orElseGet(() -> {
                                Book newBook = new Book(isbn, "Unknown", "Unknown", 0, "Unknown",false);
                                hub.getBooksList().add(newBook);
                                return newBook;
                            });

                    issues.add(new Issue(issueId, issueDate, returnDate, student, book));

                } catch (NumberFormatException e) {
                    System.out.println("Error parsing issue line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return issues;
    }
}
package org.YronJack.store;

import org.YronJack.models.Issue;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class IssueStore {

    private static final String LOANS_CSV = "data/loans.csv";
    private static int lastIssueId = 0;

    static {
        try {
            Path path = Paths.get(LOANS_CSV);
            if (Files.exists(path)) {
                List<String> lines = Files.readAllLines(path);
                if (lines.size() > 1) {
                    int maxId = 0;
                    for (int i = 1; i < lines.size(); i++) {
                        String line = lines.get(i);
                        if (line.trim().isEmpty()) continue;
                        String[] parts = line.split(",");
                        try {
                            int id = Integer.parseInt(parts[0].trim());
                            if (id > maxId) maxId = id;
                        } catch (NumberFormatException ignored) {}
                    }
                    lastIssueId = maxId;
                } else {
                    lastIssueId = 0;
                }
            } else {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOANS_CSV))) {
                    bw.write("issueId,usn,isbn,issueDate,returnDate");
                    bw.newLine();
                }
                lastIssueId = 0;
            }
        } catch (IOException e) {
            System.out.println("❌ Error initializing loans.csv: " + e.getMessage());
            lastIssueId = 0;
        }
    }

    public static void saveIssue(Issue issue) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOANS_CSV, true))) {
            String line = String.format("%d,%s,%s,%s,%s",
                    issue.getIssueId(),
                    issue.getIssueStudent().getUsn(),
                    issue.getIssueBook().getIsbn(),
                    issue.getIssueDate(),
                    issue.getReturnDate());
            bw.write(line);
            bw.newLine();
        }

        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("ℹ Using existing student: %s (USN: %s)%n",
                issue.getIssueStudent().getName(),
                issue.getIssueStudent().getUsn());
        System.out.println("     ✅ Loan (Issue) created and saved successfully!");
        System.out.printf("        Issue ID: %d | Student USN: %s | Book ISBN: %s | Issue Date: %s | Return Date: %s%n",
                issue.getIssueId(),
                issue.getIssueStudent().getUsn(),
                issue.getIssueBook().getIsbn(),
                issue.getIssueDate(),
                issue.getReturnDate());
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }


    public static int getNextIssueId() {
        lastIssueId++;
        return lastIssueId;
    }
}

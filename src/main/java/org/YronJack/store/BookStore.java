package org.YronJack.store;

import org.YronJack.models.Book;

import java.io.*;
import java.util.*;

public class BookStore {

    private static final String FILE_NAME = "data/books.csv";
    private static String fileName = FILE_NAME;  // variable mutable para el nombre de archivo

    public BookStore() {}

    // Método para cambiar el archivo usado, útil para tests
    public static void setFileNameForTests(String testFileName) {
        fileName = testFileName;
    }

    private static String normalizeIsbn(String isbn) {
        return isbn == null ? "" : isbn.replace("-", "").trim();
    }

    private static void ensureFileExists() {
        File file = new File(fileName);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.println("isbn,title,category,author,quantity");
            } catch (IOException e) {
                System.err.println("❌ Error creating initial file: " + e.getMessage());
            }
        }
    }

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        Set<String> seenIsbns = new HashSet<>();

        File file = new File(fileName);
        if (!file.exists()) return books;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) {
                    System.out.println("Invalid line: " + line);
                    continue;
                }
                try {
                    String isbn = parts[0].trim();
                    String isbnNorm = normalizeIsbn(isbn);

                    if (seenIsbns.contains(isbnNorm)) continue;
                    seenIsbns.add(isbnNorm);

                    String title = parts[1].trim();
                    String category = parts[2].trim();
                    String authorName = parts[3].trim();
                    int quantity = Integer.parseInt(parts[4].trim());

                    books.add(new Book(isbn, title, category, quantity, authorName,false));
                } catch (NumberFormatException e) {
                    System.out.println("Error converting quantity: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static boolean existsByISBN(String isbn) {
        String isbnNorm = normalizeIsbn(isbn);
        return loadBooks().stream()
                .anyMatch(book -> normalizeIsbn(book.getIsbn()).equalsIgnoreCase(isbnNorm));
    }

    public static void saveBook(Book book) {
        ensureFileExists();
        if (existsByISBN(book.getIsbn())) {
            System.out.println("⚠ Book with this ISBN already exists. Not saving.");
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(String.format("%s,%s,%s,%s,%d",
                    book.getIsbn(),
                    book.getTitle(),
                    book.getCategory(),
                    book.getAuthor().getName(),
                    book.getQuantity()
            ));
            bw.newLine();
        } catch (IOException e) {
            System.err.println("❌ Error saving book: " + e.getMessage());
        }
    }

    public static void deleteCSVFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nAre you sure you want to delete the CSV file? (YES/NO): ");
        String confirmation = scanner.nextLine().trim();

        if (!confirmation.equalsIgnoreCase("YES")) {
            System.out.println("❌ Deletion canceled.");
            return;
        }

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("⚠ CSV file does not exist.");
            return;
        }

        if (file.delete()) {
            System.out.println("✅ CSV file deleted successfully.");
        } else {
            System.out.println("❌ Failed to delete the CSV file.");
        }
    }
}
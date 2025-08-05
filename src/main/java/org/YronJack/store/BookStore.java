package org.YronJack.store;

import org.YronJack.models.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookStore {

    private static final String FILE_NAME = "data/books.csv"; // data/ folder at the root of the project

    public BookStore() {
    }

    // ✅ If the file does not exist, create it with the CSV header
    private static void ensureFileExists() {
        File file = new File(FILE_NAME);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs(); // Create data/ folder if it doesn't exist
        }

        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.println("isbn,title,category,author, quantity"); // CSV header
            } catch (IOException e) {
                System.err.println("❌ Error creating initial file: " + e.getMessage());
            }
        }
    }

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return books;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // Read and skip the header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length < 5) {
                    System.out.println("Invalid line, less than 5 fields: " + line);
                    continue;
                }
                try {
                    String isbn = parts[0].trim();
                    String title = parts[1].trim();
                    String category = parts[2].trim();
                    String authorName = parts[3].trim();
                    int quantity = Integer.parseInt(parts[4].trim());

                    Book book = new Book(isbn, title, category, quantity, authorName);
                    books.add(book);
                } catch (NumberFormatException e) {
                    System.out.println("Error converting quantity to number on line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    // ✅ Check if the ISBN already exists in the file
    public boolean existsByISBN(String isbn) {
        return loadBooks().stream().anyMatch(book -> book.getIsbn().equals(isbn));
    }

    // ✅ Save a new book to the file
    public static void saveBook(Book book) {
        ensureFileExists();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
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

    // ✅ Delete the CSV file with confirmation
    public static void deleteCSVFile() {
        final String INDENT = "    ";
        final int BOX_WIDTH = 72;

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n╔" + "═".repeat(BOX_WIDTH) + "╗");
        System.out.print(INDENT + "Are you sure you want to delete the CSV file?\n");
        System.out.print(INDENT + "This will permanently erase all saved data. (YES/NO): ");

        String confirmation = scanner.nextLine().trim();

        if (!confirmation.equalsIgnoreCase("YES")) {
            System.out.println("╠" + "─".repeat(BOX_WIDTH) + "╣");
            System.out.println(INDENT + "❌ Deletion canceled. CSV file was not deleted.");
            System.out.println("╚" + "═".repeat(BOX_WIDTH) + "╝");
            return;
        }

        File file = new File(FILE_NAME);
        System.out.println("╠" + "─".repeat(BOX_WIDTH) + "╣");
        System.out.println(INDENT + "Attempting to delete file at:");
        System.out.println(INDENT + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("╠" + "─".repeat(BOX_WIDTH) + "╣");
            System.out.println(INDENT + "⚠ CSV file does not exist. Nothing to delete.");
            System.out.println("╚" + "═".repeat(BOX_WIDTH) + "╝");
            return;
        }

        if (file.delete()) {
            System.out.println("╠" + "─".repeat(BOX_WIDTH) + "╣");
            System.out.println(INDENT + "✅ CSV file deleted successfully.");
        } else {
            System.out.println("╠" + "─".repeat(BOX_WIDTH) + "╣");
            System.out.println(INDENT + "❌ Failed to delete the CSV file.");
        }

        System.out.println("╚" + "═".repeat(BOX_WIDTH) + "╝");
    }
}

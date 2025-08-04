package org.YronJack.utils;

import org.YronJack.models.Book;
import org.YronJack.models.Category;
import org.YronJack.models.Hub;
import org.YronJack.store.BookStore;

import java.util.Arrays;
import java.util.Scanner;

public class CreateBookAction {

    private static void validateISBN13(String isbn) {
        String cleanISBN = isbn.replaceAll("-", "").trim();

        if (cleanISBN.length() != 13 || !cleanISBN.matches("\\d+")) {
            throw new IllegalArgumentException("❌ ISBN must have exactly 13 digits.");
        }

        if (!cleanISBN.startsWith("978") && !cleanISBN.startsWith("979")) {
            throw new IllegalArgumentException("❌ ISBN-13 must start with 978 or 979.");
        }

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = cleanISBN.charAt(i) - '0';
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int checkDigit = (10 - (sum % 10)) % 10;

        if (checkDigit != cleanISBN.charAt(12) - '0') {
            throw new IllegalArgumentException("❌ ISBN-13 invalid.");
        }
    }

    public static void createBook(Scanner scanner) {
        BookStore store = new BookStore();

        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("           📚 CREATE NEW BOOK ENTRY");
        System.out.println("╚════════════════════════════════════════════════════╝");

        String isbn;
        while (true) {
            try {
                System.out.print("ISBN (13 dígits): ");
                isbn = scanner.nextLine();
                validateISBN13(isbn);

                if (store.existsByISBN(isbn)) {
                    System.out.println("❌ This ISBN is already registered in the database. Please enter a different ISBN.");
                    continue;
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.print("Title: ");
        String title = scanner.nextLine();

        Category category = null;
        while (true) {
            try {
                System.out.print("Category (" + Arrays.toString(Category.values()) + "): ");
                String categoryInput = scanner.nextLine().toUpperCase();
                category = Category.valueOf(categoryInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid category, try again.");
            }
        }

        String name;
        while (true) {
            System.out.print("Author name: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                break;
            }
            System.out.println("❌ Author name is required. Please enter a valid name.");
        }

        int quantity = 0;
        while (true) {
            try {
                System.out.print("Quantity: ");
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid quantity, positive integer needed.");
            }
        }

        // ✅ Create and save the book
        Book booklet = new Book(isbn, title, category.name(), quantity, name);
        store.saveBook(booklet);

        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("     ✅ Book created and saved successfully!"); // ← 5 espacios delante
        System.out.println("        " + booklet.getInfo()); // ← más espacios delante
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

//    public static void createBook(Scanner scanner) {
//        BookStore store = new BookStore();
//
//        String isbn;
//        while (true) {
//            try {
//                System.out.print("ISBN (13 dígits): ");
//                isbn = scanner.nextLine();
//                validateISBN13(isbn);
//
//                if (store.existsByISBN(isbn)) {
//                    System.out.println("❌ This ISBN is already registered in the database. Please enter a different ISBN.");
//                    continue;
//                }
//                break;
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//
//        System.out.print("Title: ");
//        String title = scanner.nextLine();
//
//        Category category = null;
//        while (true) {
//            try {
//                System.out.print("Category (" + Arrays.toString(Category.values()) + "): ");
//                String categoryInput = scanner.nextLine().toUpperCase();
//                category = Category.valueOf(categoryInput);
//                break;
//            } catch (IllegalArgumentException e) {
//                System.out.println("❌ Invalid category, try again.");
//            }
//        }
//
//        System.out.print("Author name: ");
//        String name = scanner.nextLine();
//
//        int quantity = 0;
//        while (true) {
//            try {
//                System.out.print("Quantity: ");
//                quantity = Integer.parseInt(scanner.nextLine());
//                if (quantity < 0) throw new NumberFormatException();
//                break;
//            } catch (NumberFormatException e) {
//                System.out.println("❌ Invalid quantity, positive integer needed.");
//            }
//        }
//
//        // ✅ Create the book and automatically add it to the file
//        Book booklet = new Book(isbn, title, category.name(), quantity, name);
//        store.saveBook(booklet);
//
//        System.out.println("\n✅ Book created and saved: " + booklet.getInfo() + "\n");
//    }
}

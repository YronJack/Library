package org.YronJack.utils;

import org.YronJack.models.Book;
import org.YronJack.models.Category;

import java.util.Arrays;
import java.util.Scanner;

public class CreateBookAction {

    private static void validateISBN13(String isbn) {
        String cleanISBN = isbn.replaceAll("-", "").trim();

        if (cleanISBN.length() != 13 || !cleanISBN.matches("\\d+")) {
            throw new IllegalArgumentException("❌ ISBN must have exactly 13 dígits.");
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
        String isbn;
        while (true) {
            try {
                System.out.print("ISBN (13 dígits): ");
                isbn = scanner.nextLine();
                validateISBN13(isbn);
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

        Book booklet = new Book(isbn, title, category.name(), quantity);
        System.out.println("\n✅ Book created: " + booklet.getInfo(booklet) + "\n");
    }

}

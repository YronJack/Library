package org.YronJack.menus;

import org.YronJack.store.BookStore;
import org.YronJack.models.Hub;

import java.util.Scanner;

public class StoreMenu {

    public static void storeMenu(Scanner scanner, Hub patata, BookStore store) {
        int option = -1;

        do {
            System.out.println("\n ┌──────────────────────────────────────────────────┐");
            System.out.println(" │                   Store Menu                     │");
            System.out.println(" ├──────────────────────────────────────────────────┤");
            System.out.println(" │ 1. Load Books                                    │");
            System.out.println(" │ 2. Delete CSV File                               │");
            System.out.println(" │ 3. Save Books to CSV                             │");
            System.out.println(" │ 4. Back to Main Menu                             │");
            System.out.println(" └──────────────────────────────────────────────────┘");

            boolean validInput = false;

            while (!validInput) {
                System.out.print("Select an option: ");
                try {
                    option = Integer.parseInt(scanner.nextLine());
                    if (option >= 1 && option <= 4) {
                        validInput = true;
                    } else {
                        System.out.println("❗ Invalid option. Please enter a number between 1 and 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❗ Invalid input. Please enter a number.");
                }
            }

            switch (option) {
                case 1 -> store.loadBooks();
                case 2 -> store.deleteCSVFile();
                case 3 -> storeBook(scanner);
                case 4 -> System.out.println("👋 Returning to Main Menu...");
            }
        } while (option != 4);
    }

    public static void storeBook(Scanner scanner) {

        System.out.println("💾 Saving books to CSV... (feature pending)");
    }
}
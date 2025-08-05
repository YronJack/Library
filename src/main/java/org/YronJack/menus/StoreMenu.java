package org.YronJack.menus;

import org.YronJack.models.BookStore;
import org.YronJack.models.Hub;


import java.util.*;

public class StoreMenu {

    public static void storeMenu(Scanner scanner, Hub patata, BookStore store) {
        System.out.println("          IronLibrary ");

        int option = -1;

        do {
            System.out.println("\n         Store Menu     ");
            System.out.println("1. Load Books");
            System.out.println("2. delete CSV File");
            System.out.println("3. a lo mejor hace falta");
            System.out.println("4. Back to Main Menu");

            boolean validInput = false;

            while (!validInput) {
                System.out.print("Select an option: ");
                try {
                    option = Integer.parseInt(scanner.nextLine());
                    if (option >= 1 && option <= 5) {
                        validInput = true;
                    } else {
                        System.out.println("❗ Option invalid. Insert a valid option,try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❗ Insert a valid number.");
                }
            }

            switch (option) {
                case 1 -> {
                    BookStore.loadBooks();
                    //patata.booksList = BookStore.loadBooks(); //?????????
                    break;
                }

                case 2 -> {
                    BookStore.deleteCSVFile();;
                    break;
                }
                case 3 -> {
                    storeBook(scanner);
                    break;
                }
                case 4 -> {
                    System.out.println("👋 back to Main Menu");
                    return;
                }
            }
        } while (option != 5);
    }

    public static void storeBook(Scanner scanner) {

    }
}


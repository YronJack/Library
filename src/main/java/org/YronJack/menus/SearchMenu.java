package org.YronJack.menus;

import org.YronJack.models.Hub;
import org.YronJack.utils.SearchActions;

import java.util.Scanner;

public class SearchMenu {

    public static void searchMenu(Scanner scanner, Hub patata) {
        int option = -1;

        do {
            System.out.println("\n ┌──────────────────────────────────────────────────┐");
            System.out.println(" │                   Search Menu                    │");
            System.out.println(" ├──────────────────────────────────────────────────┤");
            System.out.println(" │ 1. Search Book by title                          │");
            System.out.println(" │ 2. Search Book by author                         │");
            System.out.println(" │ 3. Search Book by category                       │");
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
                case 1 -> {
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    SearchActions.searchByTitle(title, patata);
                }
                case 2 -> {
                    System.out.print("Enter author name: ");
                    String authorName = scanner.nextLine();
                    SearchActions.searchByAuthor(authorName, patata);
                }
                case 3 -> {
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    SearchActions.searchByCategory(category, patata);
                }
                case 4 -> System.out.println("👋 Returning to Main Menu...");
            }
        } while (option != 4);
    }
}
package org.YronJack.menus;

import org.YronJack.models.Hub;
import org.YronJack.utils.SearchActions;

import java.util.Scanner;


public class SearchMenu {

    public static void searchMenu(Scanner scanner,Hub patata) {
        System.out.println("          IronLibrary ");

        int option = -1;

        do {
            System.out.println("\n         Search Menu     ");
            System.out.println("1. Search Book by title");
            System.out.println("2. Search Book by author");
            System.out.println("3. Search Book by category");
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
                    System.out.println("Enter title: ");
                    String title = scanner.nextLine();
                    SearchActions.searchByTitle(title, patata);
                    break;
                }

                case 2 -> {
                    System.out.println("Enter author name: ");
                    String author = scanner.nextLine();
                    SearchActions.searchByAuthor(author, patata);
                    break;
                }
                case 3 -> {
                    System.out.println("Enter category: ");
                    String category = scanner.nextLine();
                    SearchActions.searchByCategory(category, patata);
                    break;
                }
                case 4 -> {
                    System.out.println("👋 back to Main Menu");
                    return;
                }
            }
        } while (option != 5);
    }
}

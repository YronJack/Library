package org.YronJack.menus;

import org.YronJack.models.Hub;

import java.util.Scanner;

import static org.YronJack.utils.CreateBookAction.createBook;

public class MainMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void launch(Hub patata) {
        System.out.println("          IronLibrary ");

        int option = -1;

        do {
            System.out.println("\n         Main Menu     ");
            System.out.println("1. Create (add a book!)");
            System.out.println("2. Search (by title, by author, by category...)");
            System.out.println("3. Listings");
            System.out.println("4. Issues");
            System.out.println("5. Exit");

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
                case 1 -> createBook(scanner);
                case 2 -> SearchMenu.searchMenu(scanner, patata);
                case 3 -> ListMenu.listMenu(scanner, patata);
                case 4 -> IssueMenu.issueMenu(scanner, patata);
                case 5 -> System.out.println("👋 Bye,see you later gator...");
            }

        } while (option != 5);
    }
}

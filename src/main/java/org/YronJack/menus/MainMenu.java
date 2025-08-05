package org.YronJack.menus;

import org.YronJack.models.Hub;
import org.YronJack.store.BookStore;
import org.YronJack.utils.*;

import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void launch(Hub patata, BookStore bookStore) {
        System.out.println("\n\n ┌──────────────────────────────────────────────────┐");
        System.out.println(" │                  IronLibrary                     │");
        System.out.println(" └──────────────────────────────────────────────────┘");

        int option = -1;

        do {
            System.out.println("\n ┌──────────────────────────────────────────────────┐");
            System.out.println(" │                   Main Menu                      │");
            System.out.println(" ├──────────────────────────────────────────────────┤");
            System.out.println(" │ 1. Create (add a book!)                          │");
            System.out.println(" │ 2. Search (by title, author, category...)        │");
            System.out.println(" │ 3. Listings                                      │");
            System.out.println(" │ 4. Issues                                        │");
            System.out.println(" │ 5. Import/Export Library                         │");
            System.out.println(" │ 6. Exit                                          │");
            System.out.println(" └──────────────────────────────────────────────────┘");

            boolean validInput = false;

            while (!validInput) {
                System.out.print("Select an option: ");
                try {
                    option = Integer.parseInt(scanner.nextLine());
                    if (option >= 1 && option <= 6) {
                        validInput = true;
                    } else {
                        System.out.println("❗ Invalid option. Please enter a number between 1 and 6.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❗ Invalid input. Please enter a number.");
                }
            }

            switch (option) {
                case 1 -> CreateBookAction.createBook(scanner);
                case 2 -> SearchMenu.searchMenu(scanner, patata);
                case 3 -> ListMenu.listMenu(scanner, patata);
                case 4 -> IssueMenu.issueMenu(scanner, patata);
                case 5 -> StoreMenu.storeMenu(scanner, patata, bookStore);
                case 6 -> System.out.println("👋 Bye, see you later gator...");
            }

        } while (option != 6);
    }
}
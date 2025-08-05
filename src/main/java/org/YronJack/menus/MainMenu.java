package org.YronJack.menus;

import org.YronJack.models.Hub;
import org.YronJack.models.BookStore;
import org.YronJack.utils.*;
import java.util.Scanner;


public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BookStore store = new BookStore();


    public static void launch(Hub patata) {
        System.out.println("          IronLibrary ");

        int option = -1;

        do {
            System.out.println("\n         Main Menu     ");
            System.out.println("1. Create (add a book!)");
            System.out.println("2. Search (by title, by author, by category...)");
            System.out.println("3. Listings");
            System.out.println("4. Issues");
            System.out.println("5. Import/Export Library");
            System.out.println("6. Exit");

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
                case 1 -> CreateBookAction.createBook(scanner);
                case 2 -> SearchMenu.searchMenu(scanner, patata);
                case 3 -> ListMenu.listMenu(scanner, patata);
                case 4 -> IssueMenu.issueMenu(scanner, patata);
                case 5 -> StoreMenu.storeMenu(scanner,patata,store);
                case 6 -> System.out.println("👋 Bye,see you later gator...");
            }

        } while (option != 6);
    }
}

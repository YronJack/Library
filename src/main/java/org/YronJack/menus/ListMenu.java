package org.YronJack.menus;

import org.YronJack.models.Hub;
import org.YronJack.utils.ListingActions;

import java.util.Scanner;

public class ListMenu {
    public static void listMenu(Scanner scanner, Hub patata) {
        int option = -1;

        do {
            System.out.println("\n\n ┌──────────────────────────────────────────────────┐");
            System.out.println(" │                 Listings Menu                    │");
            System.out.println(" ├──────────────────────────────────────────────────┤");
            System.out.println(" │ 1. List all books                                │");
            System.out.println(" │ 2. List books by student                         │");
            System.out.println(" │ 3. List all issued books                         │");
            System.out.println(" │ 4. List all books by a specific category         │");
            System.out.println(" │ 5. List books that have never been issued        │");
            System.out.println(" │ 6. List all students                             │");
            System.out.println(" │ 7. Back to Main Menu                             │");
            System.out.println(" └──────────────────────────────────────────────────┘");

            boolean validInput = false;

            while (!validInput) {
                System.out.print("Select an option: ");
                try {
                    option = Integer.parseInt(scanner.nextLine());
                    if (option >= 1 && option <= 7) {
                        validInput = true;
                    } else {
                        System.out.println("❗ Invalid option. Please enter a number between 1 and 7.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❗ Invalid input. Please enter a number.");
                }
            }

            switch (option) {
                case 1:
                    ListingActions.listAllBooks(patata);
                    break;
                case 2:
                    ListingActions.listBooksByUsn(scanner, patata);
                    break;
                case 3:
                    ListingActions.listAllIssuedBooks(patata);
                    break;
                case 4:
                    ListingActions.listBooksByCategory(scanner, patata);
                    break;
                case 5:
                    ListingActions.listBooksNeverIssued(patata);
                    break;
                case 6:
                    ListingActions.listAllStudents(patata);
                    break;
                case 7:
                    System.out.println("👋 Returning to Main Menu.");
                    break;
            }
        } while (option != 7);
    }
}

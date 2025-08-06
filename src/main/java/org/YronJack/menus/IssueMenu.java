package org.YronJack.menus;

import org.YronJack.models.Hub;
import org.YronJack.utils.IssueActions;

import java.util.Scanner;

public class IssueMenu {
    public static void issueMenu(Scanner scanner, Hub patata) {
        int option = -1;

        do {
            System.out.println("\n ┌──────────────────────────────────────────────────┐");
            System.out.println(" │                   Issue Menu                     │");
            System.out.println(" ├──────────────────────────────────────────────────┤");
            System.out.println(" │ 1. Issue book to student                         │");
            System.out.println(" │ 2. Back to Main Menu                             │");
            System.out.println(" └──────────────────────────────────────────────────┘");

            boolean validInput = false;

            while (!validInput) {
                System.out.print("Select an option: ");
                try {
                    option = Integer.parseInt(scanner.nextLine());
                    if (option >= 1 && option <= 2) {
                        validInput = true;
                    } else {
                        System.out.println("❗ Invalid option. Please enter 1 or 2.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❗ Invalid input. Please enter a number.");
                }
            }

            switch (option) {
                case 1 -> IssueActions.issueBookToStudent(scanner, patata);
                case 2 -> System.out.println("👋 Returning to Main Menu...");
            }

        } while (option != 2);
    }
}
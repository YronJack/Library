package org.YronJack.menus;

import org.YronJack.models.Hub;
import org.YronJack.utils.IssueActions;

import java.util.Scanner;

public class IssueMenu {
    public static void issueMenu(Scanner scanner, Hub patata) {
        int option = -1;

        do {
            System.out.println("\n========= Issue Menu =========");
            System.out.println("1. Issue book to student");
            System.out.println("2. Back to Main Menu");

            System.out.print("Enter your choice: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❗ Invalid option, please enter a number.");
                continue;
            }

            switch (option) {
                case 1 -> IssueActions.issueBookToStudent(scanner, patata);
                case 2 -> System.out.println("👋 Returning to Main Menu...");
                default -> System.out.println("❗ Invalid option, try again.");
            }
        } while (option != 2);
    }
}

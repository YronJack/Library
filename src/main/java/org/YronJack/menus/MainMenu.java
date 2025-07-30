package org.YronJack.menus;

import org.YronJack.models.Book;
import org.YronJack.models.Hub;

import java.util.Scanner;

public class MainMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void launch(Hub patata){
        System.out.println("          IronLibrary ");

        int option;

        while (true) {
            System.out.println("\n         Main Menu     ");
            System.out.println("1. Create (add a book!)");
            System.out.println("2. Search (by title, by author, by category...)");
            System.out.println("3. Listings");
            System.out.println("4. Issues");
            System.out.println("5. Exit");

            System.out.print("Select an option: ");
            try {
                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        createBook();
                        break;
                    case 2:
                        SearchMenu.searchMenu(scanner, patata);
                        break;
                    case 3:
                        ListMenu.listMenu(scanner, patata);
                        return;
                    case 4:
                        IssueMenu.issueMenu(scanner, patata);
                        return;
                    default:
                        System.out.println("❗ Opción no válida. Intenta de nuevo.");
                }
            } catch (NumberFormatException e){
                System.out.println("❗ Debes introducir un número válido.");
            } catch (Exception e) {
                System.out.println("❗ Ocurrió un error inesperado: " + e.getMessage());
            }
        }
    }

    public static void createBook() {

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Category: "); //que sea un ENUM mejor
        String category = scanner.nextLine();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();

        Book booklet = new Book(isbn, title, category, quantity);
    }
}

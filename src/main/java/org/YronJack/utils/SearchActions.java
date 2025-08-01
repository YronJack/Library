package org.YronJack.utils;

import org.YronJack.models.Book;
import org.YronJack.models.Hub;

import java.util.Scanner;


public class SearchActions {



    public static void searchByAuthor(Scanner scanner, Hub patata) {
        // find the list of books

        // filter books by author
        // return the filtered list of books
    }

    public static void searchByCategory(Scanner scanner, Hub patata) {
    }



    public static  Book searchByTitle(String title, Hub patata) {

        for (Book bookOnList : patata.booksList) {
            if (bookOnList.getTitle().equals(title)) {
                System.out.println(bookOnList.getInfo());
                return bookOnList;
            }
        }
        return null;
    }
}

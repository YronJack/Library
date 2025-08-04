package org.YronJack;

import org.YronJack.menus.MainMenu;
import org.YronJack.models.Hub;
import org.YronJack.store.BookStore;

public class Main {
    public static void main(String[] args) {

        Hub patata = new Hub();

        // Create BookStore instance
        BookStore bookStore = new BookStore();

        // Load books from CSV into Hub.booksList
        patata.booksList = bookStore.loadBooks();

        MainMenu mainMenu = new MainMenu();

        mainMenu.launch(patata);
    }
}

/*
2
9783161484100
9781861972712
9780131101630
9791234567896
*/
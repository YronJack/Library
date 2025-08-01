package org.YronJack;

import org.YronJack.menus.MainMenu;
import org.YronJack.models.Hub;

public class Main {
    public static void main(String[] args) {

        Hub patata = new Hub();
        MainMenu mainMenu = new MainMenu();
        mainMenu.launch(patata);

    }
}
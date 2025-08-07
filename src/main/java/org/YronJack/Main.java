package org.YronJack;

import org.YronJack.menus.MainMenu;
import org.YronJack.models.Hub;
import org.YronJack.store.BookStore;
import org.YronJack.store.IssueStore;
import org.YronJack.store.StudentStore;

public class Main {
    public static void main(String[] args) {

        Hub patata = new Hub();

        BookStore bookStore = new BookStore();
        StudentStore studentStore = new StudentStore();
        IssueStore issueStore = new IssueStore();

        patata.booksList = bookStore.loadBooks();
        patata.studentsList = studentStore.loadStudents();
        patata.issuesList = issueStore.loadIssues(patata);

        MainMenu mainMenu = new MainMenu();

        mainMenu.launch(patata,bookStore);
    }
}

/*
ISBN - VALID NUMBERS

9783161484100
9781861972712
9780131101630
9791234567896
9786001191251
9786017151133
9786001191251
9786017151133
9786028328227
9786035000451
9786068126357
9786074550351
9786082030234
9786124516597
9786131574375
9786144040188
9786155014994
9786169039334
9786175811160
9786180207897
9786199056844
9786200004574
9786047968323
9786052296267
9786074257892
9786139890373

*/
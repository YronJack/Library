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

*/
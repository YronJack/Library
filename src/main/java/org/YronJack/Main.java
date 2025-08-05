package org.YronJack;

import org.YronJack.menus.MainMenu;
import org.YronJack.models.Hub;
import org.YronJack.store.BookStore;
import org.YronJack.store.IssueStore;
import org.YronJack.store.StudentStore;

public class Main {
    public static void main(String[] args) {

        Hub patata = new Hub();

        // Create BookStore instance
        BookStore bookStore = new BookStore();
        StudentStore studentStore = new StudentStore();
        IssueStore issueStore = new IssueStore();

        // Load books from CSV into Hub.booksList
        patata.booksList = bookStore.loadBooks();
        patata.studentsList = studentStore.loadStudents();
        patata.issuesList = issueStore.loadIssues(patata);

        MainMenu mainMenu = new MainMenu();

        mainMenu.launch(patata,bookStore);
    }
}

/*
2
9783161484100
9781861972712
9780131101630
9791234567896

creo que por aqui tb se puede hablar

tienes el micro cerraºdo



*/
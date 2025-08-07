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
978-600-119-125-1

978-601-7151-13-3

978-602-8328-22-7

978-603-500-045-1

978-606-8126-35-7

978-607-455-035-1

9786082030234

978-612-45165-9-7

978-613-1-57437-5

978-614-404-018-8

978-615-5014-99-4

978-616-90393-3-4

978-617-581-116-0

978-618-02-0789-7

978-619-90568-4-4

978-620-0004574

978-604-79-6832-3

978-605-2296-26-7

978-607-425-789-2

978-613-9-89037-3

*/
package org.YronJack.models;

import java.util.*;

public class Hub {
       private List<Book> booksList;
       private List<Student> studentsList;
       private List<Issue> issuesList;
       private List<Author> authorsList;

       public Hub(List<Book> booksList, List<Student> studentsList, List<Issue> issuesList, List<Author> authorsList) {
              this.booksList = booksList;
              this.studentsList = studentsList;
              this.issuesList = issuesList;
              this.authorsList = authorsList;
       }
       public Hub() {}
}

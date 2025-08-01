package org.YronJack.models;

import java.util.*;

public class Hub {
       public static List<Book> booksList = new ArrayList<>();
       public List<Student> studentsList = new ArrayList<>();
       public List<Issue> issuesList = new ArrayList<>();
       public List<Author> authorsList = new ArrayList<>();

       public Hub(List<Book> booksList, List<Student> studentsList, List<Issue> issuesList, List<Author> authorsList) {
              this.booksList = new ArrayList<>(booksList);
              this.studentsList = new ArrayList<>(studentsList);
              this.issuesList = new ArrayList<>(issuesList);
              this.authorsList = new ArrayList<>(authorsList);
       }

       public Hub() {}
}

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

       public static List<Book> getBooksList() { return booksList; }
       public List<Student> getStudentsList() { return studentsList; }
       public List<Issue> getIssuesList() { return issuesList; }
       public List<Author> getAuthorsList() { return authorsList; }

       public static void setBooksList(List<Book> booksList) { Hub.booksList = booksList; }
       public void setStudentsList(List<Student> studentsList) { this.studentsList = studentsList; }
       public void setIssuesList(List<Issue> issuesList) { this.issuesList = issuesList; }
       public void setAuthorsList(List<Author> authorsList) { this.authorsList = authorsList; }
}

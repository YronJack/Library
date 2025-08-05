package org.YronJack.models;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private Integer authorId;
    private String name;
    private String email;
    private List<Book> authorBook = new ArrayList<>();
    private static int counter = 0;

    public Author(String name){
        this.name = name;
        this.authorId = counter;
                counter++;
    }

    public Author(String name, String email) {
        this.name = name;
        this.email = email;
        this.authorId = counter;
        counter++;
    }

    // GETTERS AND SETTERS

    public Integer getAuthorId() { return authorId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Book> getAuthorBook() { return authorBook; }

    public void setAuthorId(Integer authorId) { this.authorId = authorId; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setAuthorBook(List<Book> authorBook) { this.authorBook = authorBook; }
    public void addBookToList(Book book) {
        authorBook.add(book);
    }
}

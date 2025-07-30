package org.YronJack.models;

public class Author {
    private Integer authorId;
    private String name;
    private String email;
    private Book authorBook;
    int counter = 0;

    public Author(String name, String email, Book authorBook) {
        this.name = name;
        this.email = email;
        this.authorBook = authorBook;
        this.authorId = counter;
        counter++;
    }

    // GETTERS AND SETTERS

    public Integer getAuthorId() { return authorId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Book getAuthorBook() { return authorBook; }

    public void setAuthorId(Integer authorId) { this.authorId = authorId; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setAuthorBook(Book authorBook) { this.authorBook = authorBook; }
}

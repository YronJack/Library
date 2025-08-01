package org.YronJack.models;

import org.YronJack.models.Author;

public class Book {
    private String isbn;
    private String title;
    private String category;
    private int quantity;
    private Author author;

    // Constructor

    public Book(){};

    public Book(String isbn, String title, String category, int quantity, String name ) {
        this.isbn = isbn;
        this.title = title;
        this.category = category;
        this.quantity = quantity;
        this.author = new Author(name);
        this.author.addBookToList(this);
        Hub.booksList.add(this);
    }

    // Getters & Setters

    public String getIsbn() { return this.isbn; }
    public String getTitle() { return this.title; }
    public String getCategory() { return this.category; }
    public int getQuantity() { return this.quantity; }
    public Author getAuthor() { return author; }

    public void setIsbn(String isbn){ this.isbn = isbn; }
    public void setTitle(String title){ this.title = title; }
    public void setCategory(String category){ this.category = category; }
    public void setQuantity(int quantity){ this.quantity = quantity; }
    public void setAuthor(Author author){ this.author = author; }

    // Methods

    public String getInfo() { return toString(); }

    @Override
    public String toString() {
        return "Book {" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", author name='" + (author != null ? author.getName() : "Unknown") + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
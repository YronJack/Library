package org.YronJack.models;

public class Book {
    private String isbn;
    private String title;
    private String category;
    private int quantity;


    public void Book (String isbn, String title, String category, int quantity ) {
        this.isbn = isbn;
        this.title = title;
        this.category = category;
        this.quantity = quantity;
    }

// Getters & Setters

    public String getIsbn() {
        return this.isbn; // Another comment
    }
    public String getTitle() { return this.title; }
    public String getCategory() { return this.category; }
    public int getQuantity() { return this.quantity; }

    public void setIsbn(String isbn){ this.isbn = isbn; }
    public void setTitle(String title){ this.title = title; }
    public void setCategory(String category){ this.category = category; }
    public void setQuantity(int quantity){ this.quantity = quantity; }

     public String getInfo(Book book){ return book.toString(); }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}


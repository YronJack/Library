package org.YronJack.utils;

import org.YronJack.models.Book;
import org.YronJack.models.Hub;

import java.util.Scanner;


public class SearchActions {



    public static Book searchByAuthor(String author, Hub patata) {
       for(Book bookOnList : patata.booksList){
           if(author.equals(bookOnList.getAuthor().getName())){
               System.out.println(bookOnList.getInfo());
               return bookOnList;
           }
       } return null;
    }

    public static Book searchByCategory(String category, Hub patata) {
        for (Book bookOnList : patata.booksList) {
            if (bookOnList.getCategory().equals(category)) {
                System.out.println(bookOnList.getInfo());
                return bookOnList;
            }
        }
        return null;
    }



    public static  Book searchByTitle(String title, Hub patata) {

        for (Book bookOnList : patata.booksList) {
            if (bookOnList.getTitle().equals(title)) {
                System.out.println(bookOnList.getInfo());
                return bookOnList;
            }
        }
        return null;
    }
}

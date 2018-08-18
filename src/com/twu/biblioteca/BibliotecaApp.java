package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Book book1 = new Book("Book 1", "Author 1", 2018);
        Book book2 = new Book("Book 2", "Author 2", 2012);
        Book book3 = new Book("Book 3", "Author 2", 2015);
        ArrayList<Book> bookList = new ArrayList<Book>(Arrays.asList(book1, book2, book3));
        Library library = new Library(bookList);

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Biblioteca library system!");
        System.out.println("Books currently available:");

        for (Book book : library.getBooks()) {
            System.out.println(book.getTitle() + ", " + book.getAuthor() + ", " + book.getYearPublished());
        }
    }
}

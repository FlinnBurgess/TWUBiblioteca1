package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;

public class BibliotecaApp {
    public static void main(String[] args) {
        Library library = createGenericLibrary();
        UserInterface ui = new UserInterface(library);

        System.out.println("Welcome to the Biblioteca library system!");
        ui.loginUser();

        System.out.println("To use the system, please type one of the following commands: ");
        ui.displayOptions();
        ui.takeInput();
    }

    private static Library createGenericLibrary() {
        Book book1 = new Book("Book 1", "Author 1", 2018);
        Book book2 = new Book("Book 2", "Author 2", 2012);
        Book book3 = new Book("Book 3", "Author 2", 2015);
        Movie movie1 = new Movie("Movie 1", 2010, "Director 1", 3);
        Movie movie2 = new Movie("Movie 2", 2002, "Director 2");

        ArrayList<Item> itemList = new ArrayList<>(Arrays.asList(book1, book2, book3, movie1, movie2));

        Library library = new Library(itemList);
        library.addCustomer(new Customer("name", "email@address.com", "01234 567 890", "123-4567", "password"));

        return library;
    }


}

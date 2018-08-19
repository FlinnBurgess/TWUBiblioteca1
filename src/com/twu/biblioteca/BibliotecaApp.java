package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Library library = createGenericLibrary();

        System.out.println("Welcome to the Biblioteca library system!");
        ArrayList<String> menuOptions = new ArrayList<>(Arrays.asList("List Books", "Checkout Book", "Return Book", "Quit"));

        System.out.println("To use the system, please type one of the following commands: ");
        for (String option : menuOptions) {
            System.out.println(option);
        }

        Scanner input = new Scanner(System.in);
        String command = input.nextLine().toLowerCase();

        while (!command.equals("quit")) {
            String title;
            String author;
            int yearPublished;

            switch (command) {
                case "list books":
                    System.out.println("Books currently available:");

                    for (Book book : library.getBooks()) {
                        System.out.println(book.getTitle() + ", " + book.getAuthor() + ", " + book.getYearPublished());
                    }

                    break;
                case "checkout book":
                    System.out.println("Please enter the title of the book:");
                    title = input.nextLine();
                    System.out.println("Please enter the author of the book:");
                    author = input.nextLine();
                    System.out.println("Please enter the year the book was published:");
                    yearPublished = Integer.parseInt(input.nextLine());

                    Book bookToCheckout = new Book(title, author, yearPublished);

                    try {
                        library.checkOutBook(bookToCheckout);
                        System.out.println("Thank you! Enjoy the book");
                    } catch (BookNotAvailableException exception) {
                        System.out.println("That book is not available.");
                    }

                    break;
                case "return book":
                    System.out.println("Please enter the title of the book:");
                    title = input.nextLine();
                    System.out.println("Please enter the author of the book:");
                    author = input.nextLine();
                    System.out.println("Please enter the year the book was published:");
                    yearPublished = Integer.parseInt(input.nextLine());

                    Book bookToReturn = new Book(title, author, yearPublished);

                    try {
                        library.returnBook(bookToReturn);
                        System.out.println("Thank you for returning the book.");
                    } catch (BookNotCheckedOutException exception) {
                        System.out.println("That is not a valid book to return.");
                    }

                    break;
                default:
                    System.out.println("Please enter a valid command!");
                    for (String option : menuOptions) {
                        System.out.println(option);
                    }
            }

            System.out.println("Please enter another command: ");
            command = input.nextLine().toLowerCase();
        }

    }

    private static Library createGenericLibrary() {
        Book book1 = new Book("Book 1", "Author 1", 2018);
        Book book2 = new Book("Book 2", "Author 2", 2012);
        Book book3 = new Book("Book 3", "Author 2", 2015);
        ArrayList<Book> bookList = new ArrayList<>(Arrays.asList(book1, book2, book3));

        return new Library(bookList);
    }
}

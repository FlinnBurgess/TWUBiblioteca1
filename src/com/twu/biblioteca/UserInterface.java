package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserInterface {
    private ArrayList<String> options = new ArrayList<>(Arrays.asList("List Books", "Checkout Book", "Return Book", "List Movies", "Checkout movie", "Return movie", "My details", "Quit"));
    private Library library;
    private String currentUser;
    private Scanner input = new Scanner(System.in);

    public UserInterface(Library library) {
        this.library = library;
    }

    public void displayOptions() {
        for (String option : options) {
            System.out.println(option);
        }
    }

    public void loginUser() {
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.println("Please enter your customer id:");
            currentUser = input.nextLine();

            System.out.println("Please enter your password:");
            String password = input.nextLine();

            loggedIn = library.userLogin(currentUser, password);
        }
    }

    public void takeInput() {
        String command = input.nextLine().toLowerCase();

        while (!command.equals("quit")) {
            parseCommand(command);
            System.out.println("Please enter another command: ");
            command = input.nextLine().toLowerCase();
        }
    }

    private void parseCommand(String command) {
        switch (command) {
            case "list books":
                System.out.println("Books currently available:");

                for (Book book : library.getBooks()) {
                    System.out.println(book.getTitle() + ", " + book.getAuthor() + ", " + book.getYearPublished());
                }

                break;
            case "checkout book":
                Book bookToCheckout = generateBookFromUserInput();

                try {
                    library.checkOutBook(bookToCheckout, currentUser);
                    System.out.println("Thank you! Enjoy the book");
                } catch (ItemNotAvailableException exception) {
                    System.out.println("That book is not available.");
                }

                break;
            case "return book":
                Book bookToReturn = generateBookFromUserInput();

                try {
                    library.returnBook(bookToReturn, currentUser);
                    System.out.println("Thank you for returning the book.");
                } catch (ItemNotCheckedOutException exception) {
                    System.out.println("That is not a valid book to return.");
                }

                break;
            case "list movies":
                for (Movie movie : library.getMovies()) {
                    String movieDetails = String.format("%s, %d, %s, ", movie.getName(), movie.getYear(), movie.getCreator());
                    if (movie.hasRating()) {
                        movieDetails +=  movie.getRating();
                    } else {
                        movieDetails += "unrated";
                    }

                    System.out.println(movieDetails);
                }

                break;
            case "checkout movie":
                Movie movieToCheckout = generateMovieFromUserInput();
                try {
                    library.checkOutMovie(movieToCheckout, currentUser);
                } catch (ItemNotAvailableException e) {
                    System.out.println("That movie is not available.");
                }

                break;
            case "return movie":
                Movie movieToReturn = generateMovieFromUserInput();
                try {
                    library.returnMovie(movieToReturn, currentUser);
                    System.out.println("Thank you for returning the movie.");
                } catch (ItemNotCheckedOutException e) {
                    System.out.println("That movie is not checked out.");
                }

                break;
            case "my details":
                System.out.println(library.getCustomer(currentUser).getDetails());
                break;
            default:
                System.out.println("Please enter a valid command!");
                displayOptions();
        }
    }

    private Book generateBookFromUserInput() {
        System.out.println("Please enter the title of the book:");
        String title = input.nextLine();
        System.out.println("Please enter the author of the book:");
        String author = input.nextLine();
        System.out.println("Please enter the year the book was published:");
        int yearPublished = Integer.parseInt(input.nextLine());

        return new Book(title, author, yearPublished);
    }

    private Movie generateMovieFromUserInput() {
        System.out.println("Please enter the name of the movie:");
        String name = input.nextLine();
        System.out.println("Please enter the year of release:");
        int year = Integer.parseInt(input.nextLine());
        System.out.println("Please enter the director of the movie:");
        String author = input.nextLine();

        return new Movie(name, year, author);
    }
}

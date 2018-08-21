package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Library library = createGenericLibrary();
        ArrayList<String> menuOptions = new ArrayList<>(Arrays.asList("List Books", "Checkout Book", "Return Book", "List Movies", "Checkout movie", "Quit"));
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Biblioteca library system!");

        System.out.println("To use the system, please type one of the following commands: ");
        for (String option : menuOptions) {
            System.out.println(option);
        }

        String command = input.nextLine().toLowerCase();

        while (!command.equals("quit")) {

            switch (command) {
                case "list books":
                    System.out.println("Books currently available:");

                    for (Book book : library.getBooks()) {
                        System.out.println(book.getTitle() + ", " + book.getAuthor() + ", " + book.getYearPublished());
                    }

                    break;
                case "checkout book":
                    Book bookToCheckout = generateBookFromUserInput(input);

                    try {
                        library.checkOutBook(bookToCheckout, "123-4567");
                        System.out.println("Thank you! Enjoy the book");
                    } catch (BookNotAvailableException exception) {
                        System.out.println("That book is not available.");
                    }

                    break;
                case "return book":
                    Book bookToReturn = generateBookFromUserInput(input);

                    try {
                        library.returnBook(bookToReturn, "123-4567");
                        System.out.println("Thank you for returning the book.");
                    } catch (BookNotCheckedOutException exception) {
                        System.out.println("That is not a valid book to return.");
                    }

                    break;
                case "list movies":
                    for (Movie movie : library.getMovies()) {
                        String movieDetails = String.format("%s, %d, %s, ", movie.getName(), movie.getYear(), movie.getDirector());
                        if (movie.hasRating()) {
                            movieDetails +=  movie.getRating();
                        } else {
                            movieDetails += "unrated";
                        }

                        System.out.println(movieDetails);
                    }

                    break;
                case "checkout movie":
                    Movie movieToCheckout = generateMovieFromUserInput(input);
                    library.checkOutMovie(movieToCheckout);

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

        Movie movie1 = new Movie("Movie 1", 2010, "Director 1", 3);
        Movie movie2 = new Movie("Movie 2", 2002, "Director 2");
        ArrayList<Movie> movieList = new ArrayList<>(Arrays.asList(movie1, movie2));

        return new Library(bookList, movieList);
    }

    private static Book generateBookFromUserInput(Scanner input) {
        System.out.println("Please enter the title of the book:");
        String title = input.nextLine();
        System.out.println("Please enter the author of the book:");
        String author = input.nextLine();
        System.out.println("Please enter the year the book was published:");
        int yearPublished = Integer.parseInt(input.nextLine());

        return new Book(title, author, yearPublished);
    }

    private static Movie generateMovieFromUserInput(Scanner input) {
        System.out.println("Please enter the name of the movie:");
        String name = input.nextLine();
        System.out.println("Please enter the year of release:");
        int year = Integer.parseInt(input.nextLine());
        System.out.println("Please enter the director of the movie:");
        String author = input.nextLine();

        return new Movie(name, year, author);
    }
}

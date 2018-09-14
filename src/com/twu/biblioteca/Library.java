package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Library {
    private ArrayList<Book> bookList;
    private ArrayList<Movie> movieList;
    private ArrayList<Movie> checkedOutMovies = new ArrayList<>();
    private HashMap<String, ArrayList<Book>> checkedOutBooks= new HashMap<>();
    private HashMap<String, Customer> customers = new HashMap<>();

    public Library(ArrayList<Book> bookList, ArrayList<Movie> movieList) {
        this.bookList = bookList;
        this.movieList = movieList;
    }

    public ArrayList<Book> getBooks() {
        Collections.sort(bookList);
        return bookList;
    }

    public void checkOutBook(Book book, String userId) throws ItemNotAvailableException {
        if (bookList.contains(book)) {
            if (!checkedOutBooks.containsKey(userId)) {
                checkedOutBooks.put(userId, new ArrayList<>());
            }

            checkedOutBooks.get(userId).add(book);
            bookList.remove(book);
        } else {
            throw new ItemNotAvailableException(
                    "Book with title: " + book.getTitle() + ", author: " + book.getAuthor()
                            + " and date published: " + book.getYearPublished() + " not found"
            );
        }
    }

    public void returnBook(Book book, String userId) throws BookNotCheckedOutException {
        if (checkedOutBooks.containsKey(userId) && checkedOutBooks.get(userId).contains(book)) {
            bookList.add(book);
            checkedOutBooks.get(userId).remove(book);
        } else {
            throw new BookNotCheckedOutException(
                    String.format(
                            "Book with title: %s, author: %s and date published: %d has not been checked out of this library",
                            book.getTitle(), book.getAuthor(), book.getYearPublished()
                    )
            );
        }
    }

    public ArrayList<Movie> getMovies() {
        Collections.sort(movieList);
        return movieList;
    }

    public void checkOutMovie(Movie movie) throws ItemNotAvailableException {
        if (movieList.contains(movie)) {
            movieList.remove(movie);
            checkedOutMovies.add(movie);
            System.out.println("Thank you! Enjoy the movie");
        } else {
            throw new ItemNotAvailableException(
                String.format(
                        "Movie with name: %s, year: %d, and director: %s not found",
                        movie.getName(), movie.getYear(), movie.getRating()
                )
            );
        }
    }

    public ArrayList<Book> getCheckedOutBooks(String userId) {
        return checkedOutBooks.get(userId);
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getUserId(), customer);
    }

    public Customer getCustomer(String userId) {
        return customers.get(userId);
    }

    public boolean userLogin(String userId, String password) {
        if (customers.containsKey(userId) && password.equals(customers.get(userId).getPassword())) {
            System.out.println("Login successful!");
            return true;
        }

        System.out.println("Customer ID or password incorrect, please try again.");
        return false;
    }
}

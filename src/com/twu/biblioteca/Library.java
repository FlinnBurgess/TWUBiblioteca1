package com.twu.biblioteca;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Library {
    private ArrayList<Item> availableItems;
    private HashMap<String, ArrayList<Item>> checkedOutItems = new HashMap<>();
    private HashMap<String, Customer> customers = new HashMap<>();

    Library(ArrayList<Item> availableItems) {
        this.availableItems = availableItems;
    }

    ArrayList<Book> getBooks() {
        ArrayList<Book> bookList = new ArrayList<>();

        for (Item item : availableItems) {
            if (item.getClass().equals(Book.class)) {
                bookList.add((Book) item);
            }
        }

        Collections.sort(bookList);

        return bookList;
    }

    void checkout(Item item, String userId) throws ItemNotAvailableException {
        if (availableItems.contains(item)) {
            if (!checkedOutItems.containsKey(userId)) {
                checkedOutItems.put(userId, new ArrayList<>());
            }

            checkedOutItems.get(userId).add(availableItems.get(availableItems.indexOf(item)));
            availableItems.remove(item);
        } else {
            throw new ItemNotAvailableException(String.format(
                    "Item with name: %s, creator: %s, and year: %d not found.",
                    item.getName(),
                    item.getCreator(),
                    item.getYear()
            ));
        }

        if (item.getClass().equals(Book.class)) {
            System.out.println("Thank you! Enjoy the book.");
        } else {
            System.out.println("Thank you! Enjoy the movie.");
        }
    }

    void returnBook(Book book, String userId) throws ItemNotCheckedOutException {
        if (checkedOutBooks.containsKey(userId) && checkedOutBooks.get(userId).contains(book)) {
            bookList.add(book);
            checkedOutBooks.get(userId).remove(book);
        } else {
            throw new ItemNotCheckedOutException(
                    String.format(
                            "Book with title: %s, author: %s and date published: %d has not been checked out of this library",
                            book.getName(), book.getCreator(), book.getYear()
                    )
            );
        }
    }

    ArrayList<Movie> getMovies() {
        Collections.sort(movieList);
        return movieList;
    }

    void returnMovie(Movie movie, String userId) throws ItemNotCheckedOutException {
        if (checkedOutMovies.containsKey(userId) && checkedOutMovies.get(userId).contains(movie)) {
            ArrayList<Movie> usersCheckedOutMovies = checkedOutMovies.get(userId);
            movieList.add(usersCheckedOutMovies.get(usersCheckedOutMovies.indexOf(movie)));
            usersCheckedOutMovies.remove(movie);
        } else {
            throw new ItemNotCheckedOutException(
                    String.format(
                            "Movie with name: %s, year: %d, and director: %s has not been checked out of this library.",
                            movie.getName(),
                            movie.getYear(),
                            movie.getCreator()
                    )
            );
        }

    }

    ArrayList<Book> getCheckedOutBooks(String userId) {
        return checkedOutBooks.get(userId);
    }

    void addCustomer(Customer customer) {
        customers.put(customer.getUserId(), customer);
    }

    Customer getCustomer(String userId) {
        return customers.get(userId);
    }

    boolean userLogin(String userId, String password) {
        if (customers.containsKey(userId) && password.equals(customers.get(userId).getPassword())) {
            System.out.println("Login successful!");
            return true;
        }

        System.out.println("Customer ID or password incorrect, please try again.");
        return false;
    }
}

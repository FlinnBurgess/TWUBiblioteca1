package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Library {
    private ArrayList<Book> bookList;
    private ArrayList<Book> checkedOutBooks = new ArrayList<>();
    private ArrayList<Movie> movieList;
    private ArrayList<Movie> checkedOutMovies = new ArrayList<>();
    private HashMap<String, ArrayList<Book>> checkedOutBooksHashmap = new HashMap<>();

    public Library(ArrayList<Book> bookList, ArrayList<Movie> movieList) {
        this.bookList = bookList;
        this.movieList = movieList;
    }

    public ArrayList<Book> getBooks() {
        Collections.sort(bookList);
        return bookList;
    }

    public void checkOutBook(Book book, String userId) throws BookNotAvailableException {
        if (bookList.contains(book)) {
            if (!checkedOutBooksHashmap.containsKey(userId)) {
                checkedOutBooksHashmap.put(userId, new ArrayList<>());
            }

            checkedOutBooksHashmap.get(userId).add(book);
            bookList.remove(book);
        } else {
            throw new BookNotAvailableException(
                    "Book with title: " + book.getTitle() + ", author: " + book.getAuthor()
                            + " and date published: " + book.getYearPublished() + " not found"
            );
        }
    }

    public void returnBook(Book book, String userId) throws BookNotCheckedOutException {
        if (checkedOutBooksHashmap.get(userId).contains(book)) {
            bookList.add(book);
            checkedOutBooksHashmap.get(userId).remove(book);
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

    public void checkOutMovie(Movie movie) {
        if (movieList.contains(movie)) {
            movieList.remove(movie);
            checkedOutMovies.add(movie);
            System.out.println("Thank you! Enjoy the movie");
        }
    }

    public ArrayList<Book> getCheckedOutBooks(String userId) {
        return checkedOutBooksHashmap.get(userId);
    }
}

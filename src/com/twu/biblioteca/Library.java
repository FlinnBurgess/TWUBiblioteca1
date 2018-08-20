package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;

public class Library {
    private ArrayList<Book> bookList;
    private ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
    private ArrayList<Movie> movieList;
    private ArrayList<Movie> checkedOutMovies = new ArrayList<Movie>();

    public Library(ArrayList<Book> bookList, ArrayList<Movie> movieList) {
        this.bookList = bookList;
        this.movieList = movieList;
    }

    public ArrayList<Book> getBooks() {
        Collections.sort(bookList);
        return bookList;
    }

    public void checkOutBook(Book book) throws BookNotAvailableException {
        if (bookList.contains(book)) {
            checkedOutBooks.add(book);
            bookList.remove(book);
        } else {
            throw new BookNotAvailableException(
                    "Book with title: " + book.getTitle() + ", author: " + book.getAuthor()
                            + " and date published: " + book.getYearPublished() + " not found"
            );
        }
    }

    public void returnBook(Book book) throws BookNotCheckedOutException {
        if (checkedOutBooks.contains(book)) {
            bookList.add(book);
            checkedOutBooks.remove(book);
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
}

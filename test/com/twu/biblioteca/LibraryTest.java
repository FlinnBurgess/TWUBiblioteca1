package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;
    private Movie movie1;
    private Movie movie2;

    @Before
    public void setUp(){
        book1 = new Book("title1", "author1", 2000);
        book2 = new Book("title2", "author2", 2000);
        ArrayList<Book> bookList = new ArrayList<>(Arrays.asList(book1, book2));

        movie1 = new Movie("movie1", 2000, "director1", 5);
        movie2 = new Movie("movie2", 2000, "director2", 5);
        ArrayList<Movie> movieList = new ArrayList<>(Arrays.asList(movie1, movie2));

        library = new Library(bookList, movieList);
    }

    @Test
    public void testLibraryReturnsCorrectBookList() {
        ArrayList<Book> bookList = new ArrayList<>(Arrays.asList(book1, book2));
        ArrayList<Book> differentBookList = new ArrayList<>(Arrays.asList(book1));

        Collections.sort(bookList);
        Collections.sort(differentBookList);

        Assert.assertTrue(bookList.equals(library.getBooks()));
        Assert.assertFalse(differentBookList.equals(library.getBooks()));
    }

    @Test
    public void testCheckoutRemovesBookFromAvailableBooks() throws BookNotAvailableException {
        library.checkOutBook(book1);

        ArrayList<Book> bookList = new ArrayList<>(Arrays.asList(book2));
        Collections.sort(bookList);

        Assert.assertTrue(bookList.equals(library.getBooks()));
    }

    @Test(expected = BookNotAvailableException.class)
    public void testCheckoutThrowsExceptionOnUnavailableBook() throws BookNotAvailableException {
        Book unavailableBook = new Book("unavailable", "unavailable", 0);
        library.checkOutBook(unavailableBook);
    }

    @Test
    public void testReturnBookUpdatesListOfAvailableBooks() throws BookNotAvailableException, BookNotCheckedOutException {
        library.checkOutBook(book1);
        library.checkOutBook(book2);

        library.returnBook(book1);

        ArrayList<Book> bookList = new ArrayList<>(Arrays.asList(book1));
        Collections.sort(bookList);

        Assert.assertTrue(bookList.equals(library.getBooks()));
    }

    @Test(expected = BookNotCheckedOutException.class)
    public void testReturnNonCheckedOutBookThrowsException() throws BookNotCheckedOutException {
        library.returnBook(book1);
    }

    @Test
    public void testCheckoutMovieUpdatesListOfAvailableMovies() {
        library.checkOutMovie(movie1);

        ArrayList<Movie> expectedMovieList = new ArrayList<>();
        expectedMovieList.add(movie2);

        Assert.assertTrue(expectedMovieList.equals(library.getMovies()));
    }
}

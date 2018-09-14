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
    private String userId;

    @Before
    public void setUp(){
        book1 = new Book("title1", "author1", 2000);
        book2 = new Book("title2", "author2", 2000);
        ArrayList<Book> bookList = new ArrayList<>(Arrays.asList(book1, book2));

        movie1 = new Movie("movie1", 2000, "director1", 5);
        movie2 = new Movie("movie2", 2000, "director2", 5);
        ArrayList<Movie> movieList = new ArrayList<>(Arrays.asList(movie1, movie2));

        library = new Library(bookList, movieList);
        userId = "123-4567";

        library.addCustomer(createGenericCustomer());
    }

    private Customer createGenericCustomer() {
        return new Customer("name", "email@address.com", "01234 567 890", "123-4567", "password");
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
    public void testCheckoutBookRemovesBookFromAvailableBooks() throws BookNotAvailableException {
        library.checkOutBook(book1, userId);

        ArrayList<Book> bookList = new ArrayList<>(Arrays.asList(book2));
        Collections.sort(bookList);

        Assert.assertTrue(bookList.equals(library.getBooks()));
    }

    @Test
    public void testCheckoutBookAssignsBookToCorrectUser() throws BookNotAvailableException {
        library.checkOutBook(book1, userId);

        ArrayList<Book> expectedUserBooks = new ArrayList<>();
        expectedUserBooks.add(book1);

        Assert.assertTrue(expectedUserBooks.equals(library.getCheckedOutBooks(userId)));
    }

    @Test(expected = BookNotAvailableException.class)
    public void testCheckoutThrowsExceptionOnUnavailableBook() throws BookNotAvailableException {
        Book unavailableBook = new Book("unavailable", "unavailable", 0);
        library.checkOutBook(unavailableBook, userId);
    }

    @Test
    public void testReturnBookUpdatesListOfAvailableBooks() throws BookNotAvailableException, BookNotCheckedOutException {
        library.checkOutBook(book1, userId);
        library.checkOutBook(book2, userId);

        library.returnBook(book1, userId);

        ArrayList<Book> bookList = new ArrayList<>(Arrays.asList(book1));
        Collections.sort(bookList);

        Assert.assertTrue(bookList.equals(library.getBooks()));
    }

    @Test(expected = BookNotCheckedOutException.class)
    public void testReturnNonCheckedOutBookThrowsException() throws BookNotCheckedOutException {
        library.returnBook(book1, userId);
    }

    @Test
    public void testCheckoutMovieUpdatesListOfAvailableMovies() {
        library.checkOutMovie(movie1);

        ArrayList<Movie> expectedMovieList = new ArrayList<>();
        expectedMovieList.add(movie2);

        Assert.assertTrue(expectedMovieList.equals(library.getMovies()));
    }

    @Test
    public void testGetCustomerReturnsExpectedCustomer() {
        Customer customer = createGenericCustomer();
        Assert.assertTrue(customer.equals(library.getCustomer(userId)));
    }

    @Test
    public void testUserLoginCorrectlyValidatesUserCredentials() {
        Assert.assertTrue(library.userLogin(userId, "password"));

        Assert.assertFalse(library.userLogin("incorrect id", "password"));

        Assert.assertFalse(library.userLogin(userId, "incorrect password"));
    }
}

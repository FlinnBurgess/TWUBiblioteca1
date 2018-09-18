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
    private String userName = "name";
    private String userEmail = "email@address.com";
    private String userPhone = "01234 567 890";
    private String userId = "123-4567";
    private String userPassword = "password";

    @Before
    public void setUp(){
        book1 = new Book("title1", "author1", 2000);
        book2 = new Book("title2", "author2", 2000);
        movie1 = new Movie("movie1", 2000, "director1", 5);
        movie2 = new Movie("movie2", 2000, "director2", 5);

        ArrayList<Item> itemList = new ArrayList<>(Arrays.asList(book1, book2, movie1, movie2));

        library = new Library(itemList);
        library.addCustomer(new Customer(userName, userEmail, userPhone, userId, userPassword));
    }

    @Test
    public void testLibraryReturnsCorrectBookList() {
        ArrayList<Book> bookList = new ArrayList<>(Arrays.asList(book1, book2));
        ArrayList<Book> differentBookList = new ArrayList<>(Collections.singletonList(book1));

        Collections.sort(bookList);

        Assert.assertEquals(bookList, library.getBooks());
        Assert.assertNotEquals(differentBookList, library.getBooks());
    }

    @Test
    public void testCheckoutRemovesItemFromAvailableItems() throws ItemNotAvailableException {
        library.checkout(book1, userId);
        ArrayList<Book> expectedBookList = new ArrayList<>(Collections.singletonList(book2));
        Assert.assertEquals(expectedBookList, library.getBooks());

        library.checkout(movie1, userId);
        ArrayList<Movie> expectedMovieList = new ArrayList<>(Collections.singletonList(movie2));
        Assert.assertEquals(expectedMovieList, library.getMovies());
    }

    @Test(expected = ItemNotAvailableException.class)
    public void testCheckoutThrowsExceptionOnUnavailableBook() throws ItemNotAvailableException {
        Book unavailableBook = new Book("unavailable", "unavailable", 0);
        library.checkout(unavailableBook, userId);
    }

    @Test(expected = ItemNotAvailableException.class)
    public void testCheckoutThrowsExceptionOnUnavailableMovie() throws ItemNotAvailableException {
        Movie unavailableMovie = new Movie("unavailable", 10, "unavailable");
        library.checkout(unavailableMovie, userId);
    }

    @Test
    public void testReturnItemUpdatesListOfAvailableItems() throws ItemNotAvailableException, ItemNotCheckedOutException {
        library.checkout(book1, userId);
        library.checkout(book2, userId);
        library.returnItem(book1, userId);

        ArrayList<Book> expectedBookList = new ArrayList<>(Collections.singletonList(book1));
        Assert.assertEquals(expectedBookList, library.getBooks());

        library.checkout(movie1, userId);
        library.checkout(movie2, userId);
        library.returnItem(movie1, userId);

        ArrayList<Movie> expectedMovieList = new ArrayList<>(Collections.singletonList(movie1));
        Assert.assertEquals(expectedMovieList, library.getMovies());
    }

    @Test(expected = ItemNotCheckedOutException.class)
    public void testReturnNonCheckedOutBookThrowsException() throws ItemNotCheckedOutException {
        library.returnItem(book1, userId);
    }

    @Test(expected = ItemNotCheckedOutException.class)
    public void testReturnNonCheckedOutMovieThrowsException() throws ItemNotCheckedOutException {
        library.returnItem(movie1, userId);
    }

    @Test
    public void testGetCustomerReturnsExpectedCustomer() {
        Customer customer = new Customer(userName, userEmail, userPhone, userId, userPassword);
        Assert.assertEquals(customer, library.getCustomer(userId));
    }

    @Test
    public void testUserLoginCorrectlyValidatesUserCredentials() {
        Assert.assertTrue(library.userLogin(userId, userPassword));
        Assert.assertFalse(library.userLogin("incorrect id", userPassword));
        Assert.assertFalse(library.userLogin(userId, "incorrect password"));
    }
}

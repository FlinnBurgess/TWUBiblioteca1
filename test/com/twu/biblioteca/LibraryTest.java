package com.twu.biblioteca;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;

    @Before
    public void setUp(){
        book1 = new Book("title1", "author1", 2000);
        book2 = new Book("title2", "author2", 2000);
        ArrayList<Book> bookList = new ArrayList<Book>(Arrays.asList(book1, book2));

        library = new Library(bookList);
    }

    @Test
    public void testLibraryReturnsCorrectBookList() {
        ArrayList<Book> bookList = new ArrayList<Book>(Arrays.asList(book1, book2));
        ArrayList<Book> differentBookList = new ArrayList<Book>(Arrays.asList(book1));

        assertTrue(bookList.equals(library.getBooks()));
        assertFalse(differentBookList.equals(library.getBooks()));
    }

    @Test
    public void testCheckoutRemovesBookFromAvailableBooks() throws BookNotAvailableException {
        library.checkOutBook("title1", "author1", 2000);

        ArrayList<Book> bookList = new ArrayList<Book>(Arrays.asList(book2));

        assertTrue(bookList.equals(library.getBooks()));
    }

    @Test(expected = BookNotAvailableException.class)
    public void testCheckoutThrowsExceptionOnUnavailableBook() throws BookNotAvailableException {
        library.checkOutBook("unavailable", "unavailable", 0);
    }
}

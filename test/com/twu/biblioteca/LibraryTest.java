package com.twu.biblioteca;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class LibraryTest extends TestCase {
    Library library;
    Book book1;
    Book book2;

    @Override
    protected void setUp(){
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
    public void testCheckoutRemovesBookFromAvailableBooks() {

    }
}

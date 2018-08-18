package com.twu.biblioteca;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class LibraryTest {

    @Test
    public void testLibraryReturnsCorrectBookList() {
        Book book1 = new Book("title1", "author1", 2000);
        Book book2 = new Book("title2", "author2", 2000);
        ArrayList<Book> bookList = new ArrayList<Book>(Arrays.asList(book1, book2));

        Library library = new Library(bookList);

        Assert(bookList.equals(library.getBooks()));
    }
}

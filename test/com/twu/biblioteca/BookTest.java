package com.twu.biblioteca;

import org.junit.Test;

public class BookTest {

    @Test
    public void testBookEquality() {
        Book book1 = new Book("TITLE", "author", 2008);
        Book book2 = new Book("title", "author", 2008);

        assert (book1.equals(book2));
    }
}

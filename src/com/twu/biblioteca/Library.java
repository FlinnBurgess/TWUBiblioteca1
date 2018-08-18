package com.twu.biblioteca;

import java.util.ArrayList;

public class Library {
    ArrayList<Book> bookList;

    public Library(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public ArrayList<Book> getBooks() {
        return bookList;
    }
}

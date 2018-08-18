package com.twu.biblioteca;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> bookList;
    private ArrayList<Book> checkedOut = new ArrayList<Book>();

    public Library(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public ArrayList<Book> getBooks() {
        return bookList;
    }

    public void checkOutBook(String title, String author, int yearPublished) {
        Book bookToCheckOut = new Book(title, author, yearPublished);

        if (bookList.contains(bookToCheckOut)) {
            checkedOut.add(bookToCheckOut);
            bookList.remove(bookToCheckOut);
        }
    }
}
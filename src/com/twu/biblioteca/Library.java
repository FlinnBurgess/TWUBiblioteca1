package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;

public class Library {
    private ArrayList<Book> bookList;
    private ArrayList<Book> checkedOut = new ArrayList<Book>();

    public Library(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public ArrayList<Book> getBooks() {
        Collections.sort(bookList);
        return bookList;
    }

    public void checkOutBook(String title, String author, int yearPublished) throws BookNotAvailableException {
        Book bookToCheckOut = new Book(title, author, yearPublished);

        if (bookList.contains(bookToCheckOut)) {
            checkedOut.add(bookToCheckOut);
            bookList.remove(bookToCheckOut);
        } else {
            throw new BookNotAvailableException(
                    "Book with title: " + title + ", author: " + author + " and date published: " + yearPublished + " not found"
            );
        }
    }

    public void returnBook(String title, String author, int yearPublished) throws BookNotCheckedOutException {
        Book bookToReturn = new Book(title, author, yearPublished);

        if (checkedOut.contains(bookToReturn)) {
            bookList.add(bookToReturn);
            checkedOut.remove(bookToReturn);
        } else {
            throw new BookNotCheckedOutException(
                    String.format(
                            "Book with title: %s, author: %s and date published: %d has not been checked out of this library",
                            title, author, yearPublished
                    )
            );
        }
    }
}

package com.twu.biblioteca;

public class Book implements Comparable {
    private String title;
    private String author;
    private int yearPublished;

    public Book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    @Override
    public boolean equals(Object object) {
        Book book = (Book) object;

        return (
            this.author.toLowerCase().equals(book.getAuthor().toLowerCase())
            && this.yearPublished == book.getYearPublished()
            && this.title.toLowerCase().equals(book.getTitle().toLowerCase())
        );
    }

    @Override
    public int compareTo(Object o) {
        Book bookToCompare = (Book) o;

        return this.title.compareTo(bookToCompare.getTitle());
    }
}

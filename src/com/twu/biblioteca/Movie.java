package com.twu.biblioteca;

public class Movie extends Item {
    private int rating;

    public Movie(String name, int year, String director, int rating) {
        super(name, director, year);
        this.rating = rating;
    }

    public Movie(String name, int year, String director) {
        this(name, year, director, -1);
    }

    boolean hasRating() {
        return rating >= 0;
    }

    int getRating() {
        return rating;
    }
}

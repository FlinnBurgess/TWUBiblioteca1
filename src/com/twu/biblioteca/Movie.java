package com.twu.biblioteca;

public class Movie implements Comparable {
    private String name;
    private int year;
    private String director;
    private int rating;

    public Movie(String name, int year, String director, int rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public Movie(String name, int year, String director) {
        this(name, year, director, -1);
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public boolean hasRating() {
        return rating >= 0;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object o) {
        Movie movie = (Movie) o;

        return (
            name.toLowerCase().equals(movie.getName().toLowerCase())
            && year == movie.getYear()
            && director.toLowerCase().equals(movie.getDirector().toLowerCase())
        );
    }

    @Override
    public int compareTo(Object o) {
        Movie movie = (Movie) o;

        return this.name.compareTo(movie.getName());
    }
}

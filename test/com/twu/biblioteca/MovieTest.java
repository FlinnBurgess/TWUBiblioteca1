package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class MovieTest {
    private String testName = "name";
    private int testYear = 2005;
    private String testDirector = "director";
    private int testRating = 5;

    @Test
    public void testMovieEquality() {
        Movie movie = createRatedMovie();
        Movie sameMovie = createRatedMovie();

        Assert.assertTrue(movie.equals(sameMovie));

        Movie differentMovie = new Movie("different name", testYear, testDirector, testRating);

        Assert.assertFalse(movie.equals(differentMovie));
    }

    @Test
    public void testHasRatingReturnsCorrectValues() {
        Movie rated = createRatedMovie();
        Assert.assertTrue(rated.hasRating());

        Movie unrated = createUnratedMovie();
        Assert.assertFalse(unrated.hasRating());
    }

    @Test
    public void testGetRatingReturnsCorrectValue() {
        Movie rated = createRatedMovie();
        Assert.assertTrue(rated.getRating() == testRating);

        Movie unrated = createUnratedMovie();
        Assert.assertTrue(unrated.getRating() == -1);
    }

    private Movie createRatedMovie() {
        return new Movie(testName, testYear, testDirector, testRating);
    }

    private Movie createUnratedMovie() {
        return new Movie(testName, testYear, testDirector);
    }
}

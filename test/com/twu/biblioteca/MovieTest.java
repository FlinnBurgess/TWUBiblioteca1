package com.twu.biblioteca;

import org.junit.Test;
import org.junit.Assert;

public class MovieTest {

    @Test
    public void testMovieEquality() {
        Movie movie = new Movie("name", 2005, "director", 5);
        Movie sameMovie = new Movie("NAME", 2005, "director", 5);

        Assert.assertTrue(movie.equals(sameMovie));

        Movie differentMovie = new Movie("different name", 2005, "director", 5);

        Assert.assertFalse(movie.equals(differentMovie));
    }
}

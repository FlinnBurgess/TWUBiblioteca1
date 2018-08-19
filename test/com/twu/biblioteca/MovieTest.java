package com.twu.biblioteca;

import org.junit.Test;
import org.junit.Assert;

public class MovieTest {

    @Test
    public void testMovieEquality() {
        Movie movie1 = new Movie("name", 2005, "director", 5);
        Movie movie2 = new Movie("name", 2005, "director", 5);

        Assert.assertTrue(movie1.equals(movie2));
    }
}

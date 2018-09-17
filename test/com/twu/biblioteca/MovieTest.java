package com.twu.biblioteca;

import org.junit.Test;
import org.junit.Assert;

public class MovieTest {
    private String testName = "name";
    private int testYear = 2005;
    private String testDirector = "director";
    private int testRating = 5;

    @Test
    public void testHasRatingReturnsCorrectValues() {
        Movie rated = new Movie(testName, testYear, testDirector, testRating);
        Assert.assertTrue(rated.hasRating());

        Movie unrated = new Movie(testName, testYear, testDirector);
        Assert.assertFalse(unrated.hasRating());
    }

    @Test
    public void testGetRatingReturnsCorrectValue() {
        Movie rated = new Movie(testName, testYear, testDirector, testRating);
        Assert.assertEquals(testRating, rated.getRating());

        Movie unrated = new Movie(testName, testYear, testDirector);
        Assert.assertEquals(-1, unrated.getRating());
    }

}

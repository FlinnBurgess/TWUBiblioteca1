package com.twu.biblioteca;

import org.junit.Assert;
import org.junit.Test;

public class ItemTest {

    @Test
    public void testItemEquality() {
        Item item1 = new Item("name", "creator", 2000);
        Item item2 = new Item("NAME", "CREATOR", 2000);

        Assert.assertEquals(item1, item2);
    }
}

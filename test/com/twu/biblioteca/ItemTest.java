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

    @Test
    public void testItemComparison() {
        Item higherOrderItem = new Item("a", "creator", 2000);
        Item higherOrderItemDuplicate = new Item("a", "creator", 2000);
        Item lowerOrderItem = new Item("b", "creator", 2000);

        Assert.assertEquals(0, higherOrderItem.compareTo(higherOrderItemDuplicate));
        Assert.assertTrue(higherOrderItem.compareTo(lowerOrderItem) < 0);
        Assert.assertTrue(lowerOrderItem.compareTo(higherOrderItem) > 0);
    }
}

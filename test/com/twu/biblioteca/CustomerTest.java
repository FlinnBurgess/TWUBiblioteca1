package com.twu.biblioteca;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

    @Test
    public void testGetCustomerDetailsReturnsExpectedMessage() {
        Customer customer = new Customer("name", "email@address.com", "01234 567 890", "password", "1234567");

        String expectedMessage = "name, email@address.com, 01234 567 890";

        Assert.assertTrue(expectedMessage.equals(customer.getDetails()));
    }
}

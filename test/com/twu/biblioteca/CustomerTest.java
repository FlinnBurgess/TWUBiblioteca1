package com.twu.biblioteca;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

    @Test
    public void testGetCustomerDetailsReturnsExpectedMessage() {
        Customer customer = new Customer("name", "email@address.com", 12345678910, "password", "1234567");

        String expectedMessage = "name, email@address.com, 12345678910";

        Assert.assertTrue(expectedMessage.equals(customer.getDetails()));
    }
}

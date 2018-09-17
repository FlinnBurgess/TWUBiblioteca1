package com.twu.biblioteca;

public class Customer {
    private String name;
    private String emailAddress;
    private String phoneNumber;
    private String userId;
    private String password;

    public Customer(String name, String emailAddress, String phoneNumber, String userId, String password) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.password = password;
    }

    String getDetails() {
        return String.format("%s, %s, %s", name, emailAddress, phoneNumber);
    }

    String getUserId() {
        return userId;
    }

    String getPassword()
    {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (!getClass().equals(o.getClass())) {
            return false;
        }

        Customer customer = (Customer) o;

        return customer.getUserId().equals(getUserId());
    }
}

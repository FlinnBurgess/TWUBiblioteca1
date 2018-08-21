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

    public String getDetails() {
        return String.format("%s, %s, %s", name, emailAddress, phoneNumber);
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}

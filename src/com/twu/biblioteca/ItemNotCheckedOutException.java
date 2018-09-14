package com.twu.biblioteca;

public class ItemNotCheckedOutException extends Exception {
    public ItemNotCheckedOutException(String message) {
        super(message);
    }
}

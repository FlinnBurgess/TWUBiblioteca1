package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Library {
    private ArrayList<Item> availableItems;
    private HashMap<String, ArrayList<Item>> checkedOutItems = new HashMap<>();
    private HashMap<String, Customer> customers = new HashMap<>();

    Library(ArrayList<Item> availableItems) {
        this.availableItems = availableItems;
    }

    ArrayList<Book> getBooks() {
        ArrayList<Book> bookList = getItemsOfType(Book.class);
        Collections.sort(bookList);
        return bookList;
    }

    ArrayList<Movie> getMovies() {
        ArrayList<Movie> movieList = getItemsOfType(Movie.class);
        Collections.sort(movieList);
        return movieList;
    }

    private <T> ArrayList<T> getItemsOfType(Class<T> itemClass) {
        ArrayList<T> itemList = new ArrayList<>();

        for (Item item : availableItems) {
            if (item.getClass().equals(itemClass)) {
                itemList.add(itemClass.cast(item));
            }
        }

        return itemList;
    }

    void checkout(Item item, String userId) throws ItemNotAvailableException {
        if (availableItems.contains(item)) {
            if (!checkedOutItems.containsKey(userId)) {
                checkedOutItems.put(userId, new ArrayList<>());
            }

            checkedOutItems.get(userId).add(availableItems.get(availableItems.indexOf(item)));
            availableItems.remove(item);
        } else {
            throw new ItemNotAvailableException(String.format(
                    "Item with name: %s, creator: %s, and year: %d not found.",
                    item.getName(),
                    item.getCreator(),
                    item.getYear()
            ));
        }
    }

    void returnItem(Item item, String userId) throws ItemNotCheckedOutException {
        if (checkedOutItems.containsKey(userId) && checkedOutItems.get(userId).contains(item)) {
            ArrayList<Item> usersCheckedOutItems = checkedOutItems.get(userId);
            availableItems.add(usersCheckedOutItems.get(usersCheckedOutItems.indexOf(item)));
            usersCheckedOutItems.remove(item);
        } else {
            throw new ItemNotCheckedOutException(String.format(
                    "Item with name: %s, creator: %s, and year: %d has not been checked out.",
                    item.getName(),
                    item.getCreator(),
                    item.getYear()
            ));
        }

        System.out.println("Thanks for returning the item!");
    }

    void addCustomer(Customer customer) {
        customers.put(customer.getUserId(), customer);
    }

    Customer getCustomer(String userId) {
        return customers.get(userId);
    }

    boolean userLogin(String userId, String password) {
        if (customers.containsKey(userId) && password.equals(customers.get(userId).getPassword())) {
            System.out.println("Login successful!");
            return true;
        }

        System.out.println("Customer ID or password incorrect, please try again.");
        return false;
    }
}

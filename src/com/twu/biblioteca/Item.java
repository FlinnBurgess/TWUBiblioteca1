package com.twu.biblioteca;

public class Item implements Comparable<Item> {
    private String name;
    private String creator;
    private int year;

    Item(String name, String creator, int year) {
        this.name = name;
        this.creator = creator;
        this.year = year;
    }

    String getName() {
        return name;
    }

    String getCreator() {
        return creator;
    }

    int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) {
            return false;
        }

        Item item = (Item) o;

        return (
            name.toLowerCase().equals(item.getName().toLowerCase())
            && year == item.getYear()
            && creator.toLowerCase().equals(item.getCreator().toLowerCase())
        );
    }

    @Override
    public int compareTo(Item item) {
        return this.name.compareTo(item.getName());
    }
}

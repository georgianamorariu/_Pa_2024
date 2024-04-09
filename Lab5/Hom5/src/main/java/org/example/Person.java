package org.example;

public record Person(String name, String id) {
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}

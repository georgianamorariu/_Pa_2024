package org.example;

/**
 * Represents a person with basic attributes.
 */
public class Person {
    private String name; // The name of the person
    private int age; // The age of the person
    private String destination; // The destination of the person

    /**
     * Constructs a person with specified name, age, and destination.
     *
     * @param name        the name of the person
     * @param age         the age of the person
     * @param destination the destination of the person
     */
    public Person(String name, int age, String destination) {
        this.name = name;
        this.age = age;
        this.destination = destination;
    }

    /**
     * Gets the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the age of the person.
     *
     * @return the age of the person
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the destination of the person.
     *
     * @return the destination of the person
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Returns a string representation of the person.
     *
     * @return a string representation of the person
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Destination: " + destination;
    }
}

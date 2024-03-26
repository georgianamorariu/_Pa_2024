package org.example;

/**
 * Represents a passenger, extending the Person class.
 */
public class Passenger extends Person implements Comparable<Passenger> {

    /**
     * Constructs a passenger with specified name, age, and destination.
     *
     * @param name        the name of the passenger
     * @param age         the age of the passenger
     * @param destination the destination of the passenger
     */
    public Passenger(String name, int age, String destination) {
        super(name, age, destination);
    }

    /**
     * Compares this passenger with the specified passenger for order based on their names.
     *
     * @param other the passenger to be compared
     * @return a negative integer, zero, or a positive integer as this passenger is less than, equal to, or greater than the specified passenger
     */
    @Override
    public int compareTo(Passenger other) {
        return this.getName().compareTo(other.getName());
    }
}

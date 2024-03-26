package org.example;

/**
 * Represents a driver, extending the Person class.
 */
public class Driver extends Person implements Comparable<Driver> {

    /**
     * Constructs a driver with specified name, age, and destination.
     *
     * @param name        the name of the driver
     * @param age         the age of the driver
     * @param destination the destination of the driver
     */
    public Driver(String name, int age, String destination) {
        super(name, age, destination);
    }

    /**
     * Compares this driver with the specified driver for order based on their ages.
     *
     * @param other the driver to be compared
     * @return a negative integer, zero, or a positive integer as this driver is less than, equal to, or greater than the specified driver
     */
    @Override
    public int compareTo(Driver other) {
        return Integer.compare(this.getAge(), other.getAge());
    }
}

package org.example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a carpooling problem with drivers and passengers.
 */
public class CarpoolProblem {
    private final List<Driver> drivers;
    private final Set<Passenger> passengers;

    /**
     * Constructs a CarpoolProblem with a list of people.
     *
     * @param people the list of people including both drivers and passengers
     */
    public CarpoolProblem(List<Person> people) {
        this.drivers = people.stream()
                .filter(person -> person instanceof Driver)
                .map(person -> (Driver) person)
                .collect(Collectors.toList());

        this.passengers = people.stream()
                .filter(person -> person instanceof Passenger)
                .map(person -> (Passenger) person)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Gets the destinations of all drivers.
     *
     * @return the list of destinations of all drivers
     */
    public List<String> getDestinationsOfDrivers() {
        return drivers.stream()
                .map(Driver::getDestination)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Creates a map of destinations to the set of people going to each destination.
     *
     * @return the destination map
     */
    public Map<String, Set<Person>> getDestinationMap() {
        Map<String, Set<Person>> destinationMap = new HashMap<>();
        for (Person person : passengers) {
            destinationMap.computeIfAbsent(person.getDestination(), k -> new HashSet<>()).add(person);
        }
        for (Driver driver : drivers) {
            destinationMap.computeIfAbsent(driver.getDestination(), k -> new HashSet<>()).add(driver);
        }
        return destinationMap;
    }

    /**
     * Matches drivers with passengers based on their ages and destinations.
     *
     * @return a map of drivers to their best matched passengers
     */
    public Map<Driver, Passenger> matchDriversAndPassengers() {
        Map<Driver, Passenger> matches = new HashMap<>();
        for (Driver driver : drivers) {
            Passenger bestMatch = findBestMatch(driver, passengers);
            if (bestMatch != null) {
                matches.put(driver, bestMatch);
                passengers.remove(bestMatch);
            }
        }
        return matches;
    }

    /**
     * Finds the best match for a driver among the passengers.
     *
     * @param driver     the driver for whom to find the best match
     * @param passengers the set of passengers to consider
     * @return the best matched passenger
     */
    private Passenger findBestMatch(Driver driver, Set<Passenger> passengers) {
        Passenger bestMatch = null;
        int minDistance = Integer.MAX_VALUE;
        for (Passenger passenger : passengers) {
            if (passenger.getDestination().equals(driver.getDestination())) {
                return passenger; // If passenger's destination matches driver's destination, return immediately
            }
            int distance = Math.abs(passenger.getAge() - driver.getAge());
            if (distance < minDistance) {
                minDistance = distance;
                bestMatch = passenger;
            }
        }
        return bestMatch;
    }
}

// CarpoolProblem.java
package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class CarpoolProblem {
    private final List<Driver> drivers;
    private final Set<Passenger> passengers;

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

    public List<String> getDestinationsOfDrivers() {
        return drivers.stream()
                .map(Driver::getDestination)
                .distinct()
                .collect(Collectors.toList());
    }

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

    private Passenger findBestMatch(Driver driver, Set<Passenger> passengers) {
        Passenger bestMatch = null;
        int minDistance = Integer.MAX_VALUE;
        for (Passenger passenger : passengers) {
            if (passenger.getDestination().equals(driver.getDestination())) {
                return passenger; // If passenger's destination matches driver's destination, return immediately
            }
            // Calculate distance (for simplicity, let's assume distance is difference in ages)
            int distance = Math.abs(passenger.getAge() - driver.getAge());
            if (distance < minDistance) {
                minDistance = distance;
                bestMatch = passenger;
            }
        }
        return bestMatch;
    }


}


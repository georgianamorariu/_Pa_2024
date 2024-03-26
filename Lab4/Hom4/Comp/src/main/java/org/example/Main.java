package org.example;

import java.util.*;
import java.util.stream.Collectors;
import com.github.javafaker.Faker;


public class Main {
    public static void main(String[] args) {
        List<Person> people = createRandomPeople();
        CarpoolProblem carpoolProblem = new CarpoolProblem(people);


        // Calculate destinations of drivers
        List<String> destinationsOfDrivers = carpoolProblem.getDestinationsOfDrivers();
        System.out.println("Destinations of Drivers: " + destinationsOfDrivers);

        // Calculate destination map
        Map<String, Set<Person>> destinationMap = carpoolProblem.getDestinationMap();
        System.out.println("Destination Map:");
        destinationMap.forEach((destination, persons) -> {
            System.out.println(destination + ": " + persons);
        });

        // Match drivers and passengers using greedy algorithm
        Map<Driver, Passenger> matches = carpoolProblem.matchDriversAndPassengers();
        System.out.println("\nMatches:");
        matches.forEach((driver, passenger) -> {
            System.out.println(driver.getName() + " -> " + passenger.getName());
        });
    }

    private static List<Person> createRandomPeople() {
        List<Person> people = new ArrayList<>();
        Faker faker = new Faker();

        // Create random drivers
        for (int i = 0; i < 2; i++) {
            String name = faker.name().firstName();
            int age = faker.number().numberBetween(20, 70);
            String destination = faker.country().capital(); // Generate random destination
            people.add(new Driver(name, age, destination));
        }

        // Create random passengers
        for (int i = 0; i < 2; i++) {
            String name = faker.name().firstName();
            int age = faker.number().numberBetween(15, 35);
            String destination = faker.country().capital(); // Generate random destination
            people.add(new Passenger(name, age, destination));
        }

        return people;
    }

}
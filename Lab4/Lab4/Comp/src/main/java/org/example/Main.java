package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> people = createRandomPeople();

        List<Driver> drivers = people.stream()
                .filter(person -> person instanceof Driver)
                .map(person -> (Driver) person)
                .collect(Collectors.toList());

        Set<Passenger> passengers = people.stream()
                .filter(person -> person instanceof Passenger)
                .map(person -> (Passenger) person)
                .collect(Collectors.toCollection(TreeSet::new));

        Collections.sort(drivers);

        System.out.println("Drivers (Sorted by Age):");
        drivers.forEach(System.out::println);

        System.out.println("\nPassengers (Sorted by Name):");
        passengers.forEach(System.out::println);
    }

    private static List<Person> createRandomPeople() {
        List<Person> people = new ArrayList<>();
        Random random = new Random();
        String[] names = {"Alina", "Bogdan", "Cristi", "Daniela", "Emma", "Francesca", "George", "Ionela", "Laur", "Mihai", "Nicoleta"};

        // Create random drivers
        for (int i = 0; i < 2; i++) {
            String name = names[random.nextInt(names.length)];
            int age = random.nextInt(50) + 19;
            people.add(new Driver(name, age, null));
        }

        // Create random passengers
        for (int i = 0; i < 2; i++) {
            String name = names[random.nextInt(names.length)];
            int age = random.nextInt(20) + 15;
            people.add(new Passenger(name, age, null));
        }
        return people;
    }
}
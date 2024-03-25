package org.example;

public class Person {
    public String name;
    public int age;
    public String destination;

    public Person(String name, int age, String destination) {
        this.name = name;
        this.age = age;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Destination: " + destination;
    }
}
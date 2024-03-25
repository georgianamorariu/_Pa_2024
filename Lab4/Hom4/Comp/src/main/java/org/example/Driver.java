package org.example;

public class Driver extends Person implements Comparable<Driver>{
    public Driver(String name, int age, String destination) {
        super(name, age, destination);
    }

    @Override
    public int compareTo(Driver other){
        return Integer.compare(this.age, other.age);
    }
}

package org.example;

public class Passenger extends Person implements Comparable<Passenger>{
    public Passenger(String name, int age, String destination) {
        super(name, age, destination);
    }
    @Override
    public int compareTo(Passenger other){
        return this.name.compareTo(other.name);
    }
}
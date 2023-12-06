package DAO;

public class Greeter {
    public void greet(Person p){
        System.out.println("Hi, " + p.getName());
    }

    public void greet(Pirate p){
        System.out.println("Ahoy, " + p.getName());
    }
}

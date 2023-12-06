package DAO;

public class Pirate extends Person{
    public Pirate(String name) {
        super(name);
    }

    public String getName(){
        return name + " the pirate";
    }
}

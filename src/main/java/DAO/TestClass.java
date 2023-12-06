package DAO;

public class TestClass {
    public void test(){
        Greeter g = new Greeter();

        Person person = new Person("Amy");
        Person pirate = new Pirate("Bill");

        Person test = pirate;

        g.greet(person);
        g.greet(pirate);
        g.greet(test);
    }
}

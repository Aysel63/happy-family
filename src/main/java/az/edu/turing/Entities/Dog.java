package az.edu.turing.Entities;

public class Dog extends Pet implements Foulable{

    @Override
    public String respond() {
        return "Woof! I am a dog.";
    }

    @Override
    public String foul() {
        return "I have dug a hole in the garden!";
    }
}

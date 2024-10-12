package az.edu.turing.Entities;

public class DomesticCat extends Pet implements Foulable{

    @Override
    public String foul() {
        return "I have made a mess!";
    }

    @Override
    public String respond() {
        return "Meow! I am a domestic cat.";
    }
}

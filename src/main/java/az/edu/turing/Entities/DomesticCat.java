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

    public DomesticCat() {
    }

    public DomesticCat(Species species, String nickname) {
        super(species, nickname);
    }

    public DomesticCat(Species species, String nickname, int age, int trickLevel, String[] habits) {
        super(species, nickname, age, trickLevel, habits);
    }
}

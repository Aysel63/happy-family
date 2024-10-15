package az.edu.turing.entities;

import java.util.Set;

public class DomesticCat extends Pet implements Foulable {

    public DomesticCat(String nickname) {
        super(Species.CAT, nickname);
    }

    public DomesticCat(String nickname, int age, int trickLevel, Set<String> habits) {
        super(Species.CAT, nickname, age, trickLevel, habits);
    }

    @Override
    public String foul() {
        return "I have made a mess!";
    }

    @Override
    public String respond() {
        return "Meow! I am a domestic cat.";
    }
}

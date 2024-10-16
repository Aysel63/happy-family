package az.edu.turing.Entities;

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

    public DomesticCat() {
    }

    public DomesticCat(Species species, String nickname) {
        super(species, nickname);
    }

    public DomesticCat(Species species, String nickname, int age, int trickLevel, String[] habits) {
        super(species, nickname, age, trickLevel, habits);
    }
}

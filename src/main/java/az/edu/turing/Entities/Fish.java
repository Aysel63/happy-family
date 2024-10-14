package az.edu.turing.Entities;

import java.util.Set;

public class Fish extends Pet {

    public Fish(String nickname) {
        super(Species.FISH, nickname);
    }

    public Fish(String nickname, int age, int trickLevel, Set<String> habits) {
        super(Species.FISH, nickname, age, trickLevel, habits);
    }

    @Override
    public String respond() {
        return "I am a fish. I swim around.";
    }
}

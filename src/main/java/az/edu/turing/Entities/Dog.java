package az.edu.turing.Entities;

import java.util.Set;

public class Dog extends Pet implements Foulable {

    public Dog(String nickname) {
        super(Species.DOG, nickname);
    }

    public Dog(String nickname, int age, int trickLevel, Set<String> habits) {
        super(Species.DOG, nickname, age, trickLevel, habits);
    }

    @Override
    public String respond() {
        return "Woof! I am a dog.";
    }

    @Override
    public String foul() {
        return "I have dug a hole in the garden!";
    }
}

package az.edu.turing.entity;

import java.util.Set;

public class RobotCat extends Pet {

    public RobotCat(String nickname) {
        super(Species.CAT, nickname);
    }

    public RobotCat(String nickname, int age, int trickLevel, Set<String> habits) {
        super(Species.CAT, nickname, age, trickLevel, habits);
    }

    @Override
    public String respond() {
        return "Beep! I am a RobotCat.";
    }

}

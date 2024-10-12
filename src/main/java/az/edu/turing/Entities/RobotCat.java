package az.edu.turing.Entities;

public class RobotCat extends Pet implements Foulable{

    @Override
    public String  respond() {
        return "Beep! I am a RoboCat.";
    }

    @Override
    public String foul() {
        return "I have malfunctioned and caused a mess!";
    }
}

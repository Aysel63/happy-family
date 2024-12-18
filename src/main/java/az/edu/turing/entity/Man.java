package az.edu.turing.entity;
import java.util.Map;

public class Man extends Human {

    public Man(String name, String surname, String birthDate) {
        super(name, surname, birthDate);
    }

    public Man(String name, String surname, String birthDate, int iq) {
        super(name, surname, birthDate, iq);
    }

    public Man(String name, String surname, String birthDate, int iq, Map<String, String> schedule, Family family) {
        super(name, surname, birthDate, iq, schedule, family);
    }

    @Override
    public String greetPets() {
        return super.greetPets() + ". Let's play football.";
    }

    public String repairCar() {
        return "Sorry, I am full today. I will repair my car.";
    }
}

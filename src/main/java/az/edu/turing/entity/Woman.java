package az.edu.turing.entity;

import java.time.LocalDate;
import java.util.Map;

public class Woman extends Human {

    public Woman(String name, String surname, String birthDate) {
        super(name, surname, birthDate);
    }

    public Woman(String name, String surname, String birthDate, int iq) {
        super(name, surname, birthDate, iq);
    }

    public Woman(String name, String surname, String birthDate, int iq, Map<String, String> schedule, Family family) {
        super(name, surname, birthDate, iq, schedule, family);
    }

    @Override
    public String greetPets() {
        return super.greetPets() + "Let's go for a walk.";
    }

    public String makeup() {
        return "Just a second. I have to complete my makeup...";
    }
}

package az.edu.turing.Entities;

import java.util.Map;

public class Man extends Human {

    public Man(String name, String surname, int year) {
        super(name, surname, year);
    }

    public Man(String name, String surname, int year, int iq, Map<String, String> schedule, Family family) {
        super(name, surname, year, iq, schedule, family);
    }


    @Override
    public String greetPet() {
        return super.greetPet() + ". Let's play football";
    }

    public String repairCar() {
        return "Sorry, I am full today. I will repair my car.";
    }
}

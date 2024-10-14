package az.edu.turing.Entities;

public class Woman extends Human {

    public Woman(String name, String surname, int year) {
        super(name, surname, year);
    }

    public Woman(String name, String surname, int year, int iq, String[][] schedule, Family family) {
        super(name, surname, year, iq, schedule, family);
    }

    @Override
    public String greetPets() {
        return super.greetPets() + "Let's go for a walk.";
    }

    public String makeup() {
        return "Just a second. I have to complete my makeup...";
    }
}

package az.edu.turing.entities;

import java.util.List;
import java.util.Objects;
import java.util.Map;
import java.util.stream.Collectors;

public class Human {

    private String name;
    private String surname;
    private long birthDate;
    private int iq;
    private Map<DayOfWeek, String> schedule;
    private Family family;


    public Human(String name, String surname, long birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public Human(String name, String surname, long birthDate, int iq, Map<DayOfWeek, String> schedule, Family family) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.iq = iq;
        this.schedule = schedule;
        this.family = family;
    }

    public String greetPets() {
        List<String> petNickNames = family.getPets().stream().map(Pet::getNickname).collect(Collectors.toList());
        String result = String.join(", ", petNickNames);
        return "Hello, " + result + ".";
    }

    public String describePets() {
        StringBuilder result = new StringBuilder();
        for (Pet pet : family.getPets()) {
            String slyLevel = pet.getTrickLevel() > 50 ? "very sly" : "almost not sly";
            result.append(String.format("I have a %s, it is %d years old, and it is %s.\n",
                    pet.getSpecies(), pet.getAge(), slyLevel));
        }
        return result.toString();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }

    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    public Map<DayOfWeek, String> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<DayOfWeek, String> schedule) {
        this.schedule = schedule;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Human human = (Human) object;
        return birthDate == human.birthDate && Objects.equals(name, human.name) && Objects.equals(surname, human.surname) && Objects.equals(family, human.family);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname,birthDate, family);
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", year=" + birthDate +
                ", iq=" + iq +
                ", schedule=" + schedule +
                '}';
    }
}


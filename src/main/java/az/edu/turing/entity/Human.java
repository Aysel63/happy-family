package az.edu.turing.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Human {

    private String name;
    private String surname;
    private long birthDate;
    private int iq;
    private Map<String, String> schedule;
    private Family family;

    public Human(String name, String surname, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = parseBirthDate(birthDate);
    }

    public Human(String name, String surname, String birthDate, int iq) {
        this.name = name;
        this.surname = surname;
        this.birthDate = parseBirthDate(LocalDate.parse(birthDate));
        this.iq = iq;
    }

    public Human(String name, String surname, LocalDate birthDate, int iq, Map<String, String> schedule, Family family) {
        this(name, surname, birthDate);
        this.iq = iq;
        this.schedule = schedule;
        this.family = family;
    }

    private long parseBirthDate(LocalDate birthDate) {
        return birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public String describeAge() {
        LocalDate birthDateLocal = LocalDate.ofEpochDay(birthDate);
        LocalDate currentDate = LocalDate.now();

        Period age = Period.between(birthDateLocal, currentDate);
        return String.format("%d years, %d months, and %d days", age.getYears(), age.getMonths(), age.getDays());
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

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Human object is being removed: " + this.getName() + " " + this.getSurname());

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

    public Map<String, String> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<String, String> schedule) {
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
        return Objects.hash(name, surname, birthDate, family);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDateLocal = Instant
                .ofEpochMilli(birthDate)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        String formattedBirthDate = birthDateLocal.format(formatter);

        return "Human{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + formattedBirthDate +
                ", iq=" + iq +
                ", schedule=" + schedule +
                '}';
    }

}


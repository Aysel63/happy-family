package az.edu.turing.entity;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public abstract class Pet {

    private Species species;
    private String nickname;
    private int age;
    private int trickLevel;
    private Set<String> habits;

    protected Pet() {
    }

    protected Pet(Species species, String nickname) {
        this.species = species;
        this.nickname = nickname;
    }

    protected Pet(Species species, String nickname, int age, int trickLevel, Set<String> habits) {
        this(species, nickname);
        this.age = age;
        this.trickLevel = trickLevel;
        this.habits = habits;
    }


    public String eat() {
        return "I am eating";
    }

    public abstract String respond();


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Pet pet = (Pet) object;
        return age == pet.age && trickLevel == pet.trickLevel && species == pet.species && Objects.equals(nickname, pet.nickname) && Objects.deepEquals(habits, pet.habits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(species, nickname, age, trickLevel, Arrays.hashCode(new Set[]{habits}));
    }

    @Override
    public String toString() {
        return species + "{" +
                "nickname='" + nickname + '\'' +
                ", age=" + age +
                ", trickLevel=" + trickLevel +
                ", habits=" + habits +
                '}';
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTrickLevel() {
        return trickLevel;
    }

    public void setTrickLevel(int trickLevel) {
        this.trickLevel = trickLevel;
    }

    public Set<String> getHabits() {
        return habits;
    }

    public void setHabits(Set<String> habits) {
        this.habits = habits;
    }
}


package az.edu.turing.entity;

import az.edu.turing.model.DataUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import java.util.Random;

;

public class Family implements HumanCreator {

    private Human mother;
    private Human father;
    private List<Human> children;
    private Set<Pet> pets;

    static {
        System.out.println("Family class is being loaded");
    }

    {
        System.out.println("A new family object is being created");
    }


    public Family() {
        this.children = new ArrayList<>();
        this.pets = new HashSet<>();
    }

    public Family(Human mother, Human father) {
        this();
        this.father = father;
        this.mother = mother;

        father.setFamily(this);
        mother.setFamily(this);
    }

    public Family(Human mother, Human father, Set<Pet> pets) {
        this(mother, father);
        this.pets = pets;
    }

    public Human addChild(Human child) {
        children.add(child);
        child.setFamily(this);
        return child;
    }

    public boolean deleteChild(int index) {
        if (index < 0 || index >= children.size()) {
            return false;
        }
        Human child = children.get(index);
        child.setFamily(null);
        children.remove(index);
        return true;
    }

    public boolean deleteChild(Human child) {
        return children.remove(child);
    }

    public int countFamily() {
        return 2 + children.size();
    }

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    public List<Human> getChildren() {
        return children;
    }

    public void setChildren(List<Human> children) {
        this.children = children;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Family family = (Family) object;
        return Objects.equals(mother, family.mother) && Objects.equals(father, family.father);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mother, father);
    }

    @Override
    public String toString() {
        return "Family{" +
                "mother=" + mother +
                ", father=" + father +
                ", children=" + children.toString() +
                ", pet=" + pets +
                '}';
    }

    @Override
    public Human bornChild() {
        Random random = new Random();

        boolean isBoy = random.nextBoolean();

        String[] boyNames = {"Muhammed", "Adil", "Elshan"};
        String[] girlNames = {"Aysel", "Sevda", "Fidan"};

        String name = isBoy ? boyNames[random.nextInt(boyNames.length)] : girlNames[random.nextInt(girlNames.length)];

        int averageIq = (mother.getIq() + father.getIq()) / 2;
        Human child;
        if (isBoy) {
            child = new Man(name, father.getName(), LocalDate.now().format(DataUtils.birthDateFormatter), averageIq, null, this);
        } else {
            child = new Woman(name, father.getName(), LocalDate.now().format(DataUtils.birthDateFormatter), averageIq, null, this);
        }

        addChild(child);
        return child;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Family object is being removed: " + this);
        super.finalize();

    }

    public String prettyFormat() {
        StringBuilder result = new StringBuilder();
        result.append("family:\n")
                .append("\tmother: ").append(mother.prettyFormat()).append(",\n")
                .append("\tfather: ").append(father.prettyFormat()).append(",\n")
                .append("\tchildren:\n");

        for (Human child : children) {
            String gender = child instanceof Man ? "boy" : "girl";
            result.append("\t\t").append(gender).append(": ").append(child.prettyFormat()).append("\n");
        }

        result.append("\tpets: ").append(pets.stream().map(Pet::prettyFormat).toList());

        return result.toString();
    }

}

package az.edu.turing.entity;

import java.util.*;

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

    public Family(Human father, Human mother) {
        this.father = father;
        this.mother = mother;

        father.setFamily(this);
        mother.setFamily(this);

        this.children = new ArrayList<>();
    }

    public Family(Human mother, Human father, Set<Pet> pets) {
        this.pets = pets != null ? pets : new HashSet<>();
        this.mother = mother;
        this.father = father;
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
            child = new Man(name, father.getName(), 2024, averageIq, null, this);
        } else {
            child = new Woman(name, father.getName(), 2024, averageIq, null, this);
        }

        addChild(child);
        return child;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Family object is being removed: " + this);

    }

}

package az.edu.turing.Entities;

import java.util.*;

public class Family implements HumanCreator {

    private Human mother;
    private Human father;
    private Human[] children;
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
        this.children = new Human[]{};
    }

    public Family(Set<Pet> pets, Human mother, Human father) {
        this.pets = pets != null ? pets : new HashSet<>();
        this.mother = mother;
        this.father = father;
    }

    public void addChild(Human child) {
        children = Arrays.copyOf(children, children.length + 1);
        child.setFamily(this);
        children[children.length - 1] = child;
    }

    public boolean deleteChild(int index) {
        if (index < 0 || index >= children.length) {
            return false;
        }
        Human child = children[index];
        child.setFamily(null);
        for (int i = index; i < children.length - 1; i++) {
            children[i] = children[i + 1];
        }
        children = Arrays.copyOf(children, children.length - 1);
        return true;
    }

    public boolean deleteChild(Human child) {
        for (int i = 0; i < children.length; i++) {
            if (children[i].equals(child)) {
                return deleteChild(i);
            }
        }
        return false;
    }

    public int countFamily() {
        return 2 + children.length;
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

    public Human[] getChildren() {
        return children;
    }

    public void setChildren(Human[] children) {
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
                ", children=" + Arrays.toString(children) +
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
}

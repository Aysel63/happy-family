package az.edu.turing.Entities;

public class Main {
    public static void main(String[] args) {

        Human mother = new Human("Sarah", "Johnson", 1975);
        Human father = new Human("Michael", "Johnson", 1972);
        String[] petHabits = {"playing", "eating", "sleeping"};
        Pet pet = new Pet("Cat", "Bella", 3, 60, petHabits);
        String[][] childSchedule = {
                {"Monday", "go to school"},
                {"Tuesday", "do homework"},
                {"Wednesday", "meet friends"},
                {"Thursday", "go shopping"},
                {"Friday", "visit grandparents"},
                {"Saturday", "play video games"},
                {"Sunday", "family day"}
        };
        Human child = new Human("Tom", "Johnson", 2004, 100, pet, mother, father, childSchedule);
        System.out.println(child);
        System.out.println(child.greetPet());
        System.out.println(child.describePet());


        System.out.println(pet.eat());
        System.out.println(pet.respond());
        System.out.println(pet.foul());


    }
}

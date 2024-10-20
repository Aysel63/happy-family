package az.edu.turing;

import az.edu.turing.service.FamilyService;
import az.edu.turing.controller.FamilyController;
import az.edu.turing.dao.FamilyDao;
import az.edu.turing.dao.impl.CollectionFamilyDao;
import az.edu.turing.entity.Dog;
import az.edu.turing.entity.DomesticCat;
import az.edu.turing.entity.Family;
import az.edu.turing.entity.Human;
import az.edu.turing.entity.Man;
import az.edu.turing.entity.Pet;
import az.edu.turing.entity.Woman;

public class Main {

    public static void main(String[] args) {

        FamilyDao familyDao = new CollectionFamilyDao();
        FamilyService familyService = new FamilyService(familyDao);
        FamilyController familyController = new FamilyController(familyService);

        Human mother1 = new Woman("Charlotte", "Edwards", "18/10/1984");
        Human father1 = new Man("William", "Edwards", "18/10/1984");
        Family family1 = familyController.createNewFamily(mother1, father1);
        familyController.bornChild(family1, "Henry", "Olivia");
        Pet pet1 = new Dog("Max");
        familyController.addPet(0, pet1);

        Human mother2 = new Woman("Nazrin", "Abdullayeva", "18/10/1984");
        Human father2 = new Man("Zaur", "Abdullayev", "18/10/1984");
        Family family2 = familyController.createNewFamily(mother2, father2);
        familyController.bornChild(family2, "Davud", "Sona");
        Human child2 = new Woman("Narmin", "Fataliyeva", "18/10/2007");
        familyController.adoptChild(family2, child2);
        Pet pet2 = new DomesticCat("Masdan");
        familyController.addPet(1, pet2);

        System.out.println(familyController.count());
        familyController.displayAllFamilies();
        System.out.println();

        System.out.println(familyController.getFamilyById(0));
        System.out.println(familyController.getFamilyById(1));
        System.out.println();

        System.out.println(familyController.getPets(1));
        System.out.println();

        System.out.println(familyController.getFamiliesLessThan(4));
        System.out.println(familyController.countFamiliesWithMemberNumber(4));
        System.out.println();

        familyController.deleteFamilyByIndex(0);
    }
}

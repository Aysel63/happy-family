package az.edu.turing;

import az.edu.turing.entity.*;
import az.edu.turing.controller.FamilyController;
import az.edu.turing.dao.FamilyDao;
import az.edu.turing.dao.impl.CollectionFamilyDao;
import az.edu.turing.service.FamilyService;

public class Main {
    public static void main(String[] args) {

        FamilyDao familyDao = new CollectionFamilyDao();
        FamilyService familyService = new FamilyService(familyDao);
        FamilyController familyController = new FamilyController(familyService);

        Human mother1 = new Woman("Charlotte", "Edwards", 1984);
        Human father1 = new Man("William", "Edwards", 1982);
        Family family1 = familyController.createNewFamily(mother1, father1);
        familyController.bornChild(family1, "Henry", "Olivia");
        Pet pet1 = new Dog("Max");
        familyController.addPet(0, pet1);

        Human mother2 = new Woman("Nazrin", "Abdullayeva", 1986);
        Human father2 = new Man("Zaur", "Abdullayev", 1985);
        Family family2 = familyController.createNewFamily(father2, mother2);
        familyController.bornChild(family2, "Davud", "Abdullayev");
        Human child2 = new Woman("Narmin", "Fataliyeva", 2007);
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

        familyController.displayAllFamilies();
    }
}

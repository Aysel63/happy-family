package az.edu.turing;

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
import az.edu.turing.model.DataUtils;
import az.edu.turing.service.FamilyService;

import java.time.LocalDate;
import java.util.Scanner;

public class Console {

    private final FamilyDao familyDao = new CollectionFamilyDao();
    private final FamilyService familyService = new FamilyService(familyDao);
    private final FamilyController familyController = new FamilyController(familyService);

    public void start() {
        Scanner sc = new Scanner(System.in);

        outerLoop:
        while (true) {
            System.out.print("Enter command: ");
            String command = sc.nextLine();
            switch (command) {
                case "exit":
                    break outerLoop;
                case "1":
                    fillWithData();
                    break;
                case "2":
                    familyController.displayAllFamilies();
                    break;
                case "3":
                    familyController.getFamiliesBiggerThan(getNumber()).forEach(family -> System.out.println(family.prettyFormat()));
                    break;
                case "4":
                    familyController.getFamiliesLessThan(getNumber()).forEach(family -> System.out.println(family.prettyFormat()));
                    break;
                case "5":
                    System.out.println(familyController.countFamiliesWithMemberNumber(getNumber()).size());
                    break;
                case "6":
                    createNewFamily();
                    break;
                case "7":
                    System.out.println("Enter the id to delete family: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    familyController.deleteFamilyByIndex(id);
                    break;
                case "8":
                    System.out.print("Enter second command: ");
                    String c = sc.nextLine();
                    switch (c) {
                        case "1":
                            birthBaby();
                            break;
                        case "2":
                            adoptBaby();
                        default:
                            System.out.println("Invalid command");
                    }
                case "9":
                    System.out.println("Enter the age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    familyController.getAllFamilies().forEach(family -> familyController.deleteAllChildrenOlderThen(age));
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private int getNumber() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number: ");
        return sc.nextInt();
    }

    private void adoptBaby() {
        Scanner sc = new Scanner(System.in);
        int familyId = getFamilyId();

        System.out.println("Enter the full name of the child: ");
        String fullName = sc.nextLine();
        String[] names = fullName.split(" ");
        String name = names[0];
        String lastName = names[1];
        String birthDate = getBirthDate("child");
        int iq = getIq("child");

        Human adoptedChild = new Human(name, lastName, birthDate, iq);

        familyController.adoptChild(familyController.getFamilyById(familyId), adoptedChild);
    }


    private void birthBaby() {
        Scanner sc = new Scanner(System.in);

        int familyId = getFamilyId();

        System.out.println("If it is boy, what will be hos name?");
        String boyName = sc.nextLine();
        System.out.println("If it is girl, what will be her name?");
        String girlName = sc.nextLine();

        Family family = familyController.getFamilyById(familyId);
        familyController.bornChild(family, boyName, girlName);
    }

    private int getFamilyId() {
        Scanner sc = new Scanner(System.in);

        int familyId;
        while (true) {
            System.out.print("Please enter family id: ");
            familyId = sc.nextInt();
            if (familyId < 0 || familyId >= familyController.count()) {
                System.out.println("Invalid family id!");
                continue;
            }
            break;
        }
        return familyId;
    }

    private void createNewFamily() {
        System.out.println("Let's create a new family.");

        Human mother = requestMemberFields("mother");
        Human father = requestMemberFields("father");

        Family family = new Family(mother, father);

        mother.setFamily(family);
        father.setFamily(family);

        familyDao.saveFamily(family);
    }

    private Human requestMemberFields(String member) {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Please enter %s's name: ", member);
        String memberName = sc.nextLine();
        System.out.printf("Please enter %s's last name: ", member);
        String memberLastName = sc.nextLine();
        String memberBirthDate = getBirthDate(member);
        int iq = getIq(member);

        if (member.equals("mother")) {
            return new Woman(memberName, memberLastName, memberBirthDate, iq);
        } else if (member.equals("father")) {
            return new Man(memberName, memberLastName, memberBirthDate, iq);
        }
        return new Human(memberName, memberLastName, memberBirthDate, iq);
    }

    private int getIq(String member) {
        Scanner sc = new Scanner(System.in);
        int iq;
        while (true) {
            System.out.printf("Please enter %s's iq (1 to 100): ", member);
            iq = sc.nextInt();
            if (iq < 1 || iq > 100) {
                System.out.println("Invalid iq. Try again.");
                continue;
            }
            break;
        }
        return iq;
    }

    private String getBirthDate(String member) {
        Scanner sc = new Scanner(System.in);
        String memberBirthDate;
        while (true) {
            System.out.printf("Please enter birthDate (dd/MM/yyyy): ", member);
            memberBirthDate = sc.nextLine();
            try {
                LocalDate date = LocalDate.parse(memberBirthDate, DataUtils.birthDateFormatter);
                if (date.isAfter(LocalDate.now())) {
                    System.out.println("Enter valid birth date.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid birth date format. Try again.");
            }
        }
        return memberBirthDate;
    }

    private void fillWithData() {
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
    }
}

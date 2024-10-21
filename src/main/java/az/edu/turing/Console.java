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
import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

    private final FamilyDao familyDao = new CollectionFamilyDao();
    private final FamilyService familyService = new FamilyService(familyDao);
    private final FamilyController familyController = new FamilyController(familyService);
    private final Scanner sc = new Scanner(System.in);

    public void start() {
       boolean running=true;
        while (running) {
            int command=getValidNumber("Enter a command: ");
            switch (command) {
                case 1:
                    fillWithData();
                    break;
                case 2:
                    familyController.displayAllFamilies();
                    break;
                case 3:
                    int n = getValidNumber("Enter number: ");
                    familyController.getFamiliesBiggerThan(n).forEach(family -> System.out.println(family.prettyFormat()));
                    break;
                case 4:
                    int number1 = getValidNumber("Enter number: ");
                    familyController.getFamiliesLessThan(number1).forEach(family -> System.out.println(family.prettyFormat()));
                    break;
                case 5:
                    int number = getValidNumber("Enter number: ");
                    System.out.println(familyController.countFamiliesWithMemberNumber(number).size());
                    break;
                case 6:
                    createNewFamily();
                    break;
                case 7:
                    int id = getValidNumber("Enter the id: ");
                    familyController.deleteFamilyByIndex(id);
                    break;
                case 8:
                    editFamilyByIndex();
                    break;
                case 9:
                    int age = getValidNumber("Enter the age:");
                    familyController.getAllFamilies().forEach(family -> familyController.deleteAllChildrenOlderThen(age));
                    break;
                case 10:
                    running=false;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private int getValidNumber(String message) {
        while (true){
            System.out.println(message);
            try{return sc.nextInt();
        }catch (InputMismatchException e){
                System.out.print("Input must be integer!!! Try again: ");
                sc.nextLine();
            }
    }}

    private void editFamilyByIndex(){
        int index=getValidNumber("Enter family index: ")-1;
        Family family=familyService.getFamilyById(index);
        if(family==null){
            System.out.println("Family not found");
            return;
        }
        int option=getValidNumber("Enter the option: ");
        switch (option){
            case 1:
                birthBaby();
            case 2:
                adoptBaby();
            case 3:
                System.out.println("Invalid option.");
        }

    }

    private void adoptBaby() {
        int familyId = getFamilyId();
        if(familyId<0){
            System.out.println("Invalid family Id");
            return;
        }
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
        int familyId = getFamilyId();

        System.out.println("If it is boy, what will be his name?");
        String boyName = sc.nextLine();
        System.out.println("If it is girl, what will be her name?");
        String girlName = sc.nextLine();

        Family family = familyController.getFamilyById(familyId);
        familyController.bornChild(family, boyName, girlName);
    }

    private int getFamilyId() {
        int familyId;
        while (true) {
            familyId = getValidNumber("Please enter family id: ");
            System.out.println(familyController.count());
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

        Human mother = requestMemberFields(false);
        Human father = requestMemberFields(true);

        Family family = new Family(mother, father);

        mother.setFamily(family);
        father.setFamily(family);

        familyDao.saveFamily(family);
    }

    private Human requestMemberFields(boolean isMale) {
        System.out.printf("Please enter %s's name: ", isMale ? "male" : "female");
        String memberName = sc.nextLine();
        System.out.printf("Please enter %s's last name: ", isMale ? "male" : "female");
        String memberLastName = sc.nextLine();
        String memberBirthDate = getBirthDate(isMale ? "male" : "female");
        int iq = getIq(isMale ? "male" : "female");

        if (isMale) {
            return new Man(memberName, memberLastName, memberBirthDate, iq);
        } else {
            return new Woman(memberName, memberLastName, memberBirthDate, iq);
        }
    }

    private int getIq(String member) {
        int iq;
        while (true) {
            iq = getValidNumber("Please enter %s's iq (1 to 100): ");
            if (iq > 1 & iq <100) {
                return iq;
            }
            System.out.println("Invalid iq. Try again.");

        }
    }

    private String getBirthDate(String member) {
        String memberBirthDate;
        while (true) {
            System.out.printf("Please enter %s's birthDate (dd/MM/yyyy): ", member);
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

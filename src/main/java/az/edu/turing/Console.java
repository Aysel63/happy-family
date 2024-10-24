package az.edu.turing;

import az.edu.turing.controller.FamilyController;
import az.edu.turing.entity.Dog;
import az.edu.turing.entity.DomesticCat;
import az.edu.turing.entity.Family;
import az.edu.turing.entity.Human;
import az.edu.turing.entity.Man;
import az.edu.turing.entity.Pet;
import az.edu.turing.entity.Woman;
import az.edu.turing.model.constant.AppConstant;

import java.time.LocalDate;
import java.util.Scanner;

public class Console {

    private final FamilyController familyController;
    private final Scanner sc = new Scanner(System.in);

    public Console(FamilyController familyController) {
        this.familyController = familyController;
    }

    public void start() {
        displayCommandList();
        while (true) {
            System.out.print("Enter a command: ");
            String command = sc.nextLine();
            switch (command) {
                case "exit":
                    return;
                case "1":
//                    fillWithData();
                    familyController.loadDataFromFile();
                    break;
                case "2":
                    familyController.displayAllFamilies();
                    break;
                case "3":
                    familyController.getFamiliesBiggerThan(getValidNumber("Enter number: "))
                            .forEach(family -> System.out.println(family.prettyFormat()));
                    break;
                case "4":
                    familyController.getFamiliesLessThan(getValidNumber("Enter number: "))
                            .forEach(family -> System.out.println(family.prettyFormat()));
                    break;
                case "5":
                    System.out.println(familyController
                            .countFamiliesWithMemberNumber(getValidNumber("Enter number: ")).size());
                    break;
                case "6":
                    createNewFamily();
                    break;
                case "7":
                    familyController.deleteFamilyByIndex(getFamilyId());
                    break;
                case "8":
                    editFamilyByIndex();
                    break;
                case "9":
                    familyController.getAllFamilies()
                            .forEach(family -> familyController
                                    .deleteAllChildrenOlderThen(getValidNumber("Enter the age:")));
                    break;
                case "10":
                    familyController.saveDataToFile();
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private int getValidNumber(String message) {
        System.out.print(message);
        int number;
        while (!sc.hasNextInt()) {
            System.out.print("Input must be integer!!! Try again: ");
            sc.nextLine();
        }
        number = sc.nextInt();
        sc.nextLine();
        return number;
    }

    private void editFamilyByIndex() {
        while (true) {
            System.out.print("Enter the option: ");
            String option = sc.nextLine();
            switch (option) {
                case "1":
                    birthBaby();
                    break;
                case "2":
                    adoptBaby();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void adoptBaby() {
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
            familyId = getValidNumber("Please enter family id: ") - 1;
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

        familyController.saveFamily(family);
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
        int iq;
        while (true) {
            iq = getValidNumber(String.format("Please enter %s's iq (1 to 100): ", member));
            if (iq >= 1 && iq <= 100) {
                return iq;
            }
            System.out.println("Invalid iq. Try again.");
        }
    }

    private String getBirthDate(String member) {
        String memberBirthDate;
        while (true) {
            System.out.printf("Please enter %s's birthDate.\n", member);

            int year = getValidNumber("Enter year: ");
            int month = getValidNumber("Enter month: ");
            int day = getValidNumber("Enter day: ");

            try {
                LocalDate date = LocalDate.of(year, month, day);
                if (date.isAfter(LocalDate.now())) {
                    System.out.println("Enter valid birth date.");
                    continue;
                }
                memberBirthDate = date.format(AppConstant.BIRTH_DATE_FORMATTER);
                break;
            } catch (Exception e) {
                System.out.println("Invalid birth date. Try again.");
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


    private void displayCommandList() {
        System.out.println("Choose a command:\n" +
                "1. Load data\n" +
                "2. Display the entire list of families\n" +
                "3. Display families bigger than a specified number\n" +
                "4. Display families less than a specified number\n" +
                "5. Count families with a specific number of members\n" +
                "6. Create a new family\n" +
                "7. Delete a family by index\n" +
                "8. Edit a family by index\n" +
                "9. Remove all children over the age of majority\n" +
                "10. Save data\n" +
                "'exit' to exit");
    }
}

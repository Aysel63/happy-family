package az.edu.turing.business;

import az.edu.turing.dao.FamilyDao;
import az.edu.turing.entities.Family;
import az.edu.turing.entities.Human;
import az.edu.turing.entities.Pet;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FamilyService {

    private final FamilyDao familyDao;

    public FamilyService(FamilyDao familyDao) {
        this.familyDao = familyDao;
    }

    public List<Family> getAllFamilies() {
        return familyDao.getAllFamilies();
    }

    public void displayAllFamilies() {
        List<Family> families = getAllFamilies();
        for (int i = 0; i < families.size(); i++) {
            System.out.println(i + 1 + ". " + families.get(i));
        }
    }

    public List<Family> getFamiliesBiggerThan(int numberOfPeople) {
        return getAllFamilies()
                .stream()
                .filter(family -> family.countFamily() > numberOfPeople)
                .collect(Collectors.toList());
    }

    public List<Family> getFamiliesLessThan(int numberOfPeople) {
        return getAllFamilies()
                .stream()
                .filter(family -> family.countFamily() < numberOfPeople)
                .collect(Collectors.toList());
    }

    public List<Family> countFamiliesWithMemberNumber(int numberOfPeople) {
        return getAllFamilies()
                .stream()
                .filter(family -> family.countFamily() == numberOfPeople)
                .collect(Collectors.toList());
    }

    public Family createNewFamily(Human father, Human mother) {
        if (father == null || mother == null) throw new NullPointerException("father and mother cannot be null");
        Family family = new Family(father, mother);
        familyDao.saveFamily(family);
        return family;
    }

    public boolean deleteFamilyByIndex(int index) {
        return familyDao.deleteFamily(index);
    }

    public Family bornChild(Family family, String masculineName, String feminineName) {
        String childName = (Math.random() < 0.5) ? masculineName : feminineName;
        long birthYear = Calendar.getInstance().get(Calendar.YEAR);
        Human child = new Human(childName, family.getFather().getSurname(), birthYear);
        family.addChild(child);
        familyDao.saveFamily(family);
        return family;
    }

    public Family adoptChild(Family family, Human child) {
        family.addChild(child);
        familyDao.saveFamily(family);
        return family;
    }

    public void deleteAllChildrenOlderThen(int age) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<Family> allFamilies = familyDao.getAllFamilies();
        allFamilies.forEach(family -> {
            family.getChildren().removeIf(child -> (currentYear - child.getBirthDate()) > age);
            familyDao.saveFamily(family);
        });
    }

    public int count() {
        return familyDao.getAllFamilies().size();
    }

    public Family getFamilyById(int index) {
        List<Family> allFamilies = familyDao.getAllFamilies();
        if (index < 0 || index >= allFamilies.size())
            throw new IndexOutOfBoundsException("Invalid family index: " + index);
        return allFamilies.get(index);
    }

    public Set<Pet> getPets(int familyIndex) {
        Family family = getFamilyById(familyIndex);
        if (family != null) {
            Set<Pet> pets = family.getPets();
            return pets != null ? new HashSet<>(pets) : new HashSet<>();
        }
        return new HashSet<>();
    }

    public void addPet(int familyIndex, Pet pet) {
        Family family = getFamilyById(familyIndex);
        Set<Pet> pets = family.getPets();
        if (pets == null) {
            pets = new HashSet<>();
        }
        pets.add(pet);
        family.setPets(pets);
        familyDao.saveFamily(family);
    }
}

package az.edu.turing.service;

import az.edu.turing.dao.FamilyDao;
import az.edu.turing.entity.Man;
import az.edu.turing.entity.Woman;
import az.edu.turing.model.DataUtils;
import az.edu.turing.entity.Family;
import az.edu.turing.entity.Human;
import az.edu.turing.entity.Pet;
import az.edu.turing.exception.BadRequestException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        IntStream.range(0, count()).forEach(i -> System.out.println((i + 1) + ". " + families.get(i).prettyFormat()));

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

    public Family createNewFamily(Human mother, Human father) {
        if (mother == null || father == null) throw new BadRequestException("father and mother cannot be null");
        Family family = new Family(mother, father);
        familyDao.saveFamily(family);
        return family;
    }

    public boolean deleteFamilyByIndex(int index) {
        return familyDao.deleteFamily(index);
    }

    public Family bornChild(Family family, String masculineName, String feminineName) {
        boolean isBoy = Math.random() < 0.5;
        String childName = isBoy ? masculineName : feminineName;

        Human child;
        if (isBoy) child = new Man(childName,
                family.getFather().getSurname(),
                LocalDate.now().format(DataUtils.birthDateFormatter));
        else child = new Woman(childName,
                family.getFather().getSurname(),
                LocalDate.now().format(DataUtils.birthDateFormatter));

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
        List<Family> allFamilies = familyDao.getAllFamilies();
        allFamilies.forEach(family -> {
            family.getChildren().removeIf(child -> {
                LocalDate birthDate = Instant.ofEpochMilli(child.getBirthDate()).atZone(ZoneId.systemDefault()).toLocalDate();
                int childAge = Period.between(birthDate, LocalDate.now()).getYears();
                return childAge > age;
            });

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
            return new HashSet<>(pets);
        }
        return new HashSet<>();
    }

    public void addPet(int familyIndex, Pet pet) {
        Family family = getFamilyById(familyIndex);
        Set<Pet> pets = family.getPets();
        pets.add(pet);
        family.setPets(pets);
        familyDao.saveFamily(family);
    }
}

package az.edu.turing.controller;

import az.edu.turing.exception.FamilyOverflowException;
import az.edu.turing.service.FamilyService;
import az.edu.turing.entity.Family;
import az.edu.turing.entity.Human;
import az.edu.turing.entity.Pet;

import java.util.List;
import java.util.Set;

public class FamilyController {

    private final FamilyService familyService;
    private static final int FAMILY_SIZE_LIMIT = 5;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    public void saveFamily(Family family) {
        familyService.saveFamily(family);
    }

    public List<Family> getAllFamilies() {
        return familyService.getAllFamilies();
    }

    public void displayAllFamilies() {
        familyService.displayAllFamilies();
    }

    public List<Family> getFamiliesBiggerThan(int numberOfPeople) {
        return familyService.getFamiliesBiggerThan(numberOfPeople);
    }

    public List<Family> getFamiliesLessThan(int numberOfPeople) {
        return familyService.getFamiliesLessThan(numberOfPeople);
    }

    public List<Family> countFamiliesWithMemberNumber(int numberOfPeople) {
        return familyService.countFamiliesWithMemberNumber(numberOfPeople);
    }

    public Family createNewFamily(Human mother, Human father) {
        return familyService.createNewFamily(mother, father);
    }

    public boolean deleteFamilyByIndex(int index) {
        return familyService.deleteFamilyByIndex(index);
    }

    public Family bornChild(Family family, String masculineName, String feminineName) {
        if (family.countFamily() >= FAMILY_SIZE_LIMIT) {
            throw new FamilyOverflowException("Reached the maximum number of family members! Cannot have more than "
                    + FAMILY_SIZE_LIMIT + " members.");
        }
        return familyService.bornChild(family, masculineName, feminineName);
    }

    public Family adoptChild(Family family, Human child) {
        if (family.countFamily() >= FAMILY_SIZE_LIMIT) {
            throw new FamilyOverflowException("Reached the maximum number of family members! Cannot adopt more members.");
        }
        return familyService.adoptChild(family, child);
    }

    public void deleteAllChildrenOlderThen(int age) {
        familyService.deleteAllChildrenOlderThen(age);
    }

    public int count() {
        return familyService.count();
    }

    public Family getFamilyById(int index) {
        return familyService.getFamilyById(index);
    }

    public Set<Pet> getPets(int familyIndex) {
        return familyService.getPets(familyIndex);
    }

    public void addPet(int familyIndex, Pet pet) {
        familyService.addPet(familyIndex, pet);
    }
}


package az.edu.turing.business;

import az.edu.turing.dao.FamilyDao;
import az.edu.turing.entities.Family;
import az.edu.turing.entities.Human;

import java.util.List;
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

    public void createNewFamily(Human father, Human mother) {
        if (father == null || mother == null) throw new NullPointerException("father and mother cannot be null");
        familyDao.saveFamily(new Family(father, mother));
    }

    public boolean deleteFamilyByIndex(int index) {
        return familyDao.deleteFamily(index);
    }


}

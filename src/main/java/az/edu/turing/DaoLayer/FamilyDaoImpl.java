package az.edu.turing.DaoLayer;

import az.edu.turing.Entities.Family;

import java.util.ArrayList;
import java.util.List;

public class FamilyDaoImpl implements  FamilyDao{

    private List<Family> familyDatabase = new ArrayList<>();

    @Override
    public List<Family> getAllFamilies() {
        return new ArrayList<>(familyDatabase);
    }

    @Override
    public Family getFamilyByIndex(int index) {
        if (index < 0 || index >= familyDatabase.size()) {
            return null;
        }
        return familyDatabase.get(index);
    }

    @Override
    public boolean deleteFamily(int index) {
        if (index < 0 || index >= familyDatabase.size()) {
            return false;
        }
        familyDatabase.remove(index);
        return true;
    }

    @Override
    public boolean deleteFamily(Family family) {
        return familyDatabase.remove(family);
    }

    @Override
    public void saveFamily(Family family) {
        int existingIndex = familyDatabase.indexOf(family);
        if (existingIndex != -1) {
            familyDatabase.set(existingIndex, family);
        } else {
            familyDatabase.add(family);
        }
    }

}

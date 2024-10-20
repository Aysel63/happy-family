package az.edu.turing.dao.impl;

import az.edu.turing.dao.FamilyDao;
import az.edu.turing.entity.Family;

import java.util.ArrayList;
import java.util.List;

public class CollectionFamilyDao implements FamilyDao {

    private final List<Family> families;

    public CollectionFamilyDao() {
        this.families = new ArrayList<>();
    }

    @Override
    public List<Family> getAllFamilies() {
        return new ArrayList<>(families);
    }

    @Override
    public Family getFamilyByIndex(final int index) {
        if (index < 0 || index >= families.size()) {
            return null;
        }
        return families.get(index);
    }

    @Override
    public boolean deleteFamily(final int index) {
        if (index < 0 || index >= families.size()) {
            return false;
        }
        families.remove(index);
        return true;
    }

    @Override
    public boolean deleteFamily(Family family) {
        return families.remove(family);
    }

    @Override
    public void saveFamily(Family family) {
        int index = families.indexOf(family);
        if (index != -1) {
            families.set(index, family);
        } else {
            families.add(family);
        }
    }
}

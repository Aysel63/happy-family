package az.edu.turing.dao.impl;

import az.edu.turing.dao.FamilyDao;
import az.edu.turing.entity.Family;
import az.edu.turing.service.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CollectionFamilyDao implements FamilyDao {

    private List<Family> families;

    private static final String RESOURCE_PATH = "C:\\Users\\Support\\Documents\\Java\\Turing\\happy-family\\src\\main\\java\\resource\\";
    private static final String FAMILIES_FILE_PATH = RESOURCE_PATH.concat("families.txt");

    public CollectionFamilyDao() {
        this.families = new ArrayList<>();
    }

    @Override
    public List<Family> getAllFamilies() {
        Logger.info("receiving a family list");
        return new ArrayList<>(families);
    }

    @Override
    public Family getFamilyByIndex(final int index) {
        if (index < 0 || index >= families.size()) {
            return null;
        }
        Logger.info("receiving a family by index" + index);
        return families.get(index);
    }

    @Override
    public boolean deleteFamily(final int index) {
        if (index < 0 || index >= families.size()) {
            return false;
        }
        families.remove(index);
        Logger.info("removing a family by index" + index);
        return true;
    }

    @Override
    public boolean deleteFamily(Family family) {
        Logger.info("removing a family by object");
        return families.remove(family);
    }

    @Override
    public void saveFamily(Family family) {
        int index = families.indexOf(family);
        if (index != -1) {
            families.set(index, family);
            Logger.info("updated family");
        } else {
            families.add(family);
            Logger.info("saving family");
        }
    }

    @Override
    public void saveDataToFile() throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FAMILIES_FILE_PATH))) {
            objectOutputStream.writeObject(families);
        }
        Logger.info("saved all families to file");
    }

    @Override
    public void loadDataFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FAMILIES_FILE_PATH))) {
            families = (List<Family>) objectInputStream.readObject();
        }
        Logger.info("loaded all families from file");
    }
}

package az.edu.turing.dao.impl;

import az.edu.turing.dao.FamilyDao;
import az.edu.turing.entity.Family;
import az.edu.turing.service.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CollectionFamilyDao implements FamilyDao {

    private List<Family> families;

    private static final Path RESOURCE_PATH = Paths.get("src/main/java/resource/");
    private static final Path FAMILIES_FILE_PATH = RESOURCE_PATH.resolve("families.txt");

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
        Logger.info("deleting a family" + index);
        return true;
    }

    @Override
    public boolean deleteFamily(Family family) {
        Logger.info("deleting a family");
        return families.remove(family);
    }

    @Override
    public void saveFamily(Family family) {
        int index = families.indexOf(family);
        if (index != -1) {
            families.set(index, family);
            Logger.info("updating family");
        } else {
            families.add(family);
            Logger.info("saving family");
        }
    }

    @Override
    public void saveDataToFile() throws IOException {
        try (ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(new FileOutputStream(FAMILIES_FILE_PATH.toString()))) {
            objectOutputStream.writeObject(families);
        }
        Logger.info("saving all families to file");
    }

    @Override
    public void loadDataFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream =
                     new ObjectInputStream(new FileInputStream(FAMILIES_FILE_PATH.toString()))) {
            families = (List<Family>) objectInputStream.readObject();
        }
        Logger.info("loading all families from file");
    }
}

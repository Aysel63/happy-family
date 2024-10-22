package az.edu.turing.dao.impl;
import az.edu.turing.dao.FamilyDao;
import az.edu.turing.entity.Family;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CollectionFamilyDao implements FamilyDao {

    private List<Family> families;

    private static final String RESOURCE_PATH = "src/main/java/az/edu/turing/resource/";
    private static final String FAMILIES_FILE_PATH = RESOURCE_PATH.concat("families.txt");

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

    @Override
    public void saveDataToFile() throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FAMILIES_FILE_PATH))) {
            objectOutputStream.writeObject(families);
        }
    }

    @Override
    public void loadDataFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FAMILIES_FILE_PATH))) {
            families = (List<Family>) objectInputStream.readObject();
        }
    }
}

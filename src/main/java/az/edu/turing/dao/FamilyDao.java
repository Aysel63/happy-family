package az.edu.turing.dao;

import az.edu.turing.entity.Family;

import java.io.IOException;
import java.util.List;

public interface FamilyDao {

    List<Family> getAllFamilies();

    Family getFamilyByIndex(int index);

    boolean deleteFamily(int index);

    boolean deleteFamily(Family family);

    void saveFamily(Family family);

    void saveDataToFile() throws IOException;

    void loadDataFromFile() throws IOException, ClassNotFoundException;
}

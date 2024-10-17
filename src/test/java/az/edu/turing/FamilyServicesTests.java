package az.edu.turing;

import az.edu.turing.business.FamilyService;
import az.edu.turing.dao.FamilyDao;
import az.edu.turing.dao.impl.CollectionFamilyDao;
import az.edu.turing.entity.Dog;
import az.edu.turing.entity.Family;
import az.edu.turing.entity.Human;
import az.edu.turing.entity.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FamilyServicesTests {

    private FamilyDao familyDao;
    private FamilyService familyService;

    @BeforeEach
    void setup() {
        familyDao = new CollectionFamilyDao();
        familyService = new FamilyService(familyDao);

    }

    @Test
    void testGetAllFamilies() {
        Family family1 = new Family(new Human("Jane", "Thomson", 1982), new Human("Brad", "Thomson", 1980));
        Family family2 = new Family(new Human("Anna", "Smith", 1977), new Human("Tom", "Smith", 1975));
        familyDao.saveFamily(family1);
        familyDao.saveFamily(family2);
        List<Family> result = familyService.getAllFamilies();
        assertEquals(2, result.size());
        assertTrue(result.contains(family1));
        assertTrue(result.contains(family2));
    }

    @Test
    void testDisplayAllFamilies() {
        Family family1 = new Family(new Human("Anna", "Smith", 1982), new Human("Tom", "Smith", 1980));
        familyDao.saveFamily(family1);
        familyService.displayAllFamilies();
    }

    @Test
    void testGetFamiliesBiggerThan() {
        Family family1 = new Family(new Human("Jane", "Johnson", 1982), new Human("Tomas", "Johnson", 1980));
        family1.addChild(new Human("Alex", "Johnson", 2010));
        family1.addChild(new Human("Juliet", "Johnson", 2012));
        familyDao.saveFamily(family1);

        List<Family> result = familyService.getFamiliesBiggerThan(2);
        assertEquals(1, result.size());
        assertTrue(result.contains(family1));

    }

    @Test
    void testGetFamiliesLessThan() {
        Family family1 = new Family(new Human("Jane", "Johnson", 1982), new Human("Tomas", "Johnson", 1980));
        familyDao.saveFamily(family1);

        List<Family> result = familyService.getFamiliesLessThan(3);
        assertEquals(1, result.size());
        assertTrue(result.contains(family1));
    }

    @Test
    void testCountFamiliesWithMemberNumber() {
        Family family1 = new Family(new Human("Jane", "Johnson", 1982), new Human("Tomas", "Johnson", 1980));
        family1.addChild(new Human("Juliet", "Johnson", 2010));
        familyDao.saveFamily(family1);

        List<Family> result = familyService.countFamiliesWithMemberNumber(3);
        assertEquals(1, result.size());
        assertTrue(result.contains(family1));
    }

    @Test
    void testCreateNewFamily() {
        familyService.createNewFamily(new Human("Jane", "Johnson", 1982), new Human("Tomas", "Johnson", 1980));

        assertEquals(1, familyDao.getAllFamilies().size());
    }

    @Test
    void testDeleteFamilyByIndex() {
        Family family1 = new Family(new Human("Jane", "Johnson", 1982), new Human("Tomas", "Johnson", 1980));
        familyDao.saveFamily(family1);

        boolean isDeleted = familyService.deleteFamilyByIndex(0);
        assertTrue(isDeleted);
        assertEquals(0, familyDao.getAllFamilies().size());
    }

    @Test
    void testBornChild() {
        Family family = new Family(new Human("Tomas", "Johnson", 1980), new Human("Jane", "Johnson", 1982));
        familyDao.saveFamily(family);

        Family updatedFamily = familyService.bornChild(family, "BoyName", "GirlName");
        assertEquals(1, updatedFamily.getChildren().size());
    }

    @Test
    void testAdoptChild() {
        Family family = new Family(new Human("Jane", "Johnson", 1982), new Human("Tomas", "Johnson", 1980));
        familyDao.saveFamily(family);

        Human adoptedChild = new Human("Tom", "Johnson", 2015);
        Family updatedFamily = familyService.adoptChild(family, adoptedChild);

        assertEquals(1, updatedFamily.getChildren().size());
        assertTrue(updatedFamily.getChildren().contains(adoptedChild));
    }

    @Test
    void testDeleteAllChildrenOlderThen() {
        Family family = new Family(new Human("Jane", "Johnson", 1982), new Human("Tomas", "Johnson", 1980));
        family.addChild(new Human("Alex", "Johnson", 2000));
        family.addChild(new Human("Alice", "Johnson", 2010));
        familyDao.saveFamily(family);

        familyService.deleteAllChildrenOlderThen(18);
        assertEquals(1, family.getChildren().size());
    }

    @Test
    void testCountFamilies() {
        Family family1 = new Family(new Human("Jane", "Johnson", 1982), new Human("Tomas", "Johnson", 1980));
        familyDao.saveFamily(family1);

        assertEquals(1, familyService.count());
    }

    @Test
    void testGetFamilyById() {
        Family family1 = new Family(new Human("Jane", "Johnson", 1982), new Human("Tomas", "Johnson", 1980));
        familyDao.saveFamily(family1);

        Family result = familyService.getFamilyById(0);
        assertEquals(family1, result);
    }

    @Test
    void testGetPets() {
        Family family = new Family(new Human("Jane", "Johnson", 1982), new Human("Tomas", "Johnson", 1980));
        familyDao.saveFamily(family);
        Set<Pet> pets = familyService.getPets(0);
        assertEquals(0, pets.size());
        assertTrue(pets.isEmpty());
    }

    @Test
    void testAddPet() {
        Family family = new Family(new Human("Jane", "Johnson", 1982), new Human("Tomas", "Johnson", 1980));
        familyDao.saveFamily(family);

        Pet pet = new Dog("Rex");
        familyService.addPet(0, pet);

        assertEquals(1, familyService.getPets(0).size());
        assertTrue(familyService.getPets(0).contains(pet));
    }
}

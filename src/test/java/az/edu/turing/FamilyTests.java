package az.edu.turing;

import az.edu.turing.Entities.Family;
import az.edu.turing.Entities.Human;
import az.edu.turing.Entities.Pet;
import az.edu.turing.Entities.Species;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class FamilyTests {
    private Family family;
    private Human father;
    private Human mother;
    private Human child;

    @BeforeEach
    public void setup() {
        father = new Human("Jack", "Johnson", 1975);
        mother = new Human("Anna", "Johnson", 1977);
        child = new Human("Tom", "Johnson", 2005);
        family = new Family(father, mother);
        family.addChild(child);
        Pet pet = new Pet(Species.DOG, "Buddy");
        family.setPet(pet);
    }

    @Test
    void deleteChild_ShouldDeleteChild_Success() {
        boolean result = family.deleteChild(child);
        assertTrue(result);
        assertEquals(0, family.getChildren().length);
    }

    @Test
    void deleteChild_ChildNotFound() {
        Human nonExistentChild = new Human("Carl", "Williams", 2000);
        boolean result = family.deleteChild(nonExistentChild);
        assertFalse(result);
        assertEquals(1, family.getChildren().length);

    }

    @Test
    void deleteChild_ValidIndex() {
        boolean result = family.deleteChild(0);
        assertTrue(result);
        assertEquals(0, family.getChildren().length);
    }

    @Test
    void deleteChild_InValidIndex() {
        boolean result = family.deleteChild(1);
        assertFalse(result);
        assertEquals(1, family.getChildren().length);
    }

    @Test
    void testToString() {
        String expected = "Family{" +
                "mother=Human{name='Anna', surname='Johnson', year=1977, iq=0, schedule=null}, " +
                "father=Human{name='Jack', surname='Johnson', year=1975, iq=0, schedule=null}, " +
                "children=[Human{name='Tom', surname='Johnson', year=2005, iq=0, schedule=null}], " +
                "pet=DOG{nickname='Buddy', age=0, trickLevel=0, habits=no habits}}";
        assertEquals(expected, family.toString());
    }

}



package az.edu.turing.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FamilyTests {
    private Family family;
    private Human father;
    private Human mother;
    private Human child;

    @BeforeEach
    void setup() {
        mother = new Human("Anna", "Johnson", "18/10/1977");
        father = new Human("Jack", "Johnson", "18/10/1975");
        child = new Human("Tom", "Johnson", "18/10/2005");
        family = new Family(mother, father);
        family.addChild(child);
    }

    @Test
    void deleteChild_ShouldDeleteChild_Success() {
        boolean result = family.deleteChild(child);
        assertTrue(result);
        assertEquals(0, family.getChildren().size());
    }

    @Test
    void deleteChild_ChildNotFound() {
        Human nonExistentChild = new Human("Carl", "Williams", "18/10/2000");
        boolean result = family.deleteChild(nonExistentChild);
        assertFalse(result);
        assertEquals(1, family.getChildren().size());
    }

    @Test
    void deleteChild_ValidIndex() {
        boolean result = family.deleteChild(0);
        assertTrue(result);
        assertEquals(0, family.getChildren().size());
    }

    @Test
    void deleteChild_InValidIndex() {
        boolean result = family.deleteChild(1);
        assertFalse(result);
        assertEquals(1, family.getChildren().size());
    }

    @Test
    void testToString() {
        String expected = "Family{" +
                "mother=Human{name='Anna', surname='Johnson', birthDate=18/10/1977, iq=0, schedule=null}, " +
                "father=Human{name='Jack', surname='Johnson', birthDate=18/10/1975, iq=0, schedule=null}, " +
                "children=[Human{name='Tom', surname='Johnson', birthDate=18/10/2005, iq=0, schedule=null}], " +
                "pet=[]}";
        assertEquals(expected, family.toString());
    }
}

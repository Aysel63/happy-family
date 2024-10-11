package az.edu.turing.Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    @Test
    void testEquals() {
        Pet pet1 = new Pet(Species.DOG, "Buddy", 3, 65, new String[]{});
        Pet pet2 = new Pet(Species.DOG, "Buddy", 3, 65, new String[]{});
        Pet pet3 = new Pet(Species.CAT, "Buddy", 3, 65, new String[]{});
        Pet pet4 = new Pet(Species.DOG, "Whiskers", 3, 65, new String[]{});
        Pet pet5 = new Pet(Species.DOG, "Buddy", 4, 65, new String[]{});
        Pet pet6 = new Pet(Species.DOG, "Buddy", 4, 70, new String[]{});

        assertEquals(pet1, pet2);
        assertEquals(pet2, pet1);

        assertNotEquals(pet1, pet3);
        assertNotEquals(pet1, pet4);
        assertNotEquals(pet1, pet5);
        assertNotEquals(pet1, pet5);
        assertNotEquals(pet1, pet6);

        assertNotEquals(null, pet1);
    }

    @Test
    void testHashCode() {
        Pet pet1 = new Pet(Species.DOG, "Buddy", 3, 65, new String[]{});
        Pet pet2 = new Pet(Species.DOG, "Buddy", 3, 65, new String[]{});
        assertEquals(pet1.hashCode(), pet2.hashCode());

        Pet pet3 = new Pet(Species.DOG, "Whiskers", 3, 65, new String[]{});
        assertNotEquals(pet1.hashCode(), pet3.hashCode());
    }
}
package az.edu.turing.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanTests {

    @Test
    void formatBirthDate() {
        Human human = new Human();
        human.setName("Bob");
        human.setName("Smith");

        human.setBirthDate(757368000000L);
        String result = human.formatBirthDate();
        assertEquals("01/01/1994", result);

        human.setBirthDate(760046400000L);
        result = human.formatBirthDate();
        assertEquals("01/02/1994", result);

        human.setBirthDate(757454400000L);
        result = human.formatBirthDate();
        assertEquals("02/01/1994", result);
    }
}
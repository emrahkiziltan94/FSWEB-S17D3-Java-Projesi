package com.workintech.zoo.entity;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KoalaTest {

    @Test
    void testKoalaAllArgsConstructor() {
        // Creating an instance using all-args constructor
        Koala koala = new Koala(1, "Kara", 20.0, 15.0, "Female");

        // Assertions to ensure fields are set correctly
        assertEquals(1, koala.getId());
        assertEquals("Kara", koala.getName());
        assertEquals(20.0, koala.getSleepHour());
        assertEquals(15.0, koala.getWeight());
        assertEquals("Female", koala.getGender());
    }

    @Test
    void testKoalaSettersAndGetters() {
        // Creating an instance using no-args constructor
        Koala koala = new Koala();
        koala.setId(2);
        koala.setName("Kody");
        koala.setSleepHour(22.0);
        koala.setWeight(12.5);
        koala.setGender("Male");

        // Assertions to check if setters worked through getters
        assertEquals(2, koala.getId(), "ID should match the set value.");
        assertEquals("Kody", koala.getName(), "Name should match the set value.");
        assertEquals(22.0, koala.getSleepHour(), "Sleep hour should match the set value.");
        assertEquals(12.5, koala.getWeight(), "Weight should match the set value.");
        assertEquals("Male", koala.getGender(), "Gender should match the set value.");
    }
}


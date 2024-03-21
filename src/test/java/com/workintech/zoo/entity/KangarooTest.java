package com.workintech.zoo.entity;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KangarooTest {

    @Test
    public void testKangarooCreationAndFieldAccess() {
        
        Kangaroo kangaroo = new Kangaroo(1, "Kenny", 2.0, 85.0, "Male", false);

        
        assertEquals(1, kangaroo.getId());
        assertEquals("Kenny", kangaroo.getName());
        assertEquals(2.0, kangaroo.getHeight());
        assertEquals(85.0, kangaroo.getWeight());
        assertEquals("Male", kangaroo.getGender());
        assertEquals(false, kangaroo.getIsAggressive());
    }

    @Test
    public void testKangarooSetters() {
        
        Kangaroo kangaroo = new Kangaroo();
        kangaroo.setId(2);
        kangaroo.setName("Kanga");
        kangaroo.setHeight(1.8);
        kangaroo.setWeight(70.0);
        kangaroo.setGender("Female");
        kangaroo.setIsAggressive(true);

        
        assertEquals(2, kangaroo.getId());
        assertEquals("Kanga", kangaroo.getName());
        assertEquals(1.8, kangaroo.getHeight());
        assertEquals(70.0, kangaroo.getWeight());
        assertEquals("Female", kangaroo.getGender());
        assertTrue(kangaroo.getIsAggressive());
    }
}


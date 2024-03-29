package com.workintech.zoo;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooErrorResponse;
import com.workintech.zoo.exceptions.ZooException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    @Test
    @DisplayName("Test Kangaroo Creation and Field Access")
     void testKangarooCreationAndFieldAccess() {

        Kangaroo kangaroo = new Kangaroo(1, "Kenny", 2.0, 85.0, "Male", false);


        assertEquals(1, kangaroo.getId());
        assertEquals("Kenny", kangaroo.getName());
        assertEquals(2.0, kangaroo.getHeight());
        assertEquals(85.0, kangaroo.getWeight());
        assertEquals("Male", kangaroo.getGender());
        assertEquals(false, kangaroo.getIsAggressive());
    }

    @Test
    @DisplayName("Test Kangaroo Setters")
    void testKangarooSetters() {

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

    @Test
    @DisplayName("Test Koala AllArgsConstructor")
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
    @DisplayName("Test Koala Setters and Getters")
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

    @Test
    @DisplayName("Test ZooErrorResponse NoArgsConstructor")
    void testNoArgsConstructor() {

        ZooErrorResponse errorResponse = new ZooErrorResponse();
        errorResponse.setStatus(400);
        errorResponse.setMessage("Bad Request");
        errorResponse.setTimestamp(System.currentTimeMillis());


        assertEquals(400, errorResponse.getStatus());
        assertEquals("Bad Request", errorResponse.getMessage());

    }

    @Test
    @DisplayName("Test ZooErrorResponse AllArgsConstructor")
     void testAllArgsConstructor() {

        long now = System.currentTimeMillis();


        ZooErrorResponse errorResponse = new ZooErrorResponse(404, "Not Found", now);


        assertEquals(404, errorResponse.getStatus());
        assertEquals("Not Found", errorResponse.getMessage());
        assertEquals(now, errorResponse.getTimestamp());
    }

    @Test
    @DisplayName("Test ZooException Creation")
    void testZooExceptionCreation() {
        String expectedMessage = "Test exception message";
        HttpStatus expectedStatus = HttpStatus.NOT_FOUND;

        ZooException exception = new ZooException(expectedMessage, expectedStatus);


        assertEquals(expectedMessage, exception.getMessage(), "The exception message should match the expected value.");
        assertEquals(expectedStatus, exception.getHttpStatus(), "The HttpStatus should match the expected value.");


        assertTrue(exception instanceof RuntimeException, "ZooException should be an instance of RuntimeException.");
    }

    @Test
    @DisplayName("Test ZooException HttpStatus Setter")
    void testHttpStatusSetter() {
        ZooException exception = new ZooException("Initial message", HttpStatus.OK);
        exception.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus(), "The HttpStatus should be updatable and match the new value.");
    }
}

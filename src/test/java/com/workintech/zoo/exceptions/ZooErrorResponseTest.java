package com.workintech.zoo.exceptions;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZooErrorResponseTest {

    @Test
    public void testNoArgsConstructor() {
        
        ZooErrorResponse errorResponse = new ZooErrorResponse();
        errorResponse.setStatus(400);
        errorResponse.setMessage("Bad Request");
        errorResponse.setTimestamp(System.currentTimeMillis());

        
        assertEquals(400, errorResponse.getStatus());
        assertEquals("Bad Request", errorResponse.getMessage());
        
    }

    @Test
    public void testAllArgsConstructor() {
        
        long now = System.currentTimeMillis();

        
        ZooErrorResponse errorResponse = new ZooErrorResponse(404, "Not Found", now);

        
        assertEquals(404, errorResponse.getStatus());
        assertEquals("Not Found", errorResponse.getMessage());
        assertEquals(now, errorResponse.getTimestamp());
    }
}

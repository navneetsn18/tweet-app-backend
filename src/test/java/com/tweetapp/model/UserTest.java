package com.tweetapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserTest {
    
    @Test
    public void testConstructor() {
        User actualUser = new User();
        actualUser.setEmail("jane.doe@example.org");
        actualUser.setFirstName("Jane");
        actualUser.setId(1);
        actualUser.setLastName("Doe");
        actualUser.setLoggedin(true);
        actualUser.setPassword("pass");
        actualUser.setUsername("janedoe");
        assertEquals("jane.doe@example.org", actualUser.getEmail());
        assertEquals("Jane", actualUser.getFirstName());
        assertEquals(1, actualUser.getId());
        assertEquals("Doe", actualUser.getLastName());
        assertEquals("pass", actualUser.getPassword());
        assertEquals("janedoe", actualUser.getUsername());
        assertTrue(actualUser.isLoggedin());
    }

    @Test
    public void testConstructor2() {
        User actualUser = new User(1, "Jane", "Doe", "janedoe", "jane.doe@example.org", "pass", true);
        actualUser.setEmail("jane.doe@example.org");
        actualUser.setFirstName("Jane");
        actualUser.setId(1);
        actualUser.setLastName("Doe");
        actualUser.setLoggedin(true);
        actualUser.setPassword("pass");
        actualUser.setUsername("janedoe");
        assertEquals("jane.doe@example.org", actualUser.getEmail());
        assertEquals("Jane", actualUser.getFirstName());
        assertEquals(1, actualUser.getId());
        assertEquals("Doe", actualUser.getLastName());
        assertEquals("pass", actualUser.getPassword());
        assertEquals("janedoe", actualUser.getUsername());
        assertTrue(actualUser.isLoggedin());
    }

    @Test
    public void testConstructor3() {
        User actualUser = new User("Jane", "Doe", "janedoe", "jane.doe@example.org", "pass", true);
        actualUser.setEmail("jane.doe@example.org");
        actualUser.setFirstName("Jane");
        actualUser.setId(1);
        actualUser.setLastName("Doe");
        actualUser.setLoggedin(true);
        actualUser.setPassword("pass");
        actualUser.setUsername("janedoe");
        assertEquals("jane.doe@example.org", actualUser.getEmail());
        assertEquals("Jane", actualUser.getFirstName());
        assertEquals(1, actualUser.getId());
        assertEquals("Doe", actualUser.getLastName());
        assertEquals("pass", actualUser.getPassword());
        assertEquals("janedoe", actualUser.getUsername());
        assertTrue(actualUser.isLoggedin());
    }
}


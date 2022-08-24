package com.tweetapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UserTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#User()}
     *   <li>{@link User#setEmail(String)}
     *   <li>{@link User#setFirstName(String)}
     *   <li>{@link User#setId(int)}
     *   <li>{@link User#setLastName(String)}
     *   <li>{@link User#setLoggedin(boolean)}
     *   <li>{@link User#setPassword(String)}
     *   <li>{@link User#setUsername(String)}
     *   <li>{@link User#getEmail()}
     *   <li>{@link User#getFirstName()}
     *   <li>{@link User#getId()}
     *   <li>{@link User#getLastName()}
     *   <li>{@link User#getPassword()}
     *   <li>{@link User#getUsername()}
     *   <li>{@link User#isLoggedin()}
     * </ul>
     */
    @Test
    void testConstructor() {
        User actualUser = new User();
        actualUser.setEmail("jane.doe@example.org");
        actualUser.setFirstName("Jane");
        actualUser.setId(1);
        actualUser.setLastName("Doe");
        actualUser.setLoggedin(true);
        actualUser.setPassword("iloveyou");
        actualUser.setUsername("janedoe");
        assertEquals("jane.doe@example.org", actualUser.getEmail());
        assertEquals("Jane", actualUser.getFirstName());
        assertEquals(1, actualUser.getId());
        assertEquals("Doe", actualUser.getLastName());
        assertEquals("iloveyou", actualUser.getPassword());
        assertEquals("janedoe", actualUser.getUsername());
        assertTrue(actualUser.isLoggedin());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#User(int, String, String, String, String, String, boolean)}
     *   <li>{@link User#setEmail(String)}
     *   <li>{@link User#setFirstName(String)}
     *   <li>{@link User#setId(int)}
     *   <li>{@link User#setLastName(String)}
     *   <li>{@link User#setLoggedin(boolean)}
     *   <li>{@link User#setPassword(String)}
     *   <li>{@link User#setUsername(String)}
     *   <li>{@link User#getEmail()}
     *   <li>{@link User#getFirstName()}
     *   <li>{@link User#getId()}
     *   <li>{@link User#getLastName()}
     *   <li>{@link User#getPassword()}
     *   <li>{@link User#getUsername()}
     *   <li>{@link User#isLoggedin()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        User actualUser = new User(1, "Jane", "Doe", "janedoe", "jane.doe@example.org", "iloveyou", true);
        actualUser.setEmail("jane.doe@example.org");
        actualUser.setFirstName("Jane");
        actualUser.setId(1);
        actualUser.setLastName("Doe");
        actualUser.setLoggedin(true);
        actualUser.setPassword("iloveyou");
        actualUser.setUsername("janedoe");
        assertEquals("jane.doe@example.org", actualUser.getEmail());
        assertEquals("Jane", actualUser.getFirstName());
        assertEquals(1, actualUser.getId());
        assertEquals("Doe", actualUser.getLastName());
        assertEquals("iloveyou", actualUser.getPassword());
        assertEquals("janedoe", actualUser.getUsername());
        assertTrue(actualUser.isLoggedin());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#User(String, String, String, String, String, boolean)}
     *   <li>{@link User#setEmail(String)}
     *   <li>{@link User#setFirstName(String)}
     *   <li>{@link User#setId(int)}
     *   <li>{@link User#setLastName(String)}
     *   <li>{@link User#setLoggedin(boolean)}
     *   <li>{@link User#setPassword(String)}
     *   <li>{@link User#setUsername(String)}
     *   <li>{@link User#getEmail()}
     *   <li>{@link User#getFirstName()}
     *   <li>{@link User#getId()}
     *   <li>{@link User#getLastName()}
     *   <li>{@link User#getPassword()}
     *   <li>{@link User#getUsername()}
     *   <li>{@link User#isLoggedin()}
     * </ul>
     */
    @Test
    void testConstructor3() {
        User actualUser = new User("Jane", "Doe", "janedoe", "jane.doe@example.org", "iloveyou", true);
        actualUser.setEmail("jane.doe@example.org");
        actualUser.setFirstName("Jane");
        actualUser.setId(1);
        actualUser.setLastName("Doe");
        actualUser.setLoggedin(true);
        actualUser.setPassword("iloveyou");
        actualUser.setUsername("janedoe");
        assertEquals("jane.doe@example.org", actualUser.getEmail());
        assertEquals("Jane", actualUser.getFirstName());
        assertEquals(1, actualUser.getId());
        assertEquals("Doe", actualUser.getLastName());
        assertEquals("iloveyou", actualUser.getPassword());
        assertEquals("janedoe", actualUser.getUsername());
        assertTrue(actualUser.isLoggedin());
    }
}


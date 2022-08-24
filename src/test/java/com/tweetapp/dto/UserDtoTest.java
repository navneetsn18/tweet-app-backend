package com.tweetapp.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserDtoTest {

	@Test
	public void testConstructor() {
		UserDto actualUserDto = new UserDto();
		actualUserDto.setEmail("jane.doe@example.org");
		actualUserDto.setFirstName("Jane");
		actualUserDto.setLastName("Doe");
		actualUserDto.setLoggedin(true);
		actualUserDto.setPassword("pass");
		actualUserDto.setUsername("janedoe");
		assertEquals("jane.doe@example.org", actualUserDto.getEmail());
		assertEquals("Jane", actualUserDto.getFirstName());
		assertEquals("Doe", actualUserDto.getLastName());
		assertEquals("pass", actualUserDto.getPassword());
		assertEquals("janedoe", actualUserDto.getUsername());
		assertTrue(actualUserDto.isLoggedin());
	}

	@Test
	public void testConstructor2() {
		UserDto actualUserDto = new UserDto("Jane", "Doe", "janedoe", "jane.doe@example.org", "pass", true);
		actualUserDto.setEmail("jane.doe@example.org");
		actualUserDto.setFirstName("Jane");
		actualUserDto.setLastName("Doe");
		actualUserDto.setLoggedin(true);
		actualUserDto.setPassword("pass");
		actualUserDto.setUsername("janedoe");
		assertEquals("jane.doe@example.org", actualUserDto.getEmail());
		assertEquals("Jane", actualUserDto.getFirstName());
		assertEquals("Doe", actualUserDto.getLastName());
		assertEquals("pass", actualUserDto.getPassword());
		assertEquals("janedoe", actualUserDto.getUsername());
		assertTrue(actualUserDto.isLoggedin());
	}
}

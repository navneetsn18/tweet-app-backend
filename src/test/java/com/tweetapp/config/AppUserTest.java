package com.tweetapp.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import com.tweetapp.model.User;

public class AppUserTest {

	@Test
	public void testConstructor() {
		User user = new User();
		user.setEmail("jane.doe@example.org");
		user.setFirstName("Jane");
		user.setId(1);
		user.setLastName("Doe");
		user.setLoggedin(true);
		user.setPassword("pass");
		user.setUsername("janedoe");
		AppUser actualAppUser = new AppUser(user);
		assertTrue(actualAppUser.isAccountNonExpired());
		assertTrue(actualAppUser.isAccountNonLocked());
		assertTrue(actualAppUser.isCredentialsNonExpired());
		assertTrue(actualAppUser.isEnabled());
	}

	@Test
	public void testConstructor2() {
		User user = new User();
		user.setEmail("jane.doe@example.org");
		user.setFirstName("Jane");
		user.setId(1);
		user.setLastName("Doe");
		user.setLoggedin(true);
		user.setPassword("pass");
		user.setUsername("janedoe");
		AppUser actualAppUser = new AppUser(user);
		Collection<? extends GrantedAuthority> authorities = actualAppUser.getAuthorities();
		assertEquals(1, authorities.size());
		assertEquals("pass", actualAppUser.getPassword());
		assertEquals("user", ((List<? extends GrantedAuthority>) authorities).get(0).getAuthority());
	}

	@Test
	public void testGetPassword() {
		assertNull((new AppUser(new User())).getPassword());
	}

	@Test
	public void testGetUsername() {
		assertNull((new AppUser(new User())).getUsername());
		assertEquals("janedoe",
				(new AppUser(new User("Jane", "Doe", "janedoe", "jane.doe@example.org", "pass", true))).getUsername());
	}
}

package com.tweetapp.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tweetapp.model.User;
import com.tweetapp.repository.UsersRepository;

@ContextConfiguration(classes = { AppUserService.class })
@ExtendWith(SpringExtension.class)
public class AppUserServiceTest {
	@Autowired
	private AppUserService appUserService;

	@MockBean
	private UsersRepository usersRepository;

	@Test
	public void testLoadUserByUsername() throws UsernameNotFoundException {
		User user = new User();
		user.setEmail("jane.doe@example.org");
		user.setFirstName("Jane");
		user.setId(1);
		user.setLastName("Doe");
		user.setLoggedin(true);
		user.setPassword("pass");
		user.setUsername("janedoe");

		User user1 = new User();
		user1.setEmail("jane.doe@example.org");
		user1.setFirstName("Jane");
		user1.setId(1);
		user1.setLastName("Doe");
		user1.setLoggedin(true);
		user1.setPassword("pass");
		user1.setUsername("janedoe");
		when(usersRepository.findByEmail((String) any())).thenReturn(user);
		when(usersRepository.findByUsername((String) any())).thenReturn(user1);
		UserDetails actualLoadUserByUsernameResult = appUserService.loadUserByUsername("janedoe");
		Collection<? extends GrantedAuthority> authorities = actualLoadUserByUsernameResult.getAuthorities();
		assertEquals(1, authorities.size());
		assertEquals("pass", actualLoadUserByUsernameResult.getPassword());
		assertEquals("user", ((List<? extends GrantedAuthority>) authorities).get(0).getAuthority());
		verify(usersRepository).findByUsername((String) any());
	}

	@Test
	public void testLoadUserByUsername2() throws UsernameNotFoundException {
		when(usersRepository.findByEmail((String) any())).thenThrow(new UsernameNotFoundException("Msg"));
		when(usersRepository.findByUsername((String) any())).thenThrow(new UsernameNotFoundException("Msg"));
		assertThrows(UsernameNotFoundException.class, () -> appUserService.loadUserByUsername("janedoe"));
		verify(usersRepository).findByUsername((String) any());
	}
}

package com.tweetapp.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.tweetapp.model.User;
import com.tweetapp.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = { AuthenticationController.class })
@ExtendWith(SpringExtension.class)
class AuthenticationControllerTest {
	@Autowired
	private AuthenticationController authenticationController;

	@MockBean
	private UsersRepository usersRepository;

	/**
	 * Method under test: {@link AuthenticationController#authenticate(String)}
	 */
	@Test
	void testAuthenticate() throws Exception {
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
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/authenticate")
				.header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
		MockMvcBuilders.standaloneSetup(authenticationController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Incorrect Username or Password!\"}"));
	}

	/**
	 * Method under test: {@link AuthenticationController#encoder()}
	 */
	@Test
	void testEncoder() {
		assertTrue(authenticationController.encoder() instanceof BCryptPasswordEncoder);
	}
}

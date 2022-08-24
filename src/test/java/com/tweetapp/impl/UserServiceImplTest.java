package com.tweetapp.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tweetapp.dto.request.ForgotPassword;
import com.tweetapp.dto.request.Register;
import com.tweetapp.dto.response.UserResponse;
import com.tweetapp.model.User;
import com.tweetapp.repository.UsersRepository;
import com.tweetapp.service.EmailService;

import java.util.ArrayList;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = { UserServiceImpl.class })
@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {
	@MockBean
	private EmailService emailService;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@MockBean
	private UsersRepository usersRepository;

	@Test
	public void testGetAllUsers() {
		ArrayList<User> userList = new ArrayList<>();
		when(usersRepository.findAll()).thenReturn(userList);
		UserResponse actualAllUsers = userServiceImpl.getAllUsers();
		assertEquals("Data Found!", actualAllUsers.getStatus());
		assertEquals(userList, actualAllUsers.getUserDtos());
		verify(usersRepository).findAll();
	}

	@Test
	public void testGetAllUsers2() {
		User user = new User();
		user.setEmail("jane.doe@example.org");
		user.setFirstName("Jane");
		user.setId(1);
		user.setLastName("Doe");
		user.setLoggedin(true);
		user.setPassword("pass");
		user.setUsername("janedoe");

		ArrayList<User> userList = new ArrayList<>();
		userList.add(user);
		when(usersRepository.findAll()).thenReturn(userList);
		UserResponse actualAllUsers = userServiceImpl.getAllUsers();
		assertEquals("Data Found!", actualAllUsers.getStatus());
		assertEquals(1, actualAllUsers.getUserDtos().size());
		verify(usersRepository).findAll();
	}

	@Test
	public void testFindUser() {
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
		UserResponse actualFindUserResult = userServiceImpl.findUser("janedoe");
		assertEquals("Data Found!", actualFindUserResult.getStatus());
		assertEquals(1, actualFindUserResult.getUserDtos().size());
		verify(usersRepository).findByUsername((String) any());
	}

	@Test
	public void testGetAllLoggedInUsers() {
		ArrayList<User> userList = new ArrayList<>();
		when(usersRepository.findByLoggedinTrue()).thenReturn(userList);
		UserResponse actualAllLoggedInUsers = userServiceImpl.getAllLoggedInUsers();
		assertEquals("Data Found!", actualAllLoggedInUsers.getStatus());
		assertEquals(userList, actualAllLoggedInUsers.getUserDtos());
		verify(usersRepository).findByLoggedinTrue();
	}

	@Test
	public void testGetAllLoggedInUsers2() {
		User user = new User();
		user.setEmail("jane.doe@example.org");
		user.setFirstName("Jane");
		user.setId(1);
		user.setLastName("Doe");
		user.setLoggedin(true);
		user.setPassword("pass");
		user.setUsername("janedoe");

		ArrayList<User> userList = new ArrayList<>();
		userList.add(user);
		when(usersRepository.findByLoggedinTrue()).thenReturn(userList);
		UserResponse actualAllLoggedInUsers = userServiceImpl.getAllLoggedInUsers();
		assertEquals("Data Found!", actualAllLoggedInUsers.getStatus());
		assertEquals(1, actualAllLoggedInUsers.getUserDtos().size());
		verify(usersRepository).findByLoggedinTrue();
	}

	@Test
	public void testRegisterUser() {
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

		User user2 = new User();
		user2.setEmail("jane.doe@example.org");
		user2.setFirstName("Jane");
		user2.setId(1);
		user2.setLastName("Doe");
		user2.setLoggedin(true);
		user2.setPassword("pass");
		user2.setUsername("janedoe");
		when(usersRepository.findByEmail((String) any())).thenReturn(user);
		when(usersRepository.findByUsername((String) any())).thenReturn(user1);
		when(usersRepository.save((User) any())).thenReturn(user2);
		assertEquals("Email Already Exists!", userServiceImpl
				.registerUser(new Register("Jane", "Doe", "janedoe", "jane.doe@example.org", "pass")).getStatus());
		verify(usersRepository).findByEmail((String) any());
	}

	@Test
	public void testRegisterUser2() {
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

		User user2 = new User();
		user2.setEmail("jane.doe@example.org");
		user2.setFirstName("Jane");
		user2.setId(1);
		user2.setLastName("Doe");
		user2.setLoggedin(true);
		user2.setPassword("pass");
		user2.setUsername("janedoe");
		when(usersRepository.findByEmail((String) any())).thenReturn(user);
		when(usersRepository.findByUsername((String) any())).thenReturn(user1);
		when(usersRepository.save((User) any())).thenReturn(user2);
		assertEquals("Invalid Email Format!", userServiceImpl
				.registerUser(new Register("Jane", "Doe", "janedoe", "Email Already Exists!", "pass")).getStatus());
	}

	@Test
	public void testRegisterUser3() {
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

		User user2 = new User();
		user2.setEmail("jane.doe@example.org");
		user2.setFirstName("Jane");
		user2.setId(1);
		user2.setLastName("Doe");
		user2.setLoggedin(true);
		user2.setPassword("pass");
		user2.setUsername("janedoe");
		when(usersRepository.findByEmail((String) any())).thenReturn(user);
		when(usersRepository.findByUsername((String) any())).thenReturn(user1);
		when(usersRepository.save((User) any())).thenReturn(user2);
		assertEquals("Error Occured!",
				userServiceImpl.registerUser(new Register("Jane", "Doe", "janedoe", null, "pass")).getStatus());
	}

	@Test
	public void testRegisterUser4() {
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

		User user2 = new User();
		user2.setEmail("jane.doe@example.org");
		user2.setFirstName("Jane");
		user2.setId(1);
		user2.setLastName("Doe");
		user2.setLoggedin(true);
		user2.setPassword("pass");
		user2.setUsername("janedoe");
		when(usersRepository.findByEmail((String) any())).thenReturn(user);
		when(usersRepository.findByUsername((String) any())).thenReturn(user1);
		when(usersRepository.save((User) any())).thenReturn(user2);
		assertEquals("Error Occured!", userServiceImpl.registerUser(null).getStatus());
	}

	@Test
	public void testResetPassword() {
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

		User user2 = new User();
		user2.setEmail("jane.doe@example.org");
		user2.setFirstName("Jane");
		user2.setId(1);
		user2.setLastName("Doe");
		user2.setLoggedin(true);
		user2.setPassword("pass");
		user2.setUsername("janedoe");
		when(usersRepository.save((User) any())).thenReturn(user2);
		when(usersRepository.findByEmail((String) any())).thenReturn(user);
		when(usersRepository.findByUsername((String) any())).thenReturn(user1);
		UserResponse actualResetPasswordResult = userServiceImpl
				.resetPassword(new ForgotPassword("janedoe", "pass"));
		assertEquals("Password Successfully Changed", actualResetPasswordResult.getStatus());
		assertEquals(1, actualResetPasswordResult.getUserDtos().size());
		verify(usersRepository).findByUsername((String) any());
		verify(usersRepository).save((User) any());
	}

	@Test
	public void testResetPassword2() {
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

		User user2 = new User();
		user2.setEmail("jane.doe@example.org");
		user2.setFirstName("Jane");
		user2.setId(1);
		user2.setLastName("Doe");
		user2.setLoggedin(true);
		user2.setPassword("pass");
		user2.setUsername("janedoe");
		when(usersRepository.save((User) any())).thenReturn(user2);
		when(usersRepository.findByEmail((String) any())).thenReturn(user);
		when(usersRepository.findByUsername((String) any())).thenReturn(user1);
		assertEquals("Error Occured!", userServiceImpl.resetPassword(new ForgotPassword("janedoe", null)).getStatus());
		verify(usersRepository).findByUsername((String) any());
	}

	@Test
	public void testResetPassword3() {
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

		User user2 = new User();
		user2.setEmail("jane.doe@example.org");
		user2.setFirstName("Jane");
		user2.setId(1);
		user2.setLastName("Doe");
		user2.setLoggedin(true);
		user2.setPassword("pass");
		user2.setUsername("janedoe");
		when(usersRepository.save((User) any())).thenReturn(user2);
		when(usersRepository.findByEmail((String) any())).thenReturn(user);
		when(usersRepository.findByUsername((String) any())).thenReturn(user1);
		assertEquals("Error Occured!", userServiceImpl.resetPassword(null).getStatus());
	}

	@Test
	public void testForgotPassword() throws MessagingException {
		doNothing().when(emailService).sendNewPasswordEmail((String) any(), (String) any(), (String) any());

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

		User user2 = new User();
		user2.setEmail("jane.doe@example.org");
		user2.setFirstName("Jane");
		user2.setId(1);
		user2.setLastName("Doe");
		user2.setLoggedin(true);
		user2.setPassword("pass");
		user2.setUsername("janedoe");
		when(usersRepository.save((User) any())).thenReturn(user2);
		when(usersRepository.findByEmail((String) any())).thenReturn(user);
		when(usersRepository.findByUsername((String) any())).thenReturn(user1);
		UserResponse actualForgotPasswordResult = userServiceImpl.forgotPassword("janedoe");
		assertEquals("Password Successfully Changed", actualForgotPasswordResult.getStatus());
		assertEquals(1, actualForgotPasswordResult.getUserDtos().size());
		verify(emailService).sendNewPasswordEmail((String) any(), (String) any(), (String) any());
		verify(usersRepository).findByUsername((String) any());
		verify(usersRepository).save((User) any());
	}

	@Test
	public void testLogout() {
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

		User user2 = new User();
		user2.setEmail("jane.doe@example.org");
		user2.setFirstName("Jane");
		user2.setId(1);
		user2.setLastName("Doe");
		user2.setLoggedin(true);
		user2.setPassword("pass");
		user2.setUsername("janedoe");
		when(usersRepository.findByEmail((String) any())).thenReturn(user);
		when(usersRepository.findByUsername((String) any())).thenReturn(user1);
		assertEquals("Logged Out!", userServiceImpl.logout("navneetsn18").getStatus());
		verify(usersRepository).findByUsername((String) any());
		verify(usersRepository).save((User) any());
	}

	@Test
	public void testPasswordEncoder() {
		assertTrue(userServiceImpl.passwordEncoder() instanceof BCryptPasswordEncoder);
	}

	@Test
	public void testGeneratePassword() {
		assertNotNull(UserServiceImpl.generatePassword());
	}
}

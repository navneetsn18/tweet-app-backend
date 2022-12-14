package com.tweetapp.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tweetapp.dto.UserDto;
import com.tweetapp.dto.request.ForgotPassword;
import com.tweetapp.dto.request.Register;
import com.tweetapp.dto.response.UserResponse;
import com.tweetapp.model.User;
import com.tweetapp.repository.UsersRepository;
import com.tweetapp.service.EmailService;
import com.tweetapp.service.UserService;
import static com.tweetapp.constants.Constants.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private EmailService emailService;

	String regex = "^(.+)@(.+)$";
	Pattern pattern = Pattern.compile(regex);

	@Override
	public UserResponse getAllUsers() {
		UserResponse userResponse = new UserResponse();
		List<UserDto> userDtos = new ArrayList<>();
		try {
			List<User> users = userRepository.findAll();
			for (User user : users) {
				UserDto userDto = new UserDto(user.getFirstName(), user.getLastName(), user.getUsername(),
						user.getEmail(), null, user.isLoggedin());
				userDtos.add(userDto);
			}
			userResponse.setUserDtos(userDtos);
			userResponse.setStatus(DATA_FOUND);
		} catch (Exception e) {
			userResponse.setStatus(ERROR_OCCURED);
		}
		return userResponse;
	}

	@Override
	public UserResponse findUser(String username) {
		UserResponse userResponse = new UserResponse();
		List<UserDto> userDtos = new ArrayList<>();
		try {
			User user = userRepository.findByUsername(username);
			if (user == null) {
				user = userRepository.findByEmail(username);
			}
			if (user == null) {
				userResponse.setStatus(NO_USER_FOUND);
			} else {
				UserDto userDto = new UserDto(user.getFirstName(), user.getLastName(), user.getUsername(),
						user.getEmail(), null, user.isLoggedin());
				userDtos.add(userDto);
				userResponse.setUserDtos(userDtos);
				userResponse.setStatus(DATA_FOUND);
			}
		} catch (Exception e) {
			userResponse.setStatus(ERROR_OCCURED);
		}
		return userResponse;
	}

	@Override
	public UserResponse getAllLoggedInUsers() {
		UserResponse userResponse = new UserResponse();
		List<UserDto> userDtos = new ArrayList<>();
		try {
			List<User> users = userRepository.findByLoggedinTrue();
			for (User user : users) {
				UserDto userDto = new UserDto(user.getFirstName(), user.getLastName(), user.getUsername(),
						user.getEmail(), null, user.isLoggedin());
				userDtos.add(userDto);
			}
			userResponse.setUserDtos(userDtos);
			userResponse.setStatus(DATA_FOUND);
		} catch (Exception e) {
			userResponse.setStatus(ERROR_OCCURED);
		}
		return userResponse;
	}

	@Override
	public UserResponse registerUser(Register register) {
		UserResponse userResponse = new UserResponse();
		List<UserDto> userDtos = new ArrayList<>();
		try {
			Matcher matcher = pattern.matcher(register.getEmail());
			if (matcher.matches() == false) {
				userResponse.setStatus(INVALID_EMAIL);
			} else if (userRepository.findByEmail(register.getEmail()) != null) {
				userResponse.setStatus(EMAIL_ALREADY_EXISTS);
			} else if (userRepository.findByUsername(register.getUsername()) != null) {
				userResponse.setStatus(USERNAME_ALREADY_EXISTS);
			} else {
				User user = new User(register.getFirstName(), register.getLastName(), register.getUsername(),
						register.getEmail(), passwordEncoder().encode(register.getPassword()), false);
				UserDto userDto = new UserDto(user.getFirstName(), user.getLastName(), user.getUsername(),
						user.getEmail(), null, user.isLoggedin());
				userDtos.add(userDto);
				userResponse.setUserDtos(userDtos);
				userResponse.setStatus(USER_ADDED);
				userRepository.save(user);
			}

		} catch (Exception e) {
			userResponse.setStatus(ERROR_OCCURED);
		}
		return userResponse;
	}

	@Override
	public UserResponse resetPassword(ForgotPassword forgotPassword) {
		UserResponse userResponse = new UserResponse();
		List<UserDto> userDtos = new ArrayList<>();
		try {
			User user = userRepository.findByUsername(forgotPassword.getUsername());
			if (user == null) {
				user = userRepository.findByEmail(forgotPassword.getUsername());
			}
			if (user == null) {
				userResponse.setStatus(NO_USER_FOUND);
			} else {
				user.setPassword(passwordEncoder().encode(forgotPassword.getPassword()));
				userRepository.save(user);
				UserDto userDto = new UserDto(user.getFirstName(), user.getLastName(), user.getUsername(),
						user.getEmail(), null, user.isLoggedin());
				userDtos.add(userDto);
				userResponse.setUserDtos(userDtos);
				userResponse.setStatus(PASSWORD_CHANGED);
			}
		} catch (Exception e) {
			userResponse.setStatus(ERROR_OCCURED);
		}
		return userResponse;
	}

	@Override
	public UserResponse forgotPassword(String username) {
		UserResponse userResponse = new UserResponse();
		List<UserDto> userDtos = new ArrayList<>();
		try {
			User user = userRepository.findByUsername(username);
			if (user == null) {
				user = userRepository.findByEmail(username);
			}
			if (user == null) {
				userResponse.setStatus(NO_USER_FOUND);
			} else {
				String password = generatePassword();
				emailService.sendNewPasswordEmail(user.getUsername(), password, user.getEmail());
				user.setPassword(passwordEncoder().encode(password));
				userRepository.save(user);
				UserDto userDto = new UserDto(user.getFirstName(), user.getLastName(), user.getUsername(),
						user.getEmail(), null, user.isLoggedin());
				userDtos.add(userDto);
				userResponse.setUserDtos(userDtos);
				userResponse.setStatus(PASSWORD_CHANGED);
			}
		} catch (Exception e) {
			userResponse.setStatus(ERROR_OCCURED);
		}
		return userResponse;
	}

	@Override
	public UserResponse logout(String username) {
		UserResponse userResponse = new UserResponse();
		try {
			User user = userRepository.findByUsername(username);
			if (user == null) {
				user = userRepository.findByEmail(username);
			}
			if (user == null) {
				userResponse.setStatus(NO_USER_FOUND);
			} else {
				user.setLoggedin(false);
				userRepository.save(user);
				userResponse.setStatus(LOGGED_OUT);
			}
		} catch (Exception e) {
			userResponse.setStatus(ERROR_OCCURED);
		}
		return userResponse;
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

}

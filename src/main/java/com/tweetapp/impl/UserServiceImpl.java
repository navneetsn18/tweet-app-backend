package com.tweetapp.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dto.UserDto;
import com.tweetapp.dto.request.UserRequest;
import com.tweetapp.dto.response.UserResponse;
import com.tweetapp.model.User;
import com.tweetapp.repository.UsersRepository;
import com.tweetapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository userRepository;

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
			userResponse.setStatus("Data Found!");
		} catch (Exception e) {
			userResponse.setStatus("Error Occured!");
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
				userResponse.setStatus("No User Found!");
			} else {
				UserDto userDto = new UserDto(user.getFirstName(), user.getLastName(), user.getUsername(),
						user.getEmail(), null, user.isLoggedin());
				userDtos.add(userDto);
				userResponse.setUserDtos(userDtos);
				userResponse.setStatus("Data Found!");
			}
		} catch (Exception e) {
			userResponse.setStatus("Error Occured!");
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
			userResponse.setStatus("Data Found!");
		} catch (Exception e) {
			userResponse.setStatus("Error Occured!");
		}
		return userResponse;
	}

	@Override
	public UserResponse registerUser(UserRequest userRequest) {
		UserResponse userResponse = new UserResponse();
		try {
			Matcher matcher = pattern.matcher(userRequest.getUserDto().getEmail());
			if (matcher.matches() == false) {
				userResponse.setStatus("Invalid Email Format!");
			} else if (userRepository.findByEmail(userRequest.getUserDto().getEmail()) != null) {
				userResponse.setStatus("Email Already Exists!");
			} else if (userRepository.findByUsername(userRequest.getUserDto().getUsername()) != null) {
				userResponse.setStatus("Username Already Exists!");
			} else {
				User user = new User(userRequest.getUserDto().getFirstName(), userRequest.getUserDto().getLastName(),
						userRequest.getUserDto().getUsername(), userRequest.getUserDto().getEmail(),
						userRequest.getUserDto().getPassword(), false);
				userRepository.save(user);
				UserDto userDto = new UserDto(user.getFirstName(), user.getLastName(), user.getUsername(),
						user.getEmail(), null, user.isLoggedin());
				userResponse.setUserDtos(List.of(userDto));
				userResponse.setStatus("User Added!");
			}

		} catch (Exception e) {
			userResponse.setStatus("Error Occured!");
		}
		return userResponse;
	}

	@Override
	public UserResponse forgotPassword(UserRequest userRequest) {
		UserResponse userResponse = new UserResponse();
		List<UserDto> userDtos = new ArrayList<>();
		try {
			User user = userRepository.findByUsername(userRequest.getUserDto().getUsername());
			if (user == null) {
				user = userRepository.findByEmail(userRequest.getUserDto().getEmail());
			}
			if (user == null) {
				userResponse.setStatus("No User Found!");
			} else {
				user.setPassword(userRequest.getUserDto().getPassword());
				userRepository.save(user);
				UserDto userDto = new UserDto(user.getFirstName(), user.getLastName(), user.getUsername(),
						user.getEmail(), null, user.isLoggedin());
				userDtos.add(userDto);
				userResponse.setUserDtos(userDtos);
				userResponse.setStatus("Password Successfully Changed");
			}
		} catch (Exception e) {
			userResponse.setStatus("Error Occured!");
		}
		return userResponse;
	}

//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

}

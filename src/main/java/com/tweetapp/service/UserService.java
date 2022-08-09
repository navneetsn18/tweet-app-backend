package com.tweetapp.service;

import com.tweetapp.dto.request.UserRequest;
import com.tweetapp.dto.response.UserResponse;

public interface UserService {

	// Get all users
	public UserResponse getAllUsers();

	// Get A User
	public UserResponse findUser(String username);

	// Get All Logged In Users
	public UserResponse getAllLoggedInUsers();

	// Register a user
	public UserResponse registerUser(UserRequest userRequest);

	// Reset Password
	public UserResponse forgotPassword(UserRequest userRequest);

}

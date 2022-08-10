package com.tweetapp.service;

import com.tweetapp.dto.request.ForgotPassword;
import com.tweetapp.dto.request.Register;
import com.tweetapp.dto.response.UserResponse;

public interface UserService {

	// Get all users
	public UserResponse getAllUsers();

	// Get A User
	public UserResponse findUser(String username);

	// Get All Logged In Users
	public UserResponse getAllLoggedInUsers();

	// Register a user
	public UserResponse registerUser(Register register);

	// Reset Password
	public UserResponse forgotPassword(ForgotPassword forgotPassword);

}

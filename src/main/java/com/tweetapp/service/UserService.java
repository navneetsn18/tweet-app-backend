package com.tweetapp.service;

import java.util.List;

import com.tweetapp.exceptions.TweetAppExceptions;
import com.tweetapp.model.User;

public interface UserService {

	// Get all users
	public List<User> getAllUsers();

	// Register a user
	public boolean registerUser(User user) throws TweetAppExceptions;

	// validate a user
	public boolean loginUser(String username, String password) throws TweetAppExceptions;

	// Reset Password
	public void resetPassword(String oldPassword, String newPassword) throws TweetAppExceptions;

	// Logout
	public void logout();

}

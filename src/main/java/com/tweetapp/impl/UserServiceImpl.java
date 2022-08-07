package com.tweetapp.impl;

import org.springframework.stereotype.Service;

import com.tweetapp.dto.request.UserRequest;
import com.tweetapp.dto.response.UserResponse;
import com.tweetapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public UserResponse getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponse findUser(UserRequest userRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponse getAllLoggedInUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponse registerUser(UserRequest userRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponse forgotPassword(UserResponse userResponse) {
		// TODO Auto-generated method stub
		return null;
	}

}

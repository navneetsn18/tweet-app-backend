package com.tweetapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.model.LoginStatus;
import com.tweetapp.model.User;
import com.tweetapp.repository.LoginStatusRepository;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.repository.UsersRepository;

@Service
public class TweetAppService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private LoginStatusRepository loginStatusRepository;

	public boolean regsiterUser(String username, String name, String email, String password) {
		User user = new User(username, name, email, password);
		if (usersRepository.findByEmail(email) != null) {
			System.out.println("Email Already Exists.");
			return false;
		} else if (usersRepository.findByUsername(username) != null) {
			System.out.println("Username Already Exists.");
			return false;
		}
		usersRepository.save(user);
		LoginStatus loginData = new LoginStatus(user, false);
		loginStatusRepository.save(loginData);
		return true;
	}

	public boolean loginUser(String username, String password) {
		User user = usersRepository.findByUsername(username);
		if (user == null) {
			user = usersRepository.findByEmail(username);
			if (user == null) {
				System.out.println("Invalid Username Or Email");
				return false;
			}
		}
		if (user.getPassword().equals(password)) {
			LoginStatus loginStatus = loginStatusRepository.findById(user.getId()).get();
			loginStatus.setStatus(true);
			loginStatusRepository.save(loginStatus);
			return true;
		} else {
			System.out.println("Invalid Password");
			return false;
		}
	}

}

package com.tweetapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.model.User;
import com.tweetapp.repository.TweetApiRepository;

@Service
public class TweetAppService {

	@Autowired
	private TweetApiRepository tweetApiRepository;

	public boolean regsiterUser(String username, String name, String email, String password) {
		User user = new User(username, name, email, password);
		if (tweetApiRepository.findByEmail(email) != null) {
			System.out.println("Email Already Exists.");
			return false;
		} else if (tweetApiRepository.findByUsername(username) != null) {
			System.out.println("Username Already Exists.");
			return false;
		}
		tweetApiRepository.save(user);
		return true;
	}

}

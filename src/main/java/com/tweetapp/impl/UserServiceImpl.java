package com.tweetapp.impl;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.exceptions.TweetAppExceptions;
import com.tweetapp.model.Tweets;
import com.tweetapp.model.User;
import com.tweetapp.repository.UsersRepository;
import com.tweetapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository usersRepository;

	String regex = "^(.+)@(.+)$";
	Pattern pattern = Pattern.compile(regex);

	@Override
	public List<User> getAllUsers() {
		return usersRepository.findAll();

	}

	@Override
	public boolean registerUser(User user) throws TweetAppExceptions {
		Matcher matcher = pattern.matcher(user.getEmail());
		if (matcher.matches() == false) {
			throw new TweetAppExceptions("Invalid Email Format.");
		}
		if (usersRepository.findByEmail(user.getEmail()) != null) {
			throw new TweetAppExceptions("Email Already Exists.");
		} else if (usersRepository.findByUsername(user.getUsername()) != null) {
			throw new TweetAppExceptions("Username Already Exists.");
		}
		usersRepository.save(user);
		return true;
	}

	@Override
	public User loginUser(String username, String password) throws TweetAppExceptions {
		User user = usersRepository.findByUsername(username);
		if (user == null) {
			user = usersRepository.findByEmail(username);
			if (user == null) {
				throw new TweetAppExceptions("Invalid Username Or Email");
			}
		}
		if (user.getPassword().equals(password)) {
			user.setStatus(true);
			usersRepository.save(user);
		} else {
			System.out.println("Invalid Password");
			return null;
		}
		return user;
	}

	@Override
	public void resetPassword(String oldPassword, String newPassword) throws TweetAppExceptions {
		List<User> users = usersRepository.findAll();
		for (User user : users) {
			if (user.isStatus() == true && user.getPassword().equals(oldPassword)) {
				user.setPassword(newPassword);
				usersRepository.save(user);
				System.out.println("Password Successfully Changed!");
				return;
			} else {
				throw new TweetAppExceptions("Invalid Password");
			}
		}
	}

	@Override
	public void forgotPassword(String username, String newPassword) throws TweetAppExceptions {
		List<User> users = usersRepository.findAll();
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				user.setPassword(newPassword);
				usersRepository.save(user);
				System.out.println("Password Successfully Changed!");
				return;
			} else {
				throw new TweetAppExceptions("Invalid Password");
			}
		}
	}

	@Override
	public void logout() {
		List<User> users = usersRepository.findAll();
		if (users != null) {
			for (User user : users) {
				if (user.isStatus()) {
					user.setStatus(false);
					usersRepository.save(user);
					System.out.println("Successfully LogedOut!");
				}
			}
		}
	}

}

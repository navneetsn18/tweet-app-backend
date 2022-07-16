package com.tweetapp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.model.Tweets;
import com.tweetapp.model.User;
import com.tweetapp.repository.TweetsRepository;
import com.tweetapp.repository.UsersRepository;
import com.tweetapp.service.TweetsService;

@Service
public class TweetServiceImpl implements TweetsService {

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private TweetsRepository tweetsRepository;

	@Override
	public List<Tweets> getAllTweets() {
		return tweetsRepository.findAll();
	}

	@Override
	public List<Tweets> getTweets(String username) {
		User user = userRepository.findByUsername(username);
		return user.getTweets();
	}

	@Override
	public void postTweet(String username, Tweets tweet) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			tweetsRepository.save(tweet);
			user.addTweet(tweet);
			userRepository.save(user);
		}
	}

}

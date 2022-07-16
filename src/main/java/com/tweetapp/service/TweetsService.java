package com.tweetapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tweetapp.model.Tweets;

@Service
public interface TweetsService {
	
	// Get all the tweets
	public List<Tweets> getAllTweets();

	// Get tweets for a specific user
	public List<Tweets> getTweets(String username);

	// Save a tweet
	void postTweet(String username, Tweets tweet);
}

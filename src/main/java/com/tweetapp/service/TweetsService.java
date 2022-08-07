package com.tweetapp.service;

import com.tweetapp.dto.request.TweetRequest;
import com.tweetapp.dto.response.TweetResponse;

public interface TweetsService {
	
	// Get all the tweets
	public TweetResponse getAllTweets();

	// Get tweets for a specific user
	public TweetResponse getTweetsByUsername(String username);

	// Save a tweet
	public TweetResponse postTweet(String username, TweetRequest tweetRequest);
	
	// Delete a Tweet
	public TweetResponse deleteTweet(String username, Long id);
	
	// Reply a tweet
	public TweetResponse replyTweet(TweetRequest tweetRequest);
	
	// Like a  tweet
	public TweetResponse likeTweet(TweetRequest tweetRequest);
	
	// Update A tweet
	public TweetResponse updateTweet(TweetRequest tweetRequest);
}

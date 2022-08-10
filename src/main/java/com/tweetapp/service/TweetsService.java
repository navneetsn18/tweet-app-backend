package com.tweetapp.service;

import com.tweetapp.dto.request.PostTweet;
import com.tweetapp.dto.request.TweetReply;
import com.tweetapp.dto.request.UpdateTweet;
import com.tweetapp.dto.response.TweetResponse;

public interface TweetsService {

	// Get all the tweets
	public TweetResponse getAllTweets();

	// Get tweets for a specific user
	public TweetResponse getTweetsByUsername(String username);

	// Save a tweet
	public TweetResponse postTweet(PostTweet postTweet);

	// Delete a Tweet
	public TweetResponse deleteTweet(String username, Long id);

	// Reply a tweet
	public TweetResponse replyTweet(TweetReply tweetReply);

	// Like a tweet
	public TweetResponse likeTweet(Long id, String username);

	// Update A tweet
	public TweetResponse updateTweet(UpdateTweet updateTweet);

}

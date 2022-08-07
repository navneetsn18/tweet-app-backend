package com.tweetapp.dto.request;

import com.tweetapp.dto.TweetDto;

public class TweetRequest {

	private TweetDto tweet;

	public TweetRequest() {
		// TODO Auto-generated constructor stub
	}

	public TweetRequest(TweetDto tweet) {
		super();
		this.tweet = tweet;
	}

	public TweetDto getTweet() {
		return tweet;
	}

	public void setTweet(TweetDto tweet) {
		this.tweet = tweet;
	}
}

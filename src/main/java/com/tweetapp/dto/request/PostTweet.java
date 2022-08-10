package com.tweetapp.dto.request;

public class PostTweet {
	private String username;
	private String tweet;

	public PostTweet() {
		// TODO Auto-generated constructor stub
	}

	public PostTweet(String username, String tweet) {
		super();
		this.username = username;
		this.tweet = tweet;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

}

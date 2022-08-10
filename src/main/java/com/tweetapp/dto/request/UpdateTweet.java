package com.tweetapp.dto.request;

public class UpdateTweet {
	private Long id;
	private String tweet;

	public UpdateTweet() {
		// TODO Auto-generated constructor stub
	}

	public UpdateTweet(Long id, String tweet) {
		super();
		this.id = id;
		this.tweet = tweet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

}

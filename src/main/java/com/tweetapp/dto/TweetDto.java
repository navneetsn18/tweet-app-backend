package com.tweetapp.dto;

import java.util.List;

public class TweetDto {
	private Long id;
	private String date;
	private String time;
	private String username;
	private String tweet;
	private Long likes;
	private List<String> reply;

	public TweetDto() {
		// TODO Auto-generated constructor stub
	}

	public TweetDto(Long id, String date, String time, String username, String tweet, Long likes, List<String> reply) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.username = username;
		this.tweet = tweet;
		this.likes = likes;
		this.reply = reply;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public Long getLikes() {
		return likes;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}

	public List<String> getReply() {
		return reply;
	}

	public void setReply(List<String> reply) {
		this.reply = reply;
	}

}

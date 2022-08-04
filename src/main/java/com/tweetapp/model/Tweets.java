package com.tweetapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Tweets")
public class Tweets {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "email")
	private User user;
	private String date;
	private String tweet;
	@Column(columnDefinition = "Integer default 0")
	private int likes;
	private String username;

	public Tweets() {
		// TODO Auto-generated constructor stub
	}

	public Tweets(Long id, User user, String date, String tweet, int likes, String username) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.tweet = tweet;
		this.likes = likes;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Tweets [id=" + id + ", user=" + user + ", date=" + date + ", tweet=" + tweet + ", likes=" + likes
				+ ", username=" + username + "]";
	}

}

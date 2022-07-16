package com.tweetapp.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name = "tw_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "tw_us_id")
	private User user;
	@Column(name = "tw_date")
	private Date date;
	@Column(name = "tw_tweet")
	private String tweet;

	public Tweets() {
		// TODO Auto-generated constructor stub
	}

	public Tweets(int id, User user, Date date, String tweet) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.tweet = tweet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	@Override
	public String toString() {
		return "Tweets [id=" + id + ", user=" + user + ", date=" + date + ", tweet=" + tweet + "]";
	}

}

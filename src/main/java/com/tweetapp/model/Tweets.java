package com.tweetapp.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.tweetapp.dto.Reply;

@Entity
@Table(name = "Tweets")
public class Tweets {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private String username;
	private String tweet;
	@ElementCollection(targetClass = String.class)
	private Set<String> likes;
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "tweet_reply", joinColumns = @JoinColumn(name = "tweets_id"))
	@AttributeOverrides({ @AttributeOverride(name = "username", column = @Column(name = "username")),
			@AttributeOverride(name = "reply", column = @Column(name = "reply")),
			@AttributeOverride(name = "date", column = @Column(name = "date")) })
	private List<Reply> reply;

	public Tweets() {
		// TODO Auto-generated constructor stub
	}

	public Tweets(Long id, Date date, String username, String tweet, Set<String> likes, List<Reply> reply) {
		super();
		this.id = id;
		this.date = date;
		this.username = username;
		this.tweet = tweet;
		this.likes = likes;
		this.reply = reply;
	}

	public Tweets(Date date, String username, String tweet, Set<String> likes, List<Reply> reply) {
		super();
		this.date = date;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public Set<String> getLikes() {
		return likes;
	}

	public void setLikes(Set<String> likes) {
		this.likes = likes;
	}

	public List<Reply> getReply() {
		return reply;
	}

	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}

}

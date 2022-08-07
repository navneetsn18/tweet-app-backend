package com.tweetapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private Long likes;
	@ElementCollection(targetClass=String.class)
	private List<String> reply;

	public Tweets() {
		// TODO Auto-generated constructor stub
	}

	public Tweets(Long id, Date date, String username, String tweet, Long likes, List<String> reply) {
		super();
		this.id = id;
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

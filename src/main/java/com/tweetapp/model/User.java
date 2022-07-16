package com.tweetapp.model;

import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "us_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "us_username")
	private String username;
	@Column(name = "us_name")
	private String name;
	@Column(name = "us_email")
	private String email;
	@Column(name = "us_password")
	private String password;
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Tweets> tweets;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int id, String username, String name, String email, String password, Set<Tweets> tweets) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.tweets = tweets;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Tweets> getTweets() {
		return tweets;
	}

	public void setTweets(Set<Tweets> tweets) {
		this.tweets = tweets;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name + ", email=" + email + ", password="
				+ password + ", tweets=" + tweets + "]";
	}

}

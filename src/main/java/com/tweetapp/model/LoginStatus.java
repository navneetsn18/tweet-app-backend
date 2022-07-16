package com.tweetapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LoginStatus")
public class LoginStatus {

	@Id
	@Column(name = "ls_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	@JoinColumn(name = "ls_us_id")
	private User user;
	@Column(name = "ls_st")
	private boolean status;

	public LoginStatus() {
		// TODO Auto-generated constructor stub
	}

	public LoginStatus(int id, User user, boolean status) {
		super();
		this.id = id;
		this.user = user;
		this.status = status;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "LoginStatus [id=" + id + ", user=" + user + ", status=" + status + "]";
	}

}

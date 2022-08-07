package com.tweetapp.dto;

import java.util.Date;

public class Reply {

	private String username;
	private String reply;
	private Date date;

	public Reply() {
		// TODO Auto-generated constructor stub
	}

	public Reply(String username, String reply, Date date) {
		super();
		this.username = username;
		this.reply = reply;
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

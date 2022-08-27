package com.tweetapp.dto;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Reply {

	private String username;
	private String replyMsg;
	private Date date;

	public Reply() {
		// TODO Auto-generated constructor stub
	}

	public Reply(String username, String replyMsg, Date date) {
		super();
		this.username = username;
		this.replyMsg = replyMsg;
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReplyMsg() {
		return replyMsg;
	}

	public void setReply(String replyMsg) {
		this.replyMsg = replyMsg;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

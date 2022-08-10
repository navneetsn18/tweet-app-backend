package com.tweetapp.dto.request;

import java.util.List;

import com.tweetapp.dto.Reply;

public class TweetReply {
	private Long id;
	private List<Reply> reply;

	public TweetReply() {
		// TODO Auto-generated constructor stub
	}

	public TweetReply(Long id, List<Reply> reply) {
		super();
		this.id = id;
		this.reply = reply;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Reply> getReply() {
		return reply;
	}

	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}

}

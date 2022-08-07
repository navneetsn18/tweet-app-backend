package com.tweetapp.dto.response;

import java.io.Serializable;
import java.util.List;

import com.tweetapp.dto.TweetDto;

public class TweetResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TweetDto> tweetDtos;
	private String status;

	public TweetResponse() {
		// TODO Auto-generated constructor stub
	}

	public TweetResponse(List<TweetDto> tweetDtos, String status) {
		super();
		this.tweetDtos = tweetDtos;
		this.status = status;
	}

	public List<TweetDto> getTweetDtos() {
		return tweetDtos;
	}

	public void setTweetDtos(List<TweetDto> tweetDtos) {
		this.tweetDtos = tweetDtos;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

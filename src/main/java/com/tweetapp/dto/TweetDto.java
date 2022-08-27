package com.tweetapp.dto;

import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TweetDto {
	private Long id;
	private String date;
	private String time;
	private String username;
	private String tweet;
	private Set<String> likes;
	private List<Reply> reply;

	public TweetDto(Long id, String date, String time, String username, String tweet, Set<String> likes,
			List<Reply> reply) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.username = username;
		this.tweet = tweet;
		this.likes = likes;
		this.reply = reply;
	}
}

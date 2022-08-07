package com.tweetapp.impl;

import org.springframework.stereotype.Service;

import com.tweetapp.dto.request.TweetRequest;
import com.tweetapp.dto.response.TweetResponse;
import com.tweetapp.service.TweetsService;

@Service
public class TweetServiceImpl implements TweetsService {

	@Override
	public TweetResponse getAllTweets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponse getTweetsByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponse postTweet(String username, TweetRequest tweetRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponse deleteTweet(String username, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponse replyTweet(TweetRequest tweetRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponse likeTweet(TweetRequest tweetRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponse updateTweet(TweetRequest tweetRequest) {
		// TODO Auto-generated method stub
		return null;
	}


}

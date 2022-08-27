package com.tweetapp.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dto.Reply;
import com.tweetapp.dto.TweetDto;
import com.tweetapp.dto.request.PostTweet;
import com.tweetapp.dto.request.TweetReply;
import com.tweetapp.dto.request.UpdateTweet;
import com.tweetapp.dto.response.TweetResponse;
import com.tweetapp.model.Tweets;
import com.tweetapp.repository.TweetsRepository;
import com.tweetapp.service.TweetsService;
import static com.tweetapp.constants.Constants.*;

@Service
public class TweetServiceImpl implements TweetsService {

	@Autowired
	private TweetsRepository tweetRepository;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
	SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");

	@Override
	public TweetResponse getAllTweets() {
		TweetResponse tweetResponse = new TweetResponse();
		List<TweetDto> tweetDtos = new ArrayList<>();
		try {
			List<Tweets> tweets = tweetRepository.findAll();
			getTweets(tweetResponse, tweetDtos, tweets);
		} catch (Exception e) {
			tweetResponse.setStatus(ERROR_OCCURED);
		}
		return tweetResponse;
	}

	private void getTweets(TweetResponse tweetResponse, List<TweetDto> tweetDtos, List<Tweets> tweets) {
		for (Tweets tweet : tweets) {
			TweetDto tweetDto = new TweetDto(tweet.getId(), simpleDateFormat.format(tweet.getDate()),
					simpleTimeFormat.format(tweet.getDate()), tweet.getUsername(), tweet.getTweet(),
					tweet.getLikes(), tweet.getReply());
			tweetDtos.add(tweetDto);
		}
		Collections.reverse(tweetDtos);
		tweetResponse.setTweetDtos(tweetDtos);
		tweetResponse.setStatus(DATA_FOUND);
	}

	@Override
	public TweetResponse getTweetsByUsername(String username) {
		TweetResponse tweetResponse = new TweetResponse();
		List<TweetDto> tweetDtos = new ArrayList<>();
		try {
			List<Tweets> tweets = tweetRepository.findByUsername(username);
			getTweets(tweetResponse, tweetDtos, tweets);
		} catch (Exception e) {
			tweetResponse.setStatus(ERROR_OCCURED);
		}
		return tweetResponse;
	}

	@Override
	public TweetResponse postTweet(PostTweet postTweet) {
		TweetResponse tweetResponse = new TweetResponse();
		try {
			Tweets tweet = new Tweets(new Date(), postTweet.getUsername(), postTweet.getTweet(), new HashSet<>(),
					new ArrayList<>());
			tweetRepository.save(tweet);
			List<TweetDto> tweetDtos = new ArrayList<>();
			TweetDto tweetDto = new TweetDto(tweet.getId(), simpleDateFormat.format(tweet.getDate()),
					simpleTimeFormat.format(tweet.getDate()), tweet.getUsername(), tweet.getTweet(), tweet.getLikes(),
					tweet.getReply());
			tweetDtos.add(tweetDto);
			tweetResponse.setTweetDtos(tweetDtos);
			tweetResponse.setStatus(TWEET_POSTED);
		} catch (Exception e) {
			tweetResponse.setStatus(ERROR_OCCURED);
		}
		return tweetResponse;
	}

	@Override
	public TweetResponse deleteTweet(String username, Long id) {
		TweetResponse tweetResponse = new TweetResponse();
		try {
			tweetRepository.deleteById(id);
			tweetResponse.setStatus(DELETED);
		} catch (Exception e) {
			tweetResponse.setStatus(ERROR_OCCURED);
		}
		return tweetResponse;
	}

	@Override
	public TweetResponse replyTweet(TweetReply tweetReply) {
		TweetResponse tweetResponse = new TweetResponse();
		try {
			List<Reply> replies = tweetReply.getReply();
			Optional<Tweets> optional = tweetRepository.findById(tweetReply.getId());
			List<Reply> newReplies = new ArrayList<>();
			optional.ifPresent(tweet -> {
				for (Reply reply : replies) {
					reply.setDate(new Date());
					newReplies.add(reply);
				}
				List<Reply> oldReplies = tweet.getReply();
				oldReplies.addAll(newReplies);
				tweet.setReply(oldReplies);
				tweetRepository.save(tweet);
			});
			tweetResponse.setStatus(REPLIED);
		} catch (Exception e) {
			tweetResponse.setStatus(ERROR_OCCURED);
		}
		return tweetResponse;
	}

	@Override
	public TweetResponse likeTweet(Long id, String username) {
		TweetResponse tweetResponse = new TweetResponse();
		try {
			Optional<Tweets> optional = tweetRepository.findById(id);
			optional.ifPresent(tweet -> {
				Set<String> likes = tweet.getLikes();
				if (likes.contains(username)) {
					likes.remove(username);
				} else {
					likes.add(username);
				}
				tweet.setLikes(likes);
				tweetRepository.save(tweet);
			});
			tweetResponse.setStatus(LIKE_DISLIKE);
		} catch (Exception e) {
			tweetResponse.setStatus(ERROR_OCCURED);
		}
		return tweetResponse;
	}

	@Override
	public TweetResponse updateTweet(UpdateTweet updateTweet) {
		TweetResponse tweetResponse = new TweetResponse();
		try {
			Optional<Tweets> optional = tweetRepository.findById(updateTweet.getId());
			optional.ifPresent(tweet -> {
				tweet.setTweet(updateTweet.getTweet());
				tweetRepository.save(tweet);
			});
			tweetResponse.setStatus(TWEET_UPDATED);
		} catch (Exception e) {
			tweetResponse.setStatus(ERROR_OCCURED);
		}
		return tweetResponse;
	}

}

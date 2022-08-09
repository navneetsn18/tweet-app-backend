package com.tweetapp.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dto.Reply;
import com.tweetapp.dto.TweetDto;
import com.tweetapp.dto.request.TweetRequest;
import com.tweetapp.dto.response.TweetResponse;
import com.tweetapp.model.Tweets;
import com.tweetapp.repository.TweetsRepository;
import com.tweetapp.service.TweetsService;

@Service
public class TweetServiceImpl implements TweetsService {

	@Autowired
	private TweetsRepository tweetRepository;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");

	@Override
	public TweetResponse getAllTweets() {
		TweetResponse tweetResponse = new TweetResponse();
		List<TweetDto> tweetDtos = new ArrayList<>();
		try {
			List<Tweets> tweets = tweetRepository.findAll();
			for (Tweets tweet : tweets) {
				TweetDto tweetDto = new TweetDto(tweet.getId(), simpleDateFormat.format(tweet.getDate()),
						simpleTimeFormat.format(tweet.getDate()), tweet.getUsername(), tweet.getTweet(),
						tweet.getLikes(), tweet.getReply());
				tweetDtos.add(tweetDto);
			}
			tweetResponse.setTweetDtos(tweetDtos);
			tweetResponse.setStatus("Data Found!");
		} catch (Exception e) {
			tweetResponse.setStatus("Error Occured!");
		}
		return tweetResponse;
	}

	@Override
	public TweetResponse getTweetsByUsername(String username) {
		TweetResponse tweetResponse = new TweetResponse();
		List<TweetDto> tweetDtos = new ArrayList<>();
		try {
			List<Tweets> tweets = tweetRepository.findByUsername(username);
			for (Tweets tweet : tweets) {
				TweetDto tweetDto = new TweetDto(tweet.getId(), simpleDateFormat.format(tweet.getDate()),
						simpleTimeFormat.format(tweet.getDate()), tweet.getUsername(), tweet.getTweet(),
						tweet.getLikes(), tweet.getReply());
				tweetDtos.add(tweetDto);
			}
			tweetResponse.setTweetDtos(tweetDtos);
			tweetResponse.setStatus("Data Found!");
		} catch (Exception e) {
			tweetResponse.setStatus("Error Occured!");
		}
		return tweetResponse;
	}

	@Override
	public TweetResponse postTweet(TweetRequest tweetRequest, String username) {
		TweetResponse tweetResponse = new TweetResponse();
		List<TweetDto> tweetDtos = new ArrayList<>();
		try {
			Tweets tweet = new Tweets(new Date(), tweetRequest.getTweet().getUsername(),
					tweetRequest.getTweet().getTweet(), new HashSet<>(), new ArrayList<>());
			tweetRepository.save(tweet);
			tweetDtos.add(tweetRequest.getTweet());
			tweetResponse.setTweetDtos(tweetDtos);
			tweetResponse.setStatus("Tweet Posted!");
		} catch (Exception e) {
			tweetResponse.setStatus("Error Occured!");
		}
		return tweetResponse;
	}

	@Override
	public TweetResponse deleteTweet(String username, Long id) {
		TweetResponse tweetResponse = new TweetResponse();
		try {
			tweetRepository.deleteById(id);
			tweetResponse.setStatus("Deleted Successfully!");
		} catch (Exception e) {
			tweetResponse.setStatus("Error Occured!");
		}
		return tweetResponse;
	}

	@Override
	public TweetResponse replyTweet(TweetRequest tweetRequest) {
		TweetResponse tweetResponse = new TweetResponse();
		try {
			List<Reply> replies = tweetRequest.getTweet().getReply();
			Optional<Tweets> optional = tweetRepository.findById(tweetRequest.getTweet().getId());
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
			tweetResponse.setStatus("Replied Successfully!");
		} catch (Exception e) {
			tweetResponse.setStatus("Error Occured!");
		}
		return tweetResponse;
	}

	@Override
	public TweetResponse likeTweet(TweetRequest tweetRequest, String username) {
		TweetResponse tweetResponse = new TweetResponse();
		try {
			Optional<Tweets> optional = tweetRepository.findById(tweetRequest.getTweet().getId());
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
			tweetResponse.setStatus("Like/Dislike Success!");
		} catch (Exception e) {
			tweetResponse.setStatus("Error Occured!");
		}
		return tweetResponse;
	}

	@Override
	public TweetResponse updateTweet(TweetRequest tweetRequest, String username) {
		TweetResponse tweetResponse = new TweetResponse();
		try {
			Optional<Tweets> optional = tweetRepository.findById(tweetRequest.getTweet().getId());
			optional.ifPresent(tweet -> {
				tweet.setTweet(tweetRequest.getTweet().getTweet());
				tweetRepository.save(tweet);
			});
			tweetResponse.setStatus("Tweet Updated!");
		} catch (Exception e) {
			tweetResponse.setStatus("Error Occured!");
		}
		return tweetResponse;
	}

}

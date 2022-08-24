package com.tweetapp.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tweetapp.dto.Reply;
import com.tweetapp.dto.TweetDto;
import com.tweetapp.dto.request.PostTweet;
import com.tweetapp.dto.request.TweetReply;
import com.tweetapp.dto.request.UpdateTweet;
import com.tweetapp.dto.response.TweetResponse;
import com.tweetapp.model.Tweets;
import com.tweetapp.repository.TweetsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = { TweetServiceImpl.class })
@ExtendWith(SpringExtension.class)
public class TweetServiceImplTest {
	@Autowired
	private TweetServiceImpl tweetServiceImpl;

	@MockBean
	private TweetsRepository tweetsRepository;

	@Test
	public void testGetAllTweets() {
		ArrayList<Tweets> tweetsList = new ArrayList<>();
		when(tweetsRepository.findAll()).thenReturn(tweetsList);
		TweetResponse actualAllTweets = tweetServiceImpl.getAllTweets();
		assertEquals("Data Found!", actualAllTweets.getStatus());
		List<TweetDto> tweetDtos = actualAllTweets.getTweetDtos();
		assertEquals(tweetsList, tweetDtos);
		assertTrue(tweetDtos.isEmpty());
		verify(tweetsRepository).findAll();
	}

	@Test
	public void testGetAllTweets2() {
		Tweets tweets = new Tweets();
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		tweets.setId(123L);
		tweets.setLikes(new HashSet<>());
		tweets.setReply(new ArrayList<>());
		tweets.setTweet("Data Found!");
		tweets.setUsername("janedoe");

		ArrayList<Tweets> tweetsList = new ArrayList<>();
		tweetsList.add(tweets);
		when(tweetsRepository.findAll()).thenReturn(tweetsList);
		TweetResponse actualAllTweets = tweetServiceImpl.getAllTweets();
		assertEquals("Data Found!", actualAllTweets.getStatus());
		assertEquals(1, actualAllTweets.getTweetDtos().size());
		verify(tweetsRepository).findAll();
	}

	@Test
	public void testGetAllTweets3() {
		Tweets tweets = mock(Tweets.class);
		when(tweets.getId()).thenReturn(123L);
		when(tweets.getDate()).thenReturn(null);
		doNothing().when(tweets).setDate((Date) any());
		doNothing().when(tweets).setId((Long) any());
		doNothing().when(tweets).setLikes((Set<String>) any());
		doNothing().when(tweets).setReply((List<Reply>) any());
		doNothing().when(tweets).setTweet((String) any());
		doNothing().when(tweets).setUsername((String) any());
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		tweets.setId(123L);
		tweets.setLikes(new HashSet<>());
		tweets.setReply(new ArrayList<>());
		tweets.setTweet("Data Found!");
		tweets.setUsername("janedoe");

		ArrayList<Tweets> tweetsList = new ArrayList<>();
		tweetsList.add(tweets);
		when(tweetsRepository.findAll()).thenReturn(tweetsList);
		assertEquals("Error Occured!", tweetServiceImpl.getAllTweets().getStatus());
		verify(tweetsRepository).findAll();
		verify(tweets).getId();
		verify(tweets).getDate();
		verify(tweets).setDate((Date) any());
		verify(tweets).setId((Long) any());
		verify(tweets).setLikes((Set<String>) any());
		verify(tweets).setReply((List<Reply>) any());
		verify(tweets).setTweet((String) any());
		verify(tweets).setUsername((String) any());
	}

	@Test
	public void testGetTweetsByUsername() {
		ArrayList<Tweets> tweetsList = new ArrayList<>();
		when(tweetsRepository.findByUsername((String) any())).thenReturn(tweetsList);
		TweetResponse actualTweetsByUsername = tweetServiceImpl.getTweetsByUsername("janedoe");
		assertEquals("Data Found!", actualTweetsByUsername.getStatus());
		List<TweetDto> tweetDtos = actualTweetsByUsername.getTweetDtos();
		assertEquals(tweetsList, tweetDtos);
		assertTrue(tweetDtos.isEmpty());
		verify(tweetsRepository).findByUsername((String) any());
	}

	@Test
	public void testGetTweetsByUsername2() {
		Tweets tweets = new Tweets();
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		tweets.setId(123L);
		tweets.setLikes(new HashSet<>());
		tweets.setReply(new ArrayList<>());
		tweets.setTweet("Data Found!");
		tweets.setUsername("janedoe");

		ArrayList<Tweets> tweetsList = new ArrayList<>();
		tweetsList.add(tweets);
		when(tweetsRepository.findByUsername((String) any())).thenReturn(tweetsList);
		TweetResponse actualTweetsByUsername = tweetServiceImpl.getTweetsByUsername("janedoe");
		assertEquals("Data Found!", actualTweetsByUsername.getStatus());
		assertEquals(1, actualTweetsByUsername.getTweetDtos().size());
		verify(tweetsRepository).findByUsername((String) any());
	}

	@Test
	public void testGetTweetsByUsername3() {
		Tweets tweets = mock(Tweets.class);
		when(tweets.getId()).thenReturn(123L);
		when(tweets.getDate()).thenReturn(null);
		doNothing().when(tweets).setDate((Date) any());
		doNothing().when(tweets).setId((Long) any());
		doNothing().when(tweets).setLikes((Set<String>) any());
		doNothing().when(tweets).setReply((List<Reply>) any());
		doNothing().when(tweets).setTweet((String) any());
		doNothing().when(tweets).setUsername((String) any());
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		tweets.setId(123L);
		tweets.setLikes(new HashSet<>());
		tweets.setReply(new ArrayList<>());
		tweets.setTweet("Data Found!");
		tweets.setUsername("janedoe");

		ArrayList<Tweets> tweetsList = new ArrayList<>();
		tweetsList.add(tweets);
		when(tweetsRepository.findByUsername((String) any())).thenReturn(tweetsList);
		assertEquals("Error Occured!", tweetServiceImpl.getTweetsByUsername("janedoe").getStatus());
		verify(tweetsRepository).findByUsername((String) any());
		verify(tweets).getId();
		verify(tweets).getDate();
		verify(tweets).setDate((Date) any());
		verify(tweets).setId((Long) any());
		verify(tweets).setLikes((Set<String>) any());
		verify(tweets).setReply((List<Reply>) any());
		verify(tweets).setTweet((String) any());
		verify(tweets).setUsername((String) any());
	}

	@Test
	public void testPostTweet() {
		Tweets tweets = new Tweets();
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		tweets.setId(123L);
		tweets.setLikes(new HashSet<>());
		tweets.setReply(new ArrayList<>());
		tweets.setTweet("Tweet");
		tweets.setUsername("janedoe");
		when(tweetsRepository.save((Tweets) any())).thenReturn(tweets);
		TweetResponse actualPostTweetResult = tweetServiceImpl.postTweet(new PostTweet("janedoe", "Tweet"));
		assertEquals("Tweet Posted!", actualPostTweetResult.getStatus());
		assertEquals(1, actualPostTweetResult.getTweetDtos().size());
		verify(tweetsRepository).save((Tweets) any());
	}

	@Test
	public void testPostTweet2() {
		Tweets tweets = new Tweets();
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		tweets.setId(123L);
		tweets.setLikes(new HashSet<>());
		tweets.setReply(new ArrayList<>());
		tweets.setTweet("Tweet");
		tweets.setUsername("janedoe");
		when(tweetsRepository.save((Tweets) any())).thenReturn(tweets);
		assertEquals("Error Occured!", tweetServiceImpl.postTweet(null).getStatus());
	}

	@Test
	public void testDeleteTweet() {
		doNothing().when(tweetsRepository).deleteById((Long) any());
		assertEquals("Deleted Successfully!", tweetServiceImpl.deleteTweet("janedoe", 123L).getStatus());
		verify(tweetsRepository).deleteById((Long) any());
	}

	@Test
	public void testReplyTweet() {
		Tweets tweets = new Tweets();
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		tweets.setId(123L);
		tweets.setLikes(new HashSet<>());
		tweets.setReply(new ArrayList<>());
		tweets.setTweet("Tweet");
		tweets.setUsername("janedoe");
		Optional<Tweets> ofResult = Optional.of(tweets);
		when(tweetsRepository.findById((Long) any())).thenReturn(ofResult);
		assertEquals("Error Occured!", tweetServiceImpl.replyTweet(new TweetReply()).getStatus());
		verify(tweetsRepository).findById((Long) any());
	}

	@Test
	public void testReplyTweet2() {
		when(tweetsRepository.findById((Long) any())).thenReturn(Optional.empty());
		assertEquals("Replied Successfully!", tweetServiceImpl.replyTweet(new TweetReply()).getStatus());
		verify(tweetsRepository).findById((Long) any());
	}

	@Test
	public void testReplyTweet3() {
		Tweets tweets = new Tweets();
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		tweets.setId(123L);
		tweets.setLikes(new HashSet<>());
		tweets.setReply(new ArrayList<>());
		tweets.setTweet("Tweet");
		tweets.setUsername("janedoe");
		Optional<Tweets> ofResult = Optional.of(tweets);

		Tweets tweets1 = new Tweets();
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets1.setDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
		tweets1.setId(123L);
		tweets1.setLikes(new HashSet<>());
		tweets1.setReply(new ArrayList<>());
		tweets1.setTweet("Tweet");
		tweets1.setUsername("janedoe");
		when(tweetsRepository.save((Tweets) any())).thenReturn(tweets1);
		when(tweetsRepository.findById((Long) any())).thenReturn(ofResult);
		assertEquals("Replied Successfully!",
				tweetServiceImpl.replyTweet(new TweetReply(123L, new ArrayList<>())).getStatus());
		verify(tweetsRepository).save((Tweets) any());
		verify(tweetsRepository).findById((Long) any());
	}

	@Test
	public void testReplyTweet4() {
		Tweets tweets = new Tweets();
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		tweets.setId(123L);
		tweets.setLikes(new HashSet<>());
		tweets.setReply(new ArrayList<>());
		tweets.setTweet("Tweet");
		tweets.setUsername("janedoe");
		Optional<Tweets> ofResult = Optional.of(tweets);

		Tweets tweets1 = new Tweets();
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets1.setDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
		tweets1.setId(123L);
		tweets1.setLikes(new HashSet<>());
		tweets1.setReply(new ArrayList<>());
		tweets1.setTweet("Tweet");
		tweets1.setUsername("janedoe");
		when(tweetsRepository.save((Tweets) any())).thenReturn(tweets1);
		when(tweetsRepository.findById((Long) any())).thenReturn(ofResult);

		Reply reply = new Reply();
		LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
		reply.setDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
		reply.setReply("Replied Successfully!");
		reply.setUsername("janedoe");

		ArrayList<Reply> replyList = new ArrayList<>();
		replyList.add(reply);
		assertEquals("Replied Successfully!", tweetServiceImpl.replyTweet(new TweetReply(123L, replyList)).getStatus());
		verify(tweetsRepository).save((Tweets) any());
		verify(tweetsRepository).findById((Long) any());
	}

	@Test
	public void testReplyTweet5() {
		Tweets tweets = new Tweets();
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		tweets.setId(123L);
		tweets.setLikes(new HashSet<>());
		tweets.setReply(new ArrayList<>());
		tweets.setTweet("Tweet");
		tweets.setUsername("janedoe");
		Optional<Tweets> ofResult = Optional.of(tweets);

		Tweets tweets1 = new Tweets();
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets1.setDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
		tweets1.setId(123L);
		tweets1.setLikes(new HashSet<>());
		tweets1.setReply(new ArrayList<>());
		tweets1.setTweet("Tweet");
		tweets1.setUsername("janedoe");
		when(tweetsRepository.save((Tweets) any())).thenReturn(tweets1);
		when(tweetsRepository.findById((Long) any())).thenReturn(ofResult);
		assertEquals("Error Occured!", tweetServiceImpl.replyTweet(null).getStatus());
	}

	@Test
	public void testLikeTweet() {
		Tweets tweets = new Tweets();
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		tweets.setId(123L);
		tweets.setLikes(new HashSet<>());
		tweets.setReply(new ArrayList<>());
		tweets.setTweet("Tweet");
		tweets.setUsername("janedoe");
		Optional<Tweets> ofResult = Optional.of(tweets);

		Tweets tweets1 = new Tweets();
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets1.setDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
		tweets1.setId(123L);
		tweets1.setLikes(new HashSet<>());
		tweets1.setReply(new ArrayList<>());
		tweets1.setTweet("Tweet");
		tweets1.setUsername("janedoe");
		when(tweetsRepository.save((Tweets) any())).thenReturn(tweets1);
		when(tweetsRepository.findById((Long) any())).thenReturn(ofResult);
		assertEquals("Like/Dislike Success!", tweetServiceImpl.likeTweet(123L, "janedoe").getStatus());
		verify(tweetsRepository).save((Tweets) any());
		verify(tweetsRepository).findById((Long) any());
	}

	@Test
	public void testLikeTweet2() {
		Tweets tweets = new Tweets();
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		tweets.setId(123L);
		tweets.setLikes(new HashSet<>());
		tweets.setReply(new ArrayList<>());
		tweets.setTweet("Tweet");
		tweets.setUsername("janedoe");
		when(tweetsRepository.save((Tweets) any())).thenReturn(tweets);
		when(tweetsRepository.findById((Long) any())).thenReturn(Optional.empty());
		assertEquals("Like/Dislike Success!", tweetServiceImpl.likeTweet(123L, "janedoe").getStatus());
		verify(tweetsRepository).findById((Long) any());
	}

	@Test
	public void testUpdateTweet() {
		Tweets tweets = new Tweets();
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		tweets.setId(123L);
		tweets.setLikes(new HashSet<>());
		tweets.setReply(new ArrayList<>());
		tweets.setTweet("Tweet");
		tweets.setUsername("janedoe");
		Optional<Tweets> ofResult = Optional.of(tweets);

		Tweets tweets1 = new Tweets();
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets1.setDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
		tweets1.setId(123L);
		tweets1.setLikes(new HashSet<>());
		tweets1.setReply(new ArrayList<>());
		tweets1.setTweet("Tweet");
		tweets1.setUsername("janedoe");
		when(tweetsRepository.save((Tweets) any())).thenReturn(tweets1);
		when(tweetsRepository.findById((Long) any())).thenReturn(ofResult);
		assertEquals("Tweet Updated!", tweetServiceImpl.updateTweet(new UpdateTweet()).getStatus());
		verify(tweetsRepository).save((Tweets) any());
		verify(tweetsRepository).findById((Long) any());
	}

	@Test
	public void testUpdateTweet2() {
		Tweets tweets = new Tweets();
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		tweets.setId(123L);
		tweets.setLikes(new HashSet<>());
		tweets.setReply(new ArrayList<>());
		tweets.setTweet("Tweet");
		tweets.setUsername("janedoe");
		when(tweetsRepository.save((Tweets) any())).thenReturn(tweets);
		when(tweetsRepository.findById((Long) any())).thenReturn(Optional.empty());
		assertEquals("Tweet Updated!", tweetServiceImpl.updateTweet(new UpdateTweet()).getStatus());
		verify(tweetsRepository).findById((Long) any());
	}

	@Test
	public void testUpdateTweet3() {
		Tweets tweets = new Tweets();
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		tweets.setId(123L);
		tweets.setLikes(new HashSet<>());
		tweets.setReply(new ArrayList<>());
		tweets.setTweet("Tweet");
		tweets.setUsername("janedoe");
		Optional<Tweets> ofResult = Optional.of(tweets);

		Tweets tweets1 = new Tweets();
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		tweets1.setDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
		tweets1.setId(123L);
		tweets1.setLikes(new HashSet<>());
		tweets1.setReply(new ArrayList<>());
		tweets1.setTweet("Tweet");
		tweets1.setUsername("janedoe");
		when(tweetsRepository.save((Tweets) any())).thenReturn(tweets1);
		when(tweetsRepository.findById((Long) any())).thenReturn(ofResult);
		assertEquals("Error Occured!", tweetServiceImpl.updateTweet(null).getStatus());
	}
}

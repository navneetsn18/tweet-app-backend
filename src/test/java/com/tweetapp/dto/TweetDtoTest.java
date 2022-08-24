package com.tweetapp.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TweetDtoTest {

    @Test
  public void testConstructor() {
        TweetDto actualTweetDto = new TweetDto();
        actualTweetDto.setDate("2020-03-01");
        actualTweetDto.setId(123L);
        HashSet<String> stringSet = new HashSet<>();
        actualTweetDto.setLikes(stringSet);
        ArrayList<Reply> replyList = new ArrayList<>();
        actualTweetDto.setReply(replyList);
        actualTweetDto.setTime("15:26:56");
        actualTweetDto.setTweet("Tweet");
        actualTweetDto.setUsername("janedoe");
        assertEquals("2020-03-01", actualTweetDto.getDate());
        assertEquals(123L, actualTweetDto.getId().longValue());
        assertSame(stringSet, actualTweetDto.getLikes());
        assertSame(replyList, actualTweetDto.getReply());
        assertEquals("15:26:56", actualTweetDto.getTime());
        assertEquals("Tweet", actualTweetDto.getTweet());
        assertEquals("janedoe", actualTweetDto.getUsername());
    }

    @Test
   public void testConstructor2() {
        HashSet<String> likes = new HashSet<>();
        ArrayList<Reply> replyList = new ArrayList<>();
        TweetDto actualTweetDto = new TweetDto(123L, "2020-03-01", "Time", "janedoe", "Tweet", likes, replyList);
        actualTweetDto.setDate("2020-03-01");
        actualTweetDto.setId(123L);
        HashSet<String> stringSet = new HashSet<>();
        actualTweetDto.setLikes(stringSet);
        ArrayList<Reply> replyList1 = new ArrayList<>();
        actualTweetDto.setReply(replyList1);
        actualTweetDto.setTime("15:26:56");
        actualTweetDto.setTweet("Tweet");
        actualTweetDto.setUsername("janedoe");
        assertEquals("2020-03-01", actualTweetDto.getDate());
        assertEquals(123L, actualTweetDto.getId().longValue());
        assertSame(stringSet, actualTweetDto.getLikes());
        List<Reply> reply = actualTweetDto.getReply();
        assertSame(replyList1, reply);
        assertEquals(replyList, reply);
        assertEquals("15:26:56", actualTweetDto.getTime());
        assertEquals("Tweet", actualTweetDto.getTweet());
        assertEquals("janedoe", actualTweetDto.getUsername());
    }
}


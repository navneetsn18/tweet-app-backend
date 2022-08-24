package com.tweetapp.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class TweetAppExceptionsTest {
  
    @Test
  public  void testConstructor() {
        TweetAppExceptions actualTweetAppExceptions = new TweetAppExceptions();
        assertNull(actualTweetAppExceptions.getCause());
        assertEquals(0, actualTweetAppExceptions.getSuppressed().length);
        assertNull(actualTweetAppExceptions.getMessage());
        assertNull(actualTweetAppExceptions.getLocalizedMessage());
    }

    @Test
   public void testConstructor2() {
        TweetAppExceptions actualTweetAppExceptions = new TweetAppExceptions("Exceptions");
        assertNull(actualTweetAppExceptions.getCause());
        assertEquals(0, actualTweetAppExceptions.getSuppressed().length);
        assertEquals("Exceptions", actualTweetAppExceptions.getMessage());
        assertEquals("Exceptions", actualTweetAppExceptions.getLocalizedMessage());
    }
}


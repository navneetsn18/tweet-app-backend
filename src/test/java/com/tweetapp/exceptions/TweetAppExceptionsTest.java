package com.tweetapp.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class TweetAppExceptionsTest {
    /**
     * Method under test: {@link TweetAppExceptions#TweetAppExceptions()}
     */
    @Test
    void testConstructor() {
        TweetAppExceptions actualTweetAppExceptions = new TweetAppExceptions();
        assertNull(actualTweetAppExceptions.getCause());
        assertEquals(0, actualTweetAppExceptions.getSuppressed().length);
        assertNull(actualTweetAppExceptions.getMessage());
        assertNull(actualTweetAppExceptions.getLocalizedMessage());
    }

    /**
     * Method under test: {@link TweetAppExceptions#TweetAppExceptions(String)}
     */
    @Test
    void testConstructor2() {
        TweetAppExceptions actualTweetAppExceptions = new TweetAppExceptions("Exceptions");
        assertNull(actualTweetAppExceptions.getCause());
        assertEquals(0, actualTweetAppExceptions.getSuppressed().length);
        assertEquals("Exceptions", actualTweetAppExceptions.getMessage());
        assertEquals("Exceptions", actualTweetAppExceptions.getLocalizedMessage());
    }
}


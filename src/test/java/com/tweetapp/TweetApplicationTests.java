package com.tweetapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TweetApplicationTests {
	@Test
	public void test() {
		String test = "test";
		assertEquals("test", test);
	}
}

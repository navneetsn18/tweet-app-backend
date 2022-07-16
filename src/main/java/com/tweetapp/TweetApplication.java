package com.tweetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.tweetapp.controller.TweetAppController;
import com.tweetapp.exceptions.TweetAppExceptions;

@SpringBootApplication
public class TweetApplication {

	private static TweetAppController tweetAppController;

	public static void main(String[] args) throws TweetAppExceptions {
		ApplicationContext applicationContext = SpringApplication.run(TweetApplication.class, args);
		tweetAppController = applicationContext.getBean(TweetAppController.class);
		tweetAppController.startApp();
	}
}

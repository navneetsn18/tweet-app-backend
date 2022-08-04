package com.tweetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.tweetapp.controller.TweetAppController;

@SpringBootApplication
public class TweetApplication {

	private static TweetAppController tweetAppController;

	public static void main(String[] args){
		SpringApplication.run(TweetApplication.class, args);
	}
}

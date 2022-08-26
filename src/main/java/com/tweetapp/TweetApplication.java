package com.tweetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class TweetApplication {
	public static void main(String[] args) {
		System.out.println("Tweet App Started At Port 8081..");
		SpringApplication.run(TweetApplication.class, args);
	}
}

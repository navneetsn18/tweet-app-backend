package com.tweetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.exceptions.TweetAppExceptions;
import com.tweetapp.model.Tweets;
import com.tweetapp.model.User;
import com.tweetapp.service.TweetsService;
import com.tweetapp.service.UserService;

@RestController
@RequestMapping("/api/v1.0/tweets/")
public class TweetAppController {

	@Autowired
	private UserService userService;

	@Autowired
	private TweetsService tweetsService;

	private static String loggedInUser = "";

	@PostMapping(value = "register", produces = "application/json")
	public ResponseEntity<?> register(@RequestBody User user) {
		return null;
	}

	@GetMapping(value = "login", produces = "application/json")
	public ResponseEntity<?> login(@RequestBody String userLogin) {
		return null;
	}

	@GetMapping(value = "{username}/forgot")
	public void forgotPassword() {

	}

	@GetMapping(value = "all")
	public void getAllTweets() {

	}

	@GetMapping(value = "users/all")
	public void getAllUsers() {

	}

	@GetMapping(value = "user/search/username")
	public void searchByUsername() {

	}

	@GetMapping(value = "username")
	public void getAllTweetsOfUser() {

	}

	@PostMapping(value = "{username}/add")
	public void postNewTweet() {

	}

	@PutMapping(value = "{username}/update/{id}")
	public void updateATweet() {

	}

	@DeleteMapping(value = "{username}/delete/{id}")
	public void deleteTweet() {

	}

	@PutMapping(value = "{username}/like/{id}")
	public void likeTweet() {

	}

	@PostMapping(value = "{username}/reply/{id}")
	public void replyToTweet() {

	}

}

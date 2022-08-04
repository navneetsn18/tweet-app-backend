package com.tweetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping(value = "register",produces = "application/json")
	public ResponseEntity<String> register(@RequestBody User user) {
		try {
			boolean result = userService.registerUser(user);
			if (result) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("{\"message\":\"RegistrationSuccessfull\"}");
			}
		} catch (TweetAppExceptions e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"" + e.getMessage() + "\"}");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
	}

	@PostMapping(value = "login")
	public void login() {

	}
}

package com.tweetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dto.request.TweetRequest;
import com.tweetapp.dto.request.UserRequest;
import com.tweetapp.dto.response.TweetResponse;
import com.tweetapp.dto.response.UserResponse;
import com.tweetapp.service.TweetsService;
import com.tweetapp.service.UserService;

@RestController
@RequestMapping("/api/v1.0/tweets/")
public class TweetAppController {

	@Autowired
	private UserService userService;

	@Autowired
	private TweetsService tweetsService;

	@PostMapping(value = "register", produces = "application/json")
	public ResponseEntity<?> register(@RequestBody UserRequest userRequest) {
		UserResponse userResponse = userService.registerUser(userRequest);
		if (userResponse.getStatus().equals("User Added!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
	}

	@GetMapping(value = "forgotpassword", produces = "application/json")
	public ResponseEntity<?> forgotPassword(@RequestBody UserRequest userRequest) {
		UserResponse userResponse = userService.forgotPassword(userRequest);
		if (userResponse.getStatus().equals("Password Successfully Changed")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
	}

	@GetMapping(value = "users/all", produces = "application/json")
	public ResponseEntity<?> getAllUsers() {
		UserResponse userResponse = userService.getAllUsers();
		if (userResponse.getStatus().equals("Data Found!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
	}

	@GetMapping(value = "user/search/{username}", produces = "application/json")
	public ResponseEntity<?> searchByUsername(@PathVariable(name = "username") String username) {
		UserResponse userResponse = userService.findUser(username);
		if (userResponse.getStatus().equals("Data Found!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
	}

	@GetMapping(value = "getallloggedinusers", produces = "application/json")
	public ResponseEntity<?> getAllLoggedInUsers() {
		UserResponse userResponse = userService.getAllLoggedInUsers();
		if (userResponse.getStatus().equals("Data Found!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
	}

	@GetMapping(value = "all", produces = "application/json")
	public ResponseEntity<?> getAllTweets() {
		TweetResponse tweetResponse = tweetsService.getAllTweets();
		if (tweetResponse.getStatus().equals("Data Found!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

	@GetMapping(value = "tweets/{username}", produces = "application/json")
	public ResponseEntity<?> getAllTweetsOfUser(@PathVariable("username") String username) {
		TweetResponse tweetResponse = tweetsService.getTweetsByUsername(username);
		if (tweetResponse.getStatus().equals("Data Found!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

	@PostMapping(value = "tweets/{username}/add", produces = "application/json")
	public ResponseEntity<?> postNewTweet(@RequestBody TweetRequest tweetRequest,
			@PathVariable("username") String username) {
		TweetResponse tweetResponse = tweetsService.postTweet(tweetRequest, username);
		if (tweetResponse.getStatus().equals("Tweet Posted!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

	@PutMapping(value = "tweets/{username}/update/{id}", produces = "application/json")
	public ResponseEntity<?> updateATweet(@RequestBody TweetRequest tweetRequest,
			@PathVariable("username") String username) {
		TweetResponse tweetResponse = tweetsService.updateTweet(tweetRequest, username);
		if (tweetResponse.getStatus().equals("Tweet Updated!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

	@DeleteMapping(value = "tweets/{username}/delete/{id}", produces = "application/json")
	public ResponseEntity<?> deleteTweet(@PathVariable("username") String username, @PathVariable("id") Long id) {
		TweetResponse tweetResponse = tweetsService.deleteTweet(username, id);
		if (tweetResponse.getStatus().equals("Deleted Successfully!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

	@PutMapping(value = "tweets/like/{username}", produces = "application/json")
	public ResponseEntity<?> likeTweet(@RequestBody TweetRequest tweetRequest,
			@PathVariable("username") String username) {
		TweetResponse tweetResponse = tweetsService.likeTweet(tweetRequest, username);
		if (tweetResponse.getStatus().equals("Like/Dislike Success!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

	@PostMapping(value = "tweets/reply", produces = "application/json")
	public ResponseEntity<?> replyToTweet(@RequestBody TweetRequest tweetRequest) {
		TweetResponse tweetResponse = tweetsService.replyTweet(tweetRequest);
		if (tweetResponse.getStatus().equals("Replied Successfully!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

}

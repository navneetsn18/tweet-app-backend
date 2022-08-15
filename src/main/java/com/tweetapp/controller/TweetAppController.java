package com.tweetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dto.request.ForgotPassword;
import com.tweetapp.dto.request.PostTweet;
import com.tweetapp.dto.request.Register;
import com.tweetapp.dto.request.TweetReply;
import com.tweetapp.dto.request.UpdateTweet;
import com.tweetapp.dto.response.TweetResponse;
import com.tweetapp.dto.response.UserResponse;
import com.tweetapp.service.TweetsService;
import com.tweetapp.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1.0/")
public class TweetAppController {

	@Autowired
	private UserService userService;

	@Autowired
	private TweetsService tweetsService;

	@PostMapping(value = "user/register", produces = "application/json")
	public ResponseEntity<?> register(@RequestBody Register register) {
		UserResponse userResponse = userService.registerUser(register);
		if (userResponse.getStatus().equals("User Added!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
	}

	@PostMapping(value = "user/resetpassword", produces = "application/json")
	public ResponseEntity<?> resetPassword(@RequestBody ForgotPassword forgotPassword) {
		UserResponse userResponse = userService.resetPassword(forgotPassword);
		if (userResponse.getStatus().equals("Password Successfully Changed")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
	}
	
	@PostMapping(value = "user/forgotpassword/{username}", produces = "application/json")
	public ResponseEntity<?> forgotPassword(@PathVariable(name = "username") String username) {
		UserResponse userResponse = userService.forgotPassword(username);
		if (userResponse.getStatus().equals("Password Successfully Changed")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
	}

	@GetMapping(value = "user/all", produces = "application/json")
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

	@GetMapping(value = "user/getallloggedinusers", produces = "application/json")
	public ResponseEntity<?> getAllLoggedInUsers() {
		UserResponse userResponse = userService.getAllLoggedInUsers();
		if (userResponse.getStatus().equals("Data Found!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
	}
	
	@GetMapping(value = "user/logout/{username}", produces = "application/json")
	public ResponseEntity<?> logout(@PathVariable(name = "username") String username) {
		UserResponse userResponse = userService.logout(username);
		if (userResponse.getStatus().equals("Logged Out!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
	}

	@GetMapping(value = "tweets/all", produces = "application/json")
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

	@PostMapping(value = "tweets/add", produces = "application/json")
	public ResponseEntity<?> postNewTweet(@RequestBody PostTweet postTweet) {
		TweetResponse tweetResponse = tweetsService.postTweet(postTweet);
		if (tweetResponse.getStatus().equals("Tweet Posted!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

	@PutMapping(value = "tweets/update", produces = "application/json")
	public ResponseEntity<?> updateATweet(@RequestBody UpdateTweet updateTweet) {
		TweetResponse tweetResponse = tweetsService.updateTweet(updateTweet);
		if (tweetResponse.getStatus().equals("Tweet Updated!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

	@DeleteMapping(value = "tweets/delete/{username}/{id}", produces = "application/json")
	public ResponseEntity<?> deleteTweet(@PathVariable("username") String username, @PathVariable("id") Long id) {
		TweetResponse tweetResponse = tweetsService.deleteTweet(username, id);
		if (tweetResponse.getStatus().equals("Deleted Successfully!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

	@PutMapping(value = "tweets/like/{username}/{id}", produces = "application/json")
	public ResponseEntity<?> likeTweet(@PathVariable("id") Long id, @PathVariable("username") String username) {
		TweetResponse tweetResponse = tweetsService.likeTweet(id, username);
		if (tweetResponse.getStatus().equals("Like/Dislike Success!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

	@PostMapping(value = "tweets/reply", produces = "application/json")
	public ResponseEntity<?> replyToTweet(@RequestBody TweetReply tweetReply) {
		TweetResponse tweetResponse = tweetsService.replyTweet(tweetReply);
		if (tweetResponse.getStatus().equals("Replied Successfully!")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

}

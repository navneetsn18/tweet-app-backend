package com.tweetapp.controller;

import static com.tweetapp.constants.Constants.DATA_FOUND;
import static com.tweetapp.constants.Constants.DELETED;
import static com.tweetapp.constants.Constants.LIKE_DISLIKE;
import static com.tweetapp.constants.Constants.LOGGED_OUT;
import static com.tweetapp.constants.Constants.PASSWORD_CHANGED;
import static com.tweetapp.constants.Constants.REPLIED;
import static com.tweetapp.constants.Constants.TOPIC;
import static com.tweetapp.constants.Constants.TWEET_POSTED;
import static com.tweetapp.constants.Constants.TWEET_UPDATED;
import static com.tweetapp.constants.Constants.USER_ADDED;
import static com.tweetapp.constants.Constants.FORWARD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
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
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1.0/")
public class TweetAppController {

	@Autowired
	private UserService userService;

	@Autowired
	private TweetsService tweetsService;

	private KafkaTemplate<String, String> kafkaTemplate;

	public TweetAppController(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@PostMapping(value = "user/register", produces = "application/json")
	public ResponseEntity<UserResponse> register(@RequestBody Register register) {
		UserResponse userResponse = userService.registerUser(register);
		if (userResponse.getStatus().equals(USER_ADDED)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
	}

	@PostMapping(value = "user/resetpassword", produces = "application/json")
	public ResponseEntity<UserResponse> resetPassword(@RequestBody ForgotPassword forgotPassword) {
		UserResponse userResponse = userService.resetPassword(forgotPassword);
		if (userResponse.getStatus().equals(PASSWORD_CHANGED)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
	}

	@GetMapping(value = "user/forgotpassword/{username}", produces = "application/json")
	public ResponseEntity<UserResponse> forgotPassword(@PathVariable(name = "username") String username) {
		UserResponse userResponse = userService.forgotPassword(username);
		if (userResponse.getStatus().equals(PASSWORD_CHANGED)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
	}

	@GetMapping(value = "user/all", produces = "application/json")
	public ResponseEntity<UserResponse> getAllUsers() {
		UserResponse userResponse = userService.getAllUsers();
		if (userResponse.getStatus().equals(DATA_FOUND)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
	}

	@GetMapping(value = "user/search/{username}", produces = "application/json")
	public ResponseEntity<UserResponse> searchByUsername(@PathVariable(name = "username") String username) {
		UserResponse userResponse = userService.findUser(username);
		if (userResponse.getStatus().equals(DATA_FOUND)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
	}

	@GetMapping(value = "user/getallloggedinusers", produces = "application/json")
	public ResponseEntity<UserResponse> getAllLoggedInUsers() {
		UserResponse userResponse = userService.getAllLoggedInUsers();
		if (userResponse.getStatus().equals(DATA_FOUND)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
	}

	@GetMapping(value = "user/logout/{username}", produces = "application/json")
	public ResponseEntity<UserResponse> logout(@PathVariable(name = "username") String username) {
		UserResponse userResponse = userService.logout(username);
		if (userResponse.getStatus().equals(LOGGED_OUT)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
	}

	@GetMapping(value = "tweets/all", produces = "application/json")
	public ResponseEntity<TweetResponse> getAllTweets() {
		TweetResponse tweetResponse = tweetsService.getAllTweets();
		if (tweetResponse.getStatus().equals(DATA_FOUND)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

	@GetMapping(value = "tweets/{username}", produces = "application/json")
	public ResponseEntity<TweetResponse> getAllTweetsOfUser(@PathVariable("username") String username) {
		TweetResponse tweetResponse = tweetsService.getTweetsByUsername(username);
		if (tweetResponse.getStatus().equals(DATA_FOUND)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

	@PostMapping(value = "tweets/add", produces = "application/json")
	public ResponseEntity<TweetResponse> postNewTweet(@RequestBody PostTweet postTweet) {
		TweetResponse tweetResponse = tweetsService.postTweet(postTweet);
		if (tweetResponse.getStatus().equals(TWEET_POSTED)) {
			kafkaTemplate.send(TOPIC, "Added Tweet : " + Long.toString(tweetResponse.getTweetDtos().get(0).getId())
					+ FORWARD + postTweet.getTweet());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

	@PutMapping(value = "tweets/update", produces = "application/json")
	public ResponseEntity<TweetResponse> updateATweet(@RequestBody UpdateTweet updateTweet) {
		TweetResponse tweetResponse = tweetsService.updateTweet(updateTweet);
		if (tweetResponse.getStatus().equals(TWEET_UPDATED)) {
			kafkaTemplate.send(TOPIC, "Updated Tweet : " + Long.toString(updateTweet.getId())
					+ FORWARD + updateTweet.getTweet());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

	@DeleteMapping(value = "tweets/delete/{username}/{id}", produces = "application/json")
	public ResponseEntity<TweetResponse> deleteTweet(@PathVariable("username") String username,
			@PathVariable("id") Long id) {
		TweetResponse tweetResponse = tweetsService.deleteTweet(username, id);
		if (tweetResponse.getStatus().equals(DELETED)) {
			kafkaTemplate.send(TOPIC, "Deleted Tweet : " + Long.toString(id) + " by --> " + username);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

	@PutMapping(value = "tweets/like/{username}/{id}", produces = "application/json")
	public ResponseEntity<TweetResponse> likeTweet(@PathVariable("id") Long id,
			@PathVariable("username") String username) {
		TweetResponse tweetResponse = tweetsService.likeTweet(id, username);
		if (tweetResponse.getStatus().equals(LIKE_DISLIKE)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

	@PostMapping(value = "tweets/reply", produces = "application/json")
	public ResponseEntity<TweetResponse> replyToTweet(@RequestBody TweetReply tweetReply) {
		TweetResponse tweetResponse = tweetsService.replyTweet(tweetReply);
		if (tweetResponse.getStatus().equals(REPLIED)) {
			kafkaTemplate.send(TOPIC, "Replied To Tweet : " + Long.toString(tweetReply.getId()) + " by "
					+ tweetReply.getReply().get(0).getUsername() + FORWARD + tweetReply.getReply().get(0).getReplyMsg());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tweetResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetResponse);
	}

}

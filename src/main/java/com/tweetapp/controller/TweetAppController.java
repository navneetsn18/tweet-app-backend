package com.tweetapp.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.tweetapp.TweetApplication;
import com.tweetapp.exceptions.TweetAppExceptions;
import com.tweetapp.impl.UserServiceImpl;
import com.tweetapp.model.Tweets;
import com.tweetapp.model.User;
import com.tweetapp.service.TweetsService;
import com.tweetapp.service.UserService;

@Component
public class TweetAppController {

	@Autowired
	private static UserService userService;

	@Autowired
	private static TweetsService tweetsService;

	private static String loggedInUser = "";

	private static void registerUser() throws TweetAppExceptions {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the below details:");
		System.out.println("Name: ");
		String name = scan.next();
		System.out.println("Username: ");
		String username = scan.next();
		System.out.println("Email: ");
		String email = scan.next();
		System.out.println("Password: ");
		String password = scan.next();
		User user = new User(username, name, email, password);
		if (userService.registerUser(user)) {
			System.out.println("Registration Successful");
		} else {
			System.out.println("Registration Unsuccessful");
		}
	}

	private static void userLogin() throws TweetAppExceptions {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Username or Email: ");
		String username = scan.next();
		System.out.println("Enter Password: ");
		String password = scan.next();
		if (userService.loginUser(username, password)) {
			System.out.println("Login Successful");
			loggedInUser = username;
			boolean login = true;
			afterLogin();
		} else {
			System.out.println("Login Unsuccessful");
		}
	}

	private static void forgotPassword() {
		Scanner scan = new Scanner(System.in);
		// Not Implemented yet
	}

	public static void afterLogin() throws TweetAppExceptions {
		Scanner scan = new Scanner(System.in);
		if (loggedInUser != "") {
			System.out.println("Welcome " + loggedInUser);
			System.out.println("Choose from the menu below:");
			System.out.println("1. Post a tweet");
			System.out.println("2. View Your Tweets");
			System.out.println("3. View all tweets");
			System.out.println("4. View All User");
			System.out.println("5. Reset Password");
			System.out.println("6. Logout");
			System.out.println("Enter your choice: ");
			int choice = scan.nextInt();
			if (choice == 1) {
				Tweets tweet = new Tweets();
				tweetsService.postTweet(loggedInUser, tweet);
			} else if (choice == 2) {
				List<Tweets> tweets = tweetsService.getTweets(loggedInUser);
				for (Tweets tweet : tweets) {
					System.out.println(
							"-----------------------------------------------------------------------------------");
					System.out.println("Username: " + tweet.getUser().getUsername());
					System.out.println("Date: " + tweet.getDate());
					System.out.println("Tweet: " + tweet.getTweet());
					System.out.println("");
				}
			} else if (choice == 3) {
				List<Tweets> tweets = tweetsService.getAllTweets();
				for (Tweets tweet : tweets) {
					System.out.println(
							"-----------------------------------------------------------------------------------");
					System.out.println("Username: " + tweet.getUser().getUsername());
					System.out.println("Date: " + tweet.getDate());
					System.out.println("Tweet: " + tweet.getTweet());
					System.out.println("");
				}
			} else if (choice == 4) {
				List<User> users = userService.getAllUsers();
				for (User user : users) {
					System.out.println("Name: " + user.getName() + " --> Username: " + user.getUsername());
				}
			} else if (choice == 5) {
				System.out.println("Enter your password: ");
				String oldPassword = scan.next();
				System.out.println("Enter new password: ");
				String newPassword = scan.next();
				userService.resetPassword(oldPassword, newPassword);
			} else if (choice == 6) {
				userService.logout();
				loggedInUser = "";
				System.out.println("Logout Successful");
			} else {
				System.out.println("Enter valid choice.");
			}
		}
	}

	public void startApp() throws TweetAppExceptions {
		Scanner scan = new Scanner(System.in);
		boolean temp = true;
		userService.logout();
		while (temp) {
			System.out.println("Welcome to Tweet App");
			System.out.println("Choose from the menu below:");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Forgot Password");
			System.out.println("4. Exit");
			System.out.println("Enter your choice: ");
			int choice = scan.nextInt();
			if (choice == 1) {
				registerUser();
			} else if (choice == 2) {
				userLogin();
			} else if (choice == 3) {
				forgotPassword();
			} else if (choice == 4) {
				System.out.println("Thanks for using the app.");
				temp = false;
			} else {
				System.out.println("Enter valid choice.");
			}
		}
	}

}

package com.tweetapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tweetapp.model.Tweets;
import com.tweetapp.model.User;
import com.tweetapp.service.TweetsService;
import com.tweetapp.service.UserService;

@Component
public class TweetAppController {

	@Autowired
	private UserService userService;

	@Autowired
	private TweetsService tweetsService;

	private static String loggedInUser = "";

	private void registerUser() {
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
		try {
			if (userService.registerUser(user)) {
				System.out.println("Registration Successful");
			}
		} catch (Exception e) {
			System.out.println("Registration Unsuccessful");
		}
	}

	private void userLogin() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Username or Email: ");
		String username = scan.next();
		System.out.println("Enter Password: ");
		String password = scan.next();
		try {
			User user = userService.loginUser(username, password);
			if (user != null) {
				System.out.println("Login Successful");
				loggedInUser = user.getUsername();
				afterLogin();
			}
		} catch (Exception e) {
			System.out.println("Login Unsuccessful");
		}
	}

	private void forgotPassword() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Email Id: ");
		String email = scan.next();
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches() == false) {
			System.out.println("Invalid Email Format.");
		} else {
			List<User> users = userService.getAllUsers();
			for (User user : users) {
				if (user.getEmail().equals(email)) {
					String oldPassword = user.getPassword();
					System.out.println("Enter new password: ");
					String newPassword = scan.next();
					try {
						userService.resetPassword(oldPassword, newPassword);
						return;
					} catch (Exception e) {
						System.out.println("");
					}
				}
			}
		}
	}

	public void afterLogin() {
		Scanner scan = new Scanner(System.in);
		while (!loggedInUser.equals("")) {
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
				System.out.println("Enter Your Tweet: ");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String twe = null;
				try {
					twe = reader.readLine();
				} catch (IOException e) {
					System.out.println("Error Occured");
				}
				tweet.setTweet(twe);
				tweet.setUsername(loggedInUser);
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				tweet.setDate(dtf.format(now));
				tweetsService.postTweet(loggedInUser, tweet);
			} else if (choice == 2) {
				List<Tweets> tweets = tweetsService.getTweets(loggedInUser);
				for (Tweets tweet : tweets) {
					System.out.println(
							"-----------------------------------------------------------------------------------");
					System.out.println("Username: " + tweet.getUsername());
					System.out.println("Date: " + tweet.getDate());
					System.out.println("Tweet: " + tweet.getTweet());
					System.out.println("");
				}
			} else if (choice == 3) {
				List<Tweets> tweets = tweetsService.getAllTweets();
				for (Tweets tweet : tweets) {
					System.out.println(
							"-----------------------------------------------------------------------------------");
					System.out.println("Username: " + tweet.getUsername());
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
				try {
					userService.resetPassword(oldPassword, newPassword);
				} catch (Exception e) {
					System.out.println("");
				}
			} else if (choice == 6) {
				userService.logout();
				loggedInUser = "";
				System.out.println("Logout Successful");
			} else {
				System.out.println("Enter valid choice.");
			}
		}
	}

	public void startApp() {
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

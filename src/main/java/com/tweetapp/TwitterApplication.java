package com.tweetapp;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.tweetapp.service.TweetAppService;

@SpringBootApplication
public class TwitterApplication {

	private static TweetAppService tweetAppService;

	private static void registerUser() {
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
		if (tweetAppService.regsiterUser(username, name, email, password)) {
			System.out.println("Registration Successful");
		} else {
			System.out.println("Registration Unsuccessful");
		}
	}

	private static void userLogin() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Username or Email: ");
		String username = scan.next();
		System.out.println("Enter Password: ");
		String password = scan.next();
		if (tweetAppService.loginUser(username, password)) {
			System.out.println("Login Successful");
		} else {
			System.out.println("Login Unsuccessful");
		}
	}

	private static void forgotPassword() {
		Scanner scan = new Scanner(System.in);
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ApplicationContext context = SpringApplication.run(TwitterApplication.class, args);
		tweetAppService = context.getBean(TweetAppService.class);
		boolean temp = true;
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

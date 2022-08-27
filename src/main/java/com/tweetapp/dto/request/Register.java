package com.tweetapp.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Register {
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;

	public Register(String firstName, String lastName, String username, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
	}
}

package com.tweetapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;
	private boolean loggedin;

	public UserDto(String firstName, String lastName, String username, String email, String password,
			boolean loggedin) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.loggedin = loggedin;
	}
}

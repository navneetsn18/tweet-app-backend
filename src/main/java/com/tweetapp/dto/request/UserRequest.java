package com.tweetapp.dto.request;

import java.io.Serializable;

import com.tweetapp.dto.UserDto;

public class UserRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private UserDto userDto;

	public UserRequest() {
		// TODO Auto-generated constructor stub
	}

	public UserRequest(UserDto userDto) {
		super();
		this.userDto = userDto;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

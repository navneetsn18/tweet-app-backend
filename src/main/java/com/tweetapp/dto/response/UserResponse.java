package com.tweetapp.dto.response;

import java.io.Serializable;
import java.util.List;

import com.tweetapp.dto.UserDto;

public class UserResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<UserDto> userDtos;
	private String status;

	public UserResponse() {
		// TODO Auto-generated constructor stub
	}

	public UserResponse(List<UserDto> userDtos, String status) {
		super();
		this.userDtos = userDtos;
		this.status = status;
	}

	public List<UserDto> getUserDtos() {
		return userDtos;
	}

	public void setUserDtos(List<UserDto> userDtos) {
		this.userDtos = userDtos;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

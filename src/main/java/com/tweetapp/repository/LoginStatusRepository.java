package com.tweetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweetapp.model.LoginStatus;

public interface LoginStatusRepository extends JpaRepository<LoginStatus, Integer>{
	
	public LoginStatus findById();

}

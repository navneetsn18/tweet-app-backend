package com.tweetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.LoginStatus;

@Repository
public interface LoginStatusRepository extends JpaRepository<LoginStatus, Integer>{
	
	public LoginStatus findById();
	
}

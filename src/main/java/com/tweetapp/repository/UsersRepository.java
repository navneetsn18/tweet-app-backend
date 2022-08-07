package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer>{

	public User findByEmail(String email);
	
	public User findByUsername(String username);
	
	public List<User> findByLoggedinTrue();
}

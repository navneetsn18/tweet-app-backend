package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.Tweets;

@Repository
public interface TweetsRepository extends JpaRepository<Tweets, Long> {
	
	public List<Tweets> findByUsername(String username);
	
}

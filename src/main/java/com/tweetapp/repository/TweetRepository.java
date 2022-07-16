package com.tweetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweetapp.model.Tweets;

public interface TweetRepository extends JpaRepository<Tweets, Integer> {

}

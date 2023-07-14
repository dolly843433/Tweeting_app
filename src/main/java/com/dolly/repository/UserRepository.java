package com.dolly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.dolly.Model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer>{
	@Modifying
	@Transactional

	@Query(value="Delete from user where tweet=?",nativeQuery = true)
	public void deleteTweet(String tweetId);
	
	@Query(value="select tweet from user where user_id=?",nativeQuery = true)
	public List<String> findAllTweetByUser(String user_id);
	
	@Query(value="select distinct user_id from user",nativeQuery = true)
	public List<String> distinctUser();
}

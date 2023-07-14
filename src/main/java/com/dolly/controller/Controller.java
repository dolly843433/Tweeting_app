package com.dolly.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dolly.Model.TweetModel;

import com.dolly.service.TweetService;

@RestController
public class Controller {
	@Autowired
	TweetService tweetservice;
	
	@GetMapping("/get")
	public ResponseEntity<Map<String,List<TweetModel>>> getall(){
		return new  ResponseEntity<>(tweetservice.getAllTweets(),HttpStatus.OK);
	}
	
	@GetMapping("/gets/{tweetId}")
	public ResponseEntity<TweetModel>getbyId(@PathVariable("tweetId")String tweetId){
		return new ResponseEntity<>(tweetservice.getATweetById(tweetId),HttpStatus.OK);
	}
	
	@PostMapping("/post/{userId}")
	public ResponseEntity<String>create(@PathVariable("userId") String  userId, @RequestBody TweetModel tweetmodel){
		return new  ResponseEntity<>(tweetservice.CreatTweet( userId,tweetmodel),HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{tweetId}/{userId}")

	
	public ResponseEntity<String>update(@RequestBody TweetModel tweetmodel,@PathVariable("tweetId") String  tweetId,@PathVariable("userId") String userId ){
		return new  ResponseEntity<>(tweetservice.UpdateTweetById(userId,tweetId,  tweetmodel),HttpStatus.OK);
	}
	

	@DeleteMapping("/delete/{tweetId}")
	public ResponseEntity<String>deletes(@PathVariable("tweetId")String tweetId){
		return new  ResponseEntity<>(tweetservice.deleteTweet(tweetId),HttpStatus.ACCEPTED);
	}
	}



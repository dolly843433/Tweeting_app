package com.dolly.service;

import java.util.List;
import java.util.Map;

import com.dolly.Model.TweetModel;

public interface TweetService {

	public String CreatTweet(String userId,TweetModel tweetModel);
	public Map<String,List<TweetModel>> getAllTweets();
	public TweetModel getATweetById(String tweetId);
	public String deleteTweet(String tweetId);
	public String UpdateTweetById(String userId,String tweetId,TweetModel tweetModel);
}

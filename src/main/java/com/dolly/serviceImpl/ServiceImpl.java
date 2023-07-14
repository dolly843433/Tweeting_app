package com.dolly.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dolly.Model.TweetModel;
import com.dolly.Model.UserModel;
import com.dolly.repository.TweetRepository;
import com.dolly.repository.UserRepository;
import com.dolly.service.TweetService;
import com.fasterxml.jackson.databind.util.JSONPObject;
@Service
public class ServiceImpl implements TweetService{
	
	@Autowired
	TweetRepository tweetRepository;
	@Autowired
	UserRepository userRepository;


	@Override
	public String CreatTweet(String userId, TweetModel tweetModel) {
		// TODO Auto-generated method stub
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now(); 
		tweetModel.setTweet_id(UUID.randomUUID().toString());
		tweetModel.setTime_stamp(dtf.format(now).toString());
		tweetRepository.save(tweetModel);
		UserModel model=new UserModel();
		model.setUser_id(userId);
		model.setTweet(tweetModel.getTweet_id());
		userRepository.save(model);
		
		return "Tweet Created";
	}

	@Override
	public TweetModel getATweetById(String tweetId) {
		// TODO Auto-generated method stub
		if(tweetRepository.findById(tweetId).isEmpty()) {
			return null;
		}
		return tweetRepository.findById(tweetId).get();
	}

	@Override
	public String deleteTweet(String tweetId) {
		// TODO Auto-generated method stub
		tweetRepository.deleteById(tweetId);
		userRepository.deleteTweet(tweetId);
		return "tweet deleted";
	}

	@Override
	public String UpdateTweetById(String userId, String tweetId,TweetModel tweetModel) {
		// TODO Auto-generated method stub
		if(userRepository.findAllTweetByUser(userId).isEmpty()) {
			return "User does not exist";
		}
		if(tweetRepository.findById(tweetId).isEmpty()) {
			return "Tweet Not Found";
		}
		List<String> l=userRepository.findAllTweetByUser(userId);
		int find=0;
		for(int i=0;i<l.size();i++) {
			if(l.get(i).equals(tweetId)) {
				find=1;
				break;
			}
		}
		if(find==0) {
			return "'"+tweetId+"' this tweet is not post by "+userId;
		}
		else {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now(); 
			TweetModel t=tweetRepository.findById(tweetId).get();
			t.setTweet_id(tweetId);
			t.setTime_stamp(dtf.format(now).toString());
			t.setContent(tweetModel.getContent());
			t.setUser_information(tweetModel.getUser_information());
			tweetRepository.save(t);
			return "tweet updated";
		}
	}

	@Override
	public Map<String,List<TweetModel>> getAllTweets() {
		// TODO Auto-generated method stub
		Map<String,List<TweetModel>> map=new HashMap<>();
		List<String> dUser=userRepository.distinctUser();
		for(int i=0;i<dUser.size();i++) {
			List<String> tweets=userRepository.findAllTweetByUser(dUser.get(i));
			List<TweetModel> tm=new ArrayList<>();
			for(int j=0;j<tweets.size();j++) {
				if(!tweetRepository.findById(tweets.get(j)).isEmpty()) {
				TweetModel tModel=tweetRepository.findById(tweets.get(j)).get();
			    tm.add(tModel);
				}
			}
			map.put(dUser.get(i), tm);
		}
		return map;
	}

}

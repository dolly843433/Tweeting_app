package com.dolly.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tweeting")
public class TweetModel {

	@Id
	private String tweet_id;
	@Column
	private String content;
	@Column
	private String time_stamp;
	@Column
	private String user_information;
	public TweetModel(String tweet_id, String content, String time_stamp, String user_information) {
		super();
		this.tweet_id = tweet_id;
		this.content = content;
		this.time_stamp = time_stamp;
		this.user_information = user_information;
	}
	public TweetModel() {
		super();
	}
	public String getTweet_id() {
		return tweet_id;
	}
	public void setTweet_id(String tweet_id) {
		this.tweet_id = tweet_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}
	public String getUser_information() {
		return user_information;
	}
	public void setUser_information(String user_information) {
		this.user_information = user_information;
	}
	
	
	
}

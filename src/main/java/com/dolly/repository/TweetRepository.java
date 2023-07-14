package com.dolly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dolly.Model.TweetModel;

public interface TweetRepository extends  JpaRepository<TweetModel, String>{

}

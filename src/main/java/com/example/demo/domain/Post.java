package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Post {

	@Id
	@GeneratedValue
	@JsonProperty("id")
	private Integer postId;
	
	@JsonProperty("description")
	private String postDescription;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	public Post() {
		super();
	}
	public Post(Integer postId, String postDescription, User user) {
		super();
		this.postId = postId;
		this.postDescription = postDescription;
		this.user = user;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getPostDescription() {
		return postDescription;
	}
	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	// Never Print User here are user & post has a relationship & may lead of stack overflow error.	
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", postDescription=" + postDescription + "]";
	}
	
	
	
}

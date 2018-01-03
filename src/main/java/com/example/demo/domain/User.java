package com.example.demo.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("Api Model Details for User!")
@Entity
public class User extends ResourceSupport{
	
	@Id
	@GeneratedValue
	private Integer userId;
	
	@Size(min=3, message="Name should have atleast 3 characters!")
	@ApiModelProperty("Name should have atleast 3 characters!")
	private String name;
	
	@Past(message="The Birthdate must be a past date")
	@ApiModelProperty("The Birthdate must be a past date")
	private Date birthDate;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	
	public User() {
		super();
	}
	public User(Integer userId, String name, Date birthDate) {
		super();
		this.userId = userId;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	

}

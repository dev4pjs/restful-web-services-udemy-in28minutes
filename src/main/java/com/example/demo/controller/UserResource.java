package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.domain.UserDaoService;
import com.example.demo.exception.UserNotFoundException;

@RestController
public class UserResource implements BaseController{

	private UserDaoService userDaoService;
	
	public UserResource(UserDaoService userDaoService){
		this.userDaoService = userDaoService;
	}
	
	// Retrieve all users
	@GetMapping(path="/users")
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<List<User>>(userDaoService.findAll(),getNoCacheHeaders(),HttpStatus.OK);
	}
	// retrieve user by id
	@GetMapping("/users/{id}")
	public Resource<User> getuserByid(@PathVariable("id") Integer id)throws UserNotFoundException{
		User user = userDaoService.findById(id);
		if(user ==null){
			throw new UserNotFoundException("id ="+id);
		}
		
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkToAlUsers = linkTo(methodOn(this.getClass()).getAllUsers());
		resource.add(linkToAlUsers.withRel("all-user"));
		return resource;
	}
	
	// Delete user
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") Integer id)throws UserNotFoundException{
		User user = userDaoService.deleteUser(id);
		if(user==null)
			throw new UserNotFoundException("id = "+id);
	}
	
	
	
	// save user
	@PostMapping("/users")
	public ResponseEntity<List<User>> saveUser(@Valid @RequestBody User user){
		//URI newResource = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return new ResponseEntity<List<User>>(userDaoService.save(user),getNoCacheHeaders(),HttpStatus.CREATED);
	}
	
	
}

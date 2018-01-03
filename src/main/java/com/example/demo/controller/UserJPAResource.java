package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostRepository;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import com.example.demo.exception.UserNotFoundException;

@RestController
public class UserJPAResource implements BaseController {

	private UserRepository userRepository;
	private PostRepository postRepository;

	public UserJPAResource(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	// Retrieve all users
	@GetMapping(path = "/jpa/users")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userRepository.findAll(), getNoCacheHeaders(), HttpStatus.OK);
	}

	// retrieve user by id
	@GetMapping(value = "/jpa/users/{id}")
	// public Resource<User> getuserByid(@PathVariable("id") Integer id) throws
	// UserNotFoundException {
	public ResponseEntity<User> getuserByid(@PathVariable("id") Integer id) throws UserNotFoundException {
		User user = userRepository.findByUserId(id);
		if(user == null)
			throw new UserNotFoundException("Id = "+id);
		/*
		 * Link link =
		 * ControllerLinkBuilder.linkTo(User.class).slash(user.getUserId()).
		 * withSelfRel(); user.add(link);
		 */

		/*
		 * Resource<User> resource = new Resource<User>(user);
		 * ControllerLinkBuilder linkToAlUsers =
		 * linkTo(methodOn(this.getClass()).getAllUsers());
		 * resource.add(linkToAlUsers.withRel("all-user")); return resource;
		 */
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	// Delete user
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable("id") Integer id) throws UserNotFoundException {
		if (userRepository.exists(id)) {
			userRepository.delete(id);
		} else
			throw new UserNotFoundException("id = " + id);
	}

	// save user
	@PostMapping("/jpa/users")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		// URI newResource =
		// ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		User newUser = userRepository.save(user);
		return new ResponseEntity<User>(newUser, getNoCacheHeaders(), HttpStatus.CREATED);
	}

	// Get all Post by a User
	@GetMapping("/jpa/users/{userId}/posts")
	public ResponseEntity<List<Post>> getAllPostsByUser(@PathVariable("userId") Integer userId)
			throws UserNotFoundException {
		if (userRepository.exists(userId)) {
			User user = userRepository.findOne(userId);
			List<Post> listOfPost = user.getPosts();
			return new ResponseEntity<List<Post>>(listOfPost, HttpStatus.OK);
		} else
			throw new UserNotFoundException("userId = " + userId);
	}

	// Create Post for a User
	@PostMapping("/jpa/users/{userId}/posts")
	public ResponseEntity<Object> createPost(@RequestBody Post post, @PathVariable("userId") Integer userId)
			throws UserNotFoundException {
		if (userRepository.exists(userId)) {
			User user = userRepository.findOne(userId);
			post.setUser(user);
			postRepository.save(post);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
					.buildAndExpand(post.getPostId()).toUri();
			return ResponseEntity.created(uri).build();
		} else
			throw new UserNotFoundException("userId = " + userId);
	}

}

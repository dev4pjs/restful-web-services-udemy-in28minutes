package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int userCount = 4;

	static {
		users.add(new User(1, "Purujit", new Date()));
		users.add(new User(2, "Prajjal", new Date()));
		users.add(new User(3, "Jayita", new Date()));
		users.add(new User(4, "Jasmin", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User findById(int userId) {
		for (User user : users) {
			if (user.getUserId() == userId) {
				return user;
			}
		}
		return null;
	}

	public User deleteUser(int id) {
		Iterator<User> it = users.iterator();
		while (it.hasNext()) {
			User user = it.next();
			if (user.getUserId() == id) {
				users.remove(user);
				return user;
			}
		}
		return null;
	}

	public List<User> save(User user) {
		if (user.getUserId() == null) {
			user.setUserId(++userCount);
		}
		users.add(user);
		return users;
	}
}

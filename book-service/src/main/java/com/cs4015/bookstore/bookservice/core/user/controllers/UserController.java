package com.cs4015.bookstore.bookservice.core.user.controllers;

import java.util.Optional;

import com.cs4015.bookstore.bookservice.core.user.model.User;
import com.cs4015.bookstore.bookservice.core.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping(value="/users/create", consumes="application/json", produces="application/json")
	public void createUser(@RequestBody User user) {
		userService.saveUser(user);
	}

	@GetMapping(value="/users/view/{id}", produces="application/json")
	public User getUserById(@PathVariable ("id") Long id) {
		Optional<User> user = userService.getUser(id);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@GetMapping(value="/users/view/username/{username}", produces="application/json")
	public User getUserByUsername(@PathVariable ("username") String username) {
		Optional<User> user = userService.getUser(username);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}
}

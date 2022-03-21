package com.cs4015.bookstore.bookservice.core.user.controllers;

import java.util.Optional;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.models.UserBooks;
import com.cs4015.bookstore.api.exceptions.InvalidInputException;
import com.cs4015.bookstore.bookservice.core.book.manager.UserBookManager;
import com.cs4015.bookstore.bookservice.core.user.model.User;
import com.cs4015.bookstore.bookservice.core.user.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "UserService", description =
		"REST API for user information.")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;

	@Autowired
	UserBookManager userBookManager;


	@Operation(
			summary = "Create a user profile and save into database.",
			description = "Create a user profile based on the user inforamtion that provide in the request body.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
			@ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
			@ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
			@ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
	})
	@PostMapping(value="/users/create", consumes="application/json", produces="application/json")
	public void createUser(@RequestBody User user) {
		logger.debug("POST, add a new user {}", user);
		userService.saveUser(user);
	}

	@Operation(
			summary = "Get the user profile by the given user id.",
			description = "Return a user profile if the user is found by the given user id. Otherwise an error will return.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
			@ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
			@ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
			@ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
	})
	@GetMapping(value="/users/view/{id}", produces="application/json")
	public User getUserById(@PathVariable ("id") Long id) {
		Optional<User> user = userService.getUser(id);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@Operation(
			summary = "Get the user profile by the given user id.",
			description = "Return a user profile if the user is found by the given user id. Otherwise an error will return.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
			@ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
			@ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
			@ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
	})
	@GetMapping(value="/users/view/username/{username}", produces="application/json")
	public User getUserByUsername(@PathVariable ("username") String username) {
		Optional<User> user = userService.getUser(username);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@Operation(
			summary = "Get all the books that the given user is on sell.",
			description = "Return a user-book list that contains all the books that the user are owned or sell.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
			@ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
			@ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
			@ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
	})
	@GetMapping(value="/users/{user_id}/books", produces="application/json")
	public UserBooks getUserBooks(@PathVariable ("user_id") long user_id){
		Optional<User> user = userService.getUser(user_id);
		if (user.isPresent()) {
			return userBookManager.getUsersBooks(user_id);
		}
		return null;
	}

	@Operation(
			summary = "Add a book to the given user id.",
			description = "Add a book to the giver user id. If the book is successfully add, a list of user books will be returned.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
			@ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
			@ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
			@ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
	})
	@PostMapping(value="/users/{user_id}/books", produces="application/json")
	public UserBooks addBookToUser(@PathVariable ("user_id") long user_id, @RequestBody Book book){
		Optional<User> user = userService.getUser(user_id);
		if (user.isPresent()) {
			return userBookManager.addBookToUser(user_id, book);
		}else{
			throw new InvalidInputException("The user is not exist: userId = " + user_id);
		}
	}
}

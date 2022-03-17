package com.cs4015.bookstore.bookservice.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

import com.cs4015.bookstore.bookservice.core.user.model.User;
import com.cs4015.bookstore.bookservice.core.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component("signupMB")
@RequestScoped
@Data
public class SignupMB {
	
	@Autowired
	private UserService bookService;

	@Autowired
	private MessageService messageService;

	private User user;

	@PostConstruct
	public void init() {
		user = new User();
	}

	public void createUser() {
		try {
			bookService.saveUser(user);
			messageService.showInfoMessage("User created successfully.");
		} catch (DataIntegrityViolationException e) {
			messageService.showErrorMessage("There is already a user with this username.");
		} catch (Exception e) {
			messageService.showErrorMessage("Failed to create user.", e.getMessage());
		}
	}
}

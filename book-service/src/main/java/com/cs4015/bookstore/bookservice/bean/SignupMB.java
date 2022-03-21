package com.cs4015.bookstore.bookservice.bean;

import javax.annotation.PostConstruct;

import com.cs4015.bookstore.api.core.user.models.User;
import com.cs4015.bookstore.api.core.user.services.UserService;
import com.cs4015.bookstore.bookservice.core.user.manager.UserManager;
import com.cs4015.bookstore.bookservice.util.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@Component("signupMB")
@RequestScope
@Data
public class SignupMB {
	
	@Autowired
	private UserManager userManager;

	@Autowired
	private MessageService messageService;

	private User user;

	@PostConstruct
	public void init() {
		user = new User();
	}

	public void createUser() {
		try {
			userManager.saveUser(user);
			messageService.showInfoMessage("User created successfully.");
		} catch (DataIntegrityViolationException e) {
			messageService.showErrorMessage("There is already a user with this username.");
		} catch (Exception e) {
			messageService.showErrorMessage("Failed to create user.", e.getMessage());
		}
	}
}

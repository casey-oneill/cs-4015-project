package com.cs4015.bookstore.bookservice.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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

	private User user;

	@PostConstruct
	public void init() {
		user = new User();
	}

	public void createUser() {
		try {
			bookService.saveUser(user);
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage("User created successfully."));
		} catch (DataIntegrityViolationException e) {
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "There is already a user with this username.", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to create user.", e.getMessage()));
		}
	}
}

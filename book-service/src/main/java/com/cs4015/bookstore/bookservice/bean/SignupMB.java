package com.cs4015.bookstore.bookservice.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.cs4015.bookstore.api.core.book.services.BookService;
import com.cs4015.bookstore.bookservice.core.user.model.User;
import com.cs4015.bookstore.bookservice.core.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
		User saveUser = bookService.saveUser(user);
		if (saveUser.getUserId() > 0) {
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage("User created successfully."));
		}
	}
}

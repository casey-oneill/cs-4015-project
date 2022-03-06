package com.cs4015.bookstore.bookservice.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.cs4015.bookstore.api.core.book.models.User;
import com.cs4015.bookstore.api.core.book.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component("signupMB")
@RequestScoped
@Data
public class SignupMB {
	
	private BookService bookService;

	private User user;

	@Autowired
	public SignupMB(@Qualifier("mockService") BookService bookService) {
		this.bookService = bookService;
	}

	@PostConstruct
	public void init() {
		user = new User();
	}

	public void createUser() {
		// TODO: Connect to CreateUser API method
	}
}

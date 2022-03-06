package com.cs4015.bookstore.api.core.book.models;

import lombok.Data;

@Data
public class User {
	
	private String username;
	private String fullName;
	private String password;
	private String email;
	private String phone;
}

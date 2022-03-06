package com.cs4015.bookstore.api.core.book.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
	
	private String username;
	private String fullName;
	private String password;
	private String email;
	private String phone;

	public User(String username, String fullName, String password, String email, String phone) {
		this.username = username;
		this.fullName = fullName;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}
}

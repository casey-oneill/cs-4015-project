package com.cs4015.bookstore.bookservice.core.user.model;

import java.util.NoSuchElementException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cs4015.bookstore.bookservice.core.user.services.UserService;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long userId;

	@Column(unique=true)
	private String username;
	private String fullName;
	private String password;
	private String email;
	private String phone;

	private static User userInstance = null;

	public User(String username, String fullName, String password, String email, String phone) {
		this.username = username;
		this.fullName = fullName;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

	public static User getInstance(){
		if(userInstance == null){
			userInstance = new User();
		}
		return userInstance;
	}

	public static void setInstance(User user){
		userInstance = user;
	}

	public static void destroyInstance(){
		userInstance = null;
	}

	@Override
	public String toString() {
		return "User {" +
                "id=" + userId +
				", password=" + password +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
	}
}

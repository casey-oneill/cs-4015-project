package com.cs4015.bookstore.bookservice.util;

import com.cs4015.bookstore.bookservice.core.user.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("currentUser")
public class CurrentUserProvider {

	private static final Logger LOG = LoggerFactory.getLogger(CurrentUserProvider.class);
	
	public User getCurrentUser() {
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return user;
		} catch (Exception e) {
			LOG.error("Failed to provide current user.", e.getMessage());
		}

		return null;
	}
}

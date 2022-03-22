package com.cs4015.bookstore.bookservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cs4015.bookstore.api.core.user.models.User;
import com.cs4015.bookstore.bookservice.core.user.manager.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserManager userManager;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String user = authentication.getName();
		String password = authentication.getCredentials().toString();

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		// Database connection for custom login
		Optional<User> validatedUser = userManager.validateUserCredentials(user, password);
		if (validatedUser.isPresent()) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}

		if (grantedAuthorities.size() > 0) {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(validatedUser.get(), password, grantedAuthorities);
			return authenticationToken;
		}

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}

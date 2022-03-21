package com.cs4015.bookstore.bookservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomAuthenticationProvider authenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/signup.xhtml", "/openapi/**").permitAll()
				.and()
			.authorizeRequests()
				.antMatchers("/", "/login.xhtml", "/javax.faces.resource/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login.xhtml")
				.defaultSuccessUrl("/index.xhtml")
				.successForwardUrl("/index.xhtml")
				.failureUrl("/login.xhtml?error=true")
				.permitAll()
				.and()
			.logout()
				.logoutSuccessUrl("/login.xhtml")
				.permitAll()
				.and()
			.csrf()
				.disable(); // JSF 2.2 already protects against CSRF attacks

		// Allows using resource links (ex. PDF)
		http.headers().frameOptions().sameOrigin();
	}
}

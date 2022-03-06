package com.cs4015.bookstore.bookservice.bean;

import java.io.IOException;
import com.cs4015.bookstore.api.core.book.models.User;
import com.cs4015.bookstore.api.core.book.services.BookService;

import org.omnifaces.util.Faces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Component("logonMB")
@SessionScope
@Data
public class LogonMB {

    private BookService bookService;

    private String username;
    private String password;
    
    private User user;

    @Autowired
	public LogonMB(@Qualifier("mockService") BookService bookService) {
		this.bookService = bookService;
	}

    public User getUser() {
        // TODO: Connect to User API
        return new User(
            "johndoe123",
            "John Doe",
            "abc123",
            "johnd@email.com",
            "15065555555"
        );
    }

    public void login() {
        try {
            // TODO: Connect to User API
            Faces.redirect("index.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        try {
            Faces.redirect("logon.jsf");
            this.user = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.cs4015.bookstore.bookservice.bean;

import java.io.IOException;

import com.cs4015.bookstore.bookservice.core.user.model.User;
import com.cs4015.bookstore.bookservice.core.user.services.UserService;
import com.cs4015.bookstore.bookservice.util.MessageService;

import org.omnifaces.util.Faces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Component("logonMB")
@SessionScope
@Data
public class LogonMB {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    private String username;
    private String password;
    private User user;

    public User getUser() {
        return user;
    }

    public void login() {
        try {
            user = userService.validateUserCredentials(username, password);
            if (user != null) {
                Faces.redirect("index.jsf");
            } else {
                messageService.showErrorMessage("Invalid username or password.");
            }
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

package com.cs4015.bookstore.bookservice.bean;

import com.cs4015.bookstore.bookservice.core.user.controllers.CurrentUsers;
import com.cs4015.bookstore.bookservice.core.user.model.User;
import com.cs4015.bookstore.bookservice.core.user.services.UserService;
import com.cs4015.bookstore.bookservice.util.MessageService;

import org.omnifaces.util.Faces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Component("loginMB")
@SessionScope
@Data
public class LoginMB {

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
                if (CurrentUsers.searchCurrentUser(user)) {
                    logout();
                    messageService.showErrorMessage("User is already logged in!");
                } else {
                    CurrentUsers.addCurrentUser(user);
                    Faces.redirect("index.jsf");
                }
            } else {
                messageService.showErrorMessage("Invalid username or password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        try {
            User.destroyInstance();
            Faces.redirect("logon.jsf");
        } catch (Exception e) {
            Faces.redirect("logon.jsf");
            CurrentUsers.removeCurrentUser(user);
            this.user = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.cs4015.bookstore.bookservice.bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.cs4015.bookstore.bookservice.core.user.model.User;
import com.cs4015.bookstore.bookservice.core.user.services.UserService;

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
                userService.saveUser(user);
                User.setInstance(user);
                Faces.redirect("index.jsf");
            } else {
                FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username or password.", ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        try {
            Faces.redirect("logon.jsf");
            User.destroyInstance();
            this.user = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

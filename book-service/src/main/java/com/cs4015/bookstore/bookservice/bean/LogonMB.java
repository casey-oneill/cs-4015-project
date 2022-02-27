package com.cs4015.bookstore.bookservice.bean;

import java.io.IOException;
import javax.annotation.ManagedBean;
import org.omnifaces.util.Faces;
import org.springframework.web.context.annotation.SessionScope;

@ManagedBean("logonMB")
@SessionScope
public class LogonMB {

    private String username;
    private String password;
    private String currentUser;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public void Login() throws IOException {
        setCurrentUser("John Doe");
        Faces.redirect("index.jsf");
    }
}

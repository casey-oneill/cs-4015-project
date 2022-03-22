package com.cs4015.bookstore.bookservice.core.user.manager;

import com.cs4015.bookstore.api.core.user.models.User;

import java.util.Optional;

public interface UserManager {

    Optional<User> getUserById(Long userId);
    Optional<User> saveUser(User user);
    void deleteUser(long userId);
    Optional<User> validateUserCredentials(String username, String password);
}

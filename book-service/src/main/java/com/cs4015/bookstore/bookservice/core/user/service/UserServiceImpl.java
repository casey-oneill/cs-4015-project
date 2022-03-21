package com.cs4015.bookstore.bookservice.core.user.service;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.models.UserBooks;
import com.cs4015.bookstore.api.core.user.models.User;
import com.cs4015.bookstore.api.core.user.services.UserService;
import com.cs4015.bookstore.api.exceptions.InvalidInputException;
import com.cs4015.bookstore.api.exceptions.NotFoundException;
import com.cs4015.bookstore.bookservice.core.book.manager.UserBookManager;
import com.cs4015.bookstore.bookservice.core.user.manager.UserManager;
import com.cs4015.bookstore.util.ServiceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

@RestController
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserBookManager userBookManager;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public User getUser(long userId) {
        logger.info("/Users return a user.");
        if (userId < 1) {
            throw new InvalidInputException("Invalid userId: " + userId);
        }

        return null; // FIXME: Finish implementation
    }

    @Override
    public User updateUser(User user,  long userId) {
        logger.debug("PUT: /users, update a user {}", user);

        if (userId < 1) {
            throw new InvalidInputException("Invalid userId: " + userId);
        }
        if (userManager.getUserById(userId).isEmpty()) {
            throw new NotFoundException("No User found for userId: " + userId);
        }

        Optional<User> saveUser = userManager.saveUser(user);
        if (saveUser.isPresent()) {
            return saveUser.get();
        }
        return null;
    }

    @Override
    public User addUser(User user) {
        logger.debug("POST: /users, add a new User {}", user);

        Optional<User> saveUser = userManager.saveUser(user);
        if (saveUser.isPresent()) {
            return saveUser.get();
        }
        return null;
    }

    @Override
    public void deleteUser(long userId) {
        logger.debug("POST: /users, delete a user {}", userId);
        if (userId < 1) {
            throw new InvalidInputException("Invalid userId: " + userId);
        }
        userManager.deleteUser(userId);
    }

    @Override
    public UserBooks getUserBooks(long user_id) {
        if (user_id < 1) {
            throw new InvalidInputException("Invalid userId: " + user_id);
        }
        Optional<User> user = userManager.getUserById(user_id);
        if (user.isPresent()) {
            return userBookManager.getUsersBooks(user_id);
        }
        return null;
    }

    @Override
    public UserBooks addBookToUser(long user_id, Book book) {
        if (user_id < 1) {
            throw new InvalidInputException("Invalid userId: " + user_id);
        }
        if(book == null){
            throw new InvalidInputException("Invalid Book: " + book.toString());
        }
        Optional<User> user = userManager.getUserById(user_id);
        if (user.isPresent()) {
            return userBookManager.addBookToUser(user_id, book);
        } else {
            throw new InvalidInputException("The user is not exist: userId = " + user_id);
        }
    }
}
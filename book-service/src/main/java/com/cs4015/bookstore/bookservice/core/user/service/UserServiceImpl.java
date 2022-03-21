package com.cs4015.bookstore.bookservice.core.user.service;

import com.cs4015.bookstore.api.core.user.models.User;
import com.cs4015.bookstore.api.core.user.services.UserService;
import com.cs4015.bookstore.api.exceptions.InvalidInputException;
import com.cs4015.bookstore.api.exceptions.NotFoundException;
import com.cs4015.bookstore.bookservice.core.user.manager.UserManager;
import com.cs4015.bookstore.bookservice.core.user.mapper.UserMapper;
import com.cs4015.bookstore.bookservice.core.user.model.UserEntity;
import com.cs4015.bookstore.bookservice.core.user.repository.UserRepository;
import com.cs4015.bookstore.util.ServiceUtil;
import com.google.protobuf.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserManager userManager;

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
    public User updateUser(User user, long userId) {
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
        UserEntity userEntity = repository.findById(userId).orElseThrow(()-> new NotFoundException("No user found for userId: " + userId));
        repository.delete(userEntity);
    }
}

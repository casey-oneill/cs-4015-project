package com.cs4015.bookstore.bookservice.core.user.service;

import com.cs4015.bookstore.api.core.user.services.UserService;
import com.cs4015.bookstore.api.exceptions.InvalidInputException;
import com.cs4015.bookstore.bookservice.core.user.manager.UserManager;
import com.cs4015.bookstore.bookservice.core.user.mapper.UserMapper;
import com.cs4015.bookstore.bookservice.core.user.model.UserEntity;
import com.cs4015.bookstore.bookservice.core.user.repository.UserRepository;
import com.cs4015.bookstore.util.ServiceUtil;

import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final UserManager userManager;

    private final ServiceUtil serviceUtil;


    @Override
    public UserEntity getUser(long userId){
        logger.info("/Users return a user.");
        if(userId < 1){
            throw new InvalidInputException("Invalid userId: " + userId);
        }

    @Override
    public UserEntity updateUserEntity(UserEntity user, long userId) {
        logger.debug("PUT: /users, update a user {}", user);
        if (userId < 1) {
            throw new InvalidInputException("Invalid userId: " + userId);
        }
        if (userManger.getUserById(userId).isEmpty()) {
            throw new NotFoundException("No User found for userId: " + userId);
        }
        user.setUserId(userId);

        User updateUser = userManager.saveUser(user).orElseThrow(() ->new BadRequestException("An error occurs to update the user:  " + user.toString()));
        return updateUserEntity(user, userId);
    }
    
    @Override
    public UserEntity addUser(UserEntity user) {
        logger.debug("POST: /users, add a new User {}", user);
        UserEntity updateUser = UserManger.saveUser(user).orElseThrow(() -> new BadRequestException("An error occurs to save the user:  " + user.toString()) );
        return updateUser;
    }

    @Override
    public void deleteUser(long userId){
        logger.debug("POST: /users, delete a user {}", userId);
        if(userId < 1) {
            throw new InvalidInputException("Invalid userId: " + userId);
        }
        UserEntity userEntity = repository.findById(userId).orElseThrow(()-> new NotFoundException("No user found for userId: " + userId));
        repository.delete(userEntity);
    }

    @Override
    public List<UserEntity> getAllUsers(int page, int offset) {
        if (page <= 0)
            page = 1;
        if (offset <= 0)
            offset = 10;
        List<UserEntity> rtnUsers = UserManger.getAllUserWithPagination(page, offset).get();
        return rtnUsers;
    }



























}
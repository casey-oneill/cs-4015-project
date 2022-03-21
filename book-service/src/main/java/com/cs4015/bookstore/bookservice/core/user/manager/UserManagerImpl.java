package com.cs4015.bookstore.bookservice.core.user.manager;


import com.cs4015.bookstore.api.core.user.models.User;
import com.cs4015.bookstore.api.exceptions.InvalidInputException;
import com.cs4015.bookstore.api.exceptions.NotFoundException;
import com.cs4015.bookstore.bookservice.core.user.mapper.UserMapper;
import com.cs4015.bookstore.bookservice.core.user.model.UserEntity;
import com.cs4015.bookstore.bookservice.core.user.repository.UserRepository;
import com.cs4015.bookstore.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserManagerImpl implements UserManager {
	private static final Logger logger = LoggerFactory.getLogger(UserManagerImpl.class);
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final ServiceUtil serviceUtil;

    @Autowired
    public UserManagerImpl(UserRepository userRepository, UserMapper userMapper, ServiceUtil serviceUtil) {
        this.repository = userRepository;
        this.userMapper = userMapper;
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        UserEntity userEntity = repository.findById(userId).orElseThrow(() -> new NotFoundException("No User found for productId: " + userId));
        User user = userMapper.entityToApi(userEntity).orElseThrow(() -> new InvalidInputException("Cannot convert the User Entity to API"));
        logger.debug("getUser from the database: found userId: " + user.getUserId());
        return Optional.of(user);
    }

    @Override
    public Optional<org.apache.catalina.User> saveUser(User user) {
        UserEntity updateUser = userMapper.apiToEntity(user).get();
        try {
            updateUser = repository.save(updateUser);
            return userMapper.entityToApi(updateUser);
        } catch(Exception ex) {
            logger.error("Error to update a user {} ", user);
            throw ex;
        }
    }

    @Override
    public void deleteUser(long userId) {
        logger.debug("Delete a user {}", userId);
        if (userId < 1) {
            throw new InvalidInputException("Invalid userId: " + userId);
        }
        UserEntity userEntity = repository.findById(userId).orElseThrow(()-> new NotFoundException("No user found for productId: " + userId));
        repository.delete(userEntity);
    }

    /*
    @Override
    public Optional<List<User>> getAllUserWithPagination(int page, int offset) {
        if (page <= 0)
            page = 1;
        if (offset <= 0)
            offset = 10;
        try {
            Pageable pageable = PageRequest.of(page - 1, offset, Sort.Direction.ASC, "id");
            Page<UserEntity> users = repository.findAll(pageable);
            List<UserEntity> userEntities = users.getContent();
            List<User> rtnUsers = userEntities.stream().map(entity -> userMapper.entityToApi(entity).get()).collect(Collectors.toCollection(ArrayList<User>::new));
            return Optional.of(rtnUsers);
        }catch(Exception ex){
            logger.error("An error to get AllUsers {}", ex);
            throw ex;
        }

    }
    */

}

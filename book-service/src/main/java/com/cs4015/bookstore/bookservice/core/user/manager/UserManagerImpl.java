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

import java.util.Optional;

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
        UserEntity userEntity = repository.findById(userId).orElseThrow(() -> new NotFoundException("No user found for productId: " + userId));
        User user = userMapper.entityToApi(userEntity).orElseThrow(() -> new InvalidInputException("Cannot convert the User Entity to API"));
        return Optional.of(user);
    }

    @Override
    public Optional<User> saveUser(User user) {
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
        if (userId < 1) {
            throw new InvalidInputException("Invalid userId: " + userId);
        }

        UserEntity userEntity = repository.findById(userId).orElseThrow(()-> new NotFoundException("No user found for productId: " + userId));
        repository.delete(userEntity);
    }

    @Override
    public Optional<User> validateUserCredentials(String username, String password) {
        UserEntity userEntity = repository.findByUsername(username).orElseThrow(() -> new NotFoundException("No user found for username: " + username));
        Optional<User> user = userMapper.entityToApi(userEntity);

        if (user.isPresent()) {
            return user;
        }
        return null;
    }
}

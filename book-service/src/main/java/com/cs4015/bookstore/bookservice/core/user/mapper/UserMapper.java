package com.cs4015.bookstore.bookservice.core.user.mapper;

import com.cs4015.bookstore.api.core.user.models.User;
import com.cs4015.bookstore.bookservice.core.user.model.UserEntity;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {

    public Optional<UserEntity> apiToEntity(User user) {
        if (user == null) {
            return Optional.empty();
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(user.getUserId());
        userEntity.setUsername(user.getUsername());
        userEntity.setFullName(user.getFullName());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhone(user.getPhone());

        return Optional.of(userEntity);
    };

    public Optional<User> entityToApi(UserEntity userEntity) {
        if (userEntity == null) {
            return Optional.empty();
        }

        User user = new User();
        user.setUserId(userEntity.getUserId());
        user.setUsername(userEntity.getUsername());
        user.setFullName(userEntity.getFullName());
        user.setPassword(userEntity.getPassword());
        user.setEmail(userEntity.getEmail());
        user.setPhone(userEntity.getPhone());

        return Optional.of(user);
    }
}

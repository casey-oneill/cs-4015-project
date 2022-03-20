package com.cs4015.bookstore.bookservice.core.user.mapper;

import com.cs4015.bookstore.api.core.book.models.*;
import com.cs4015.bookstore.bookservice.core.user.model.UserEntity;
import org.apache.catalina.User;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
   // BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    @ObjectFactory
    default Optional<UserEntity> apiToEntity(com.cs4015.bookstore.api.core.user.models.User user2){
        UserEntity bookEntity = null;
        if(user2 == null){
            return Optional.empty();
        }
        User user = null;

        if(user2.getUserId() != null) {
            UserEntity.setUserId(user2.getUserId());
        }
        UserEntity.setUsername(user2.getUsername());
        UserEntity.setAuthors(user2.getFullname().stream().collect(Collectors.joining(",")));
        UserEntity.setDescription(user2.getPassword());
        UserEntity.setPrice(user2.getEmail());
        UserEntity.setPhotoUrls(user2.getPhone());

        return Optional.of(userEntity);
    };

    @ObjectFactory
    default  Optional<User> entityToApi(UserEntity userEntity){
        if(userEntity == null){
            return Optional.empty();
        }
        UserEntity user = null;

        UserEntity.setUserId(userEntity.getUserId());
        return Optional.of(user);
    };


}

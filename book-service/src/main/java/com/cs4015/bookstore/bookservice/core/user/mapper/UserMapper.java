package com.cs4015.bookstore.bookservice.core.user.mapper;

import com.cs4015.bookstore.api.core.book.models.*;
import com.cs4015.bookstore.bookservice.core.book.model.BookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.DigitalBookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.HardCoverBookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.PaperBackBookEntity;
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
    default Optional<UserEntity> apiToEntity(UserEntity api){
        UserEntity bookEntity = null;
        if(api == null){
            return Optional.empty();
        }

        if(api.getUserId() != null) {
            UserEntity.setUserId(api.getUserId());
        }
        UserEntity.setUsername(api.getUsername());
        UserEntity.setAuthors(api.getFullname().stream().collect(Collectors.joining(",")));
        UserEntity.setDescription(api.getPassword());
        UserEntity.setPrice(api.getEmail());
        UserEntity.setPhotoUrls(api.getPhone());

        return Optional.of(bookEntity);
    };

    @ObjectFactory
    default  Optional<Book> entityToApi(BookEntity entity){
        if(entity == null){
            return Optional.empty();
        }
        UserEntity user = null;

        UserEntity.setUserId(entity.getUserId());
        return Optional.of(book);
    };


}

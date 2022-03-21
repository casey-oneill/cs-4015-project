package com.cs4015.bookstore.bookservice.core.book.mapper;

import com.cs4015.bookstore.api.core.book.models.UserBooks;
import com.cs4015.bookstore.api.core.user.models.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserBooksMapper {
    @Mappings({
            @Mapping(target = "books", ignore = true)
    })
    public UserBooks userEntityToUserBooks(User user);
}

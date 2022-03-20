package com.cs4015.bookstore.bookservice.core.book.mapper;

import com.cs4015.bookstore.api.core.book.models.*;
import com.cs4015.bookstore.bookservice.core.book.model.BookEntity;


import java.util.Optional;

public class BookMapperAdapter implements MyBookMapper {
    ApiFactory apifactory;
    EntityFactory entityfactory;

    public BookMapperAdapter(){
        apifactory = new ApiFactory();
        entityfactory = new EntityFactory();
    }


    public Optional<BookEntity> apiToEntity(Book api){

        BookEntity bookEntity = apifactory.toEntity(api);

        return Optional.of(bookEntity);
    }

    public Optional<Book> entityToApi(BookEntity entity){

        Book book = entityfactory.toApi(entity);

        return Optional.of(book);
    }
    
}

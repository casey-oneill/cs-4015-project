package com.cs4015.bookstore.bookservice.core.book.mapper;

import com.cs4015.bookstore.api.core.book.models.*;
import com.cs4015.bookstore.bookservice.core.book.model.BookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.DigitalBookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.HardCoverBookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.PaperBackBookEntity;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
public class BookMapperAdapter implements Mapper {
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

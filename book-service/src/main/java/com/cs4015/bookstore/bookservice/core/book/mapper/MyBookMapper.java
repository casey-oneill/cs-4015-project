package com.cs4015.bookstore.bookservice.core.book.mapper;
import com.cs4015.bookstore.api.core.book.models.*;
import com.cs4015.bookstore.bookservice.core.book.model.BookEntity;


import java.util.Optional;

public interface MyBookMapper {
    public Optional<BookEntity> apiToEntity(Book api);
    public Optional<Book> entityToApi(BookEntity entity);
}

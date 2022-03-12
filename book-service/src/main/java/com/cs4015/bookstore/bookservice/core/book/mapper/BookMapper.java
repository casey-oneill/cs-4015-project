package com.cs4015.bookstore.bookservice.core.book.mapper;

import com.cs4015.bookstore.api.core.book.models.*;
import com.cs4015.bookstore.bookservice.core.book.model.BookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.DigitalBookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.HardCoverBookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.PaperBackBookEntity;
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
public interface BookMapper {
   // BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    @ObjectFactory
    default Optional<BookEntity> apiToEntity(Book api){
        BookEntity bookEntity = null;
        if(api == null){
            return Optional.empty();
        }
        String bookType = api.getBookType();

        switch (bookType){
            case BookType.Values.DIGITAL:
                DigitalBook dBook = (DigitalBook) api;
                DigitalBookEntity dEntity = new DigitalBookEntity();
                //dEntity.setBookType(BookType.DIGITAL);
                dEntity.setDigitalFormat(dBook.getDigitalFormat());
                dEntity.setDigitalUrl(dBook.getUrl());
                bookEntity = dEntity;
                break;
            case BookType.Values.HARDCOVER:
                HardCoverBook hBook = (HardCoverBook) api;
                HardCoverBookEntity hEntity = new HardCoverBookEntity();
                //hEntity.setBookType(BookType.HARDCOVER);
                hEntity.setBookCondition(hBook.getCondition());
                bookEntity = hEntity;
                break;
            case BookType.Values.PAPERBACK:
                PaperBackBook pBook = (PaperBackBook) api;
                PaperBackBookEntity pEntity = new PaperBackBookEntity();
                //pEntity.setBookType(BookType.PAPERBACK);
                pEntity.setBookCondition(pBook.getCondition());
                bookEntity = pEntity;
                break;
        }
        bookEntity.setTitle(api.getTitle());
        bookEntity.setAuthors(api.getAuthors().stream().collect(Collectors.joining(",")));
        bookEntity.setDescription(api.getDescription());
        bookEntity.setPrice(api.getPrice());
        bookEntity.setPhotoUrls(api.getPhotoUrls());

        return Optional.of(bookEntity);
    };

    @ObjectFactory
    default  Optional<Book> entityToApi(BookEntity entity){
        if(entity == null){
            return Optional.empty();
        }
        Book book = null;

        BookType bookType = entity.getType();
        switch (bookType){
            case DIGITAL:
                DigitalBookEntity dBookEntity = (DigitalBookEntity) entity;
                book = new DigitalBook(entity.getId(), entity.getType().toString(), entity.getTitle(),
                        new ArrayList<String>(Arrays.asList(entity.getAuthors().split(","))),
                        entity.getDescription(), entity.getPrice(), entity.getPhotoUrls(), dBookEntity.getDigitalFormat(), dBookEntity.getDigitalUrl());
                break;
            case HARDCOVER:
                HardCoverBookEntity hardCoverBookEntity = (HardCoverBookEntity) entity;
                book = new HardCoverBook(entity.getId(), entity.getType().toString(), entity.getTitle(),
                        new ArrayList<String>(Arrays.asList(entity.getAuthors().split(","))),
                        entity.getDescription(), entity.getPrice(), entity.getPhotoUrls(), hardCoverBookEntity.getBookCondition());
                break;
            case PAPERBACK:
                PaperBackBookEntity paperBackBookEntity = (PaperBackBookEntity) entity;
                book = new PaperBackBook(entity.getId(), entity.getType().toString(), entity.getTitle(),
                        new ArrayList<String>(Arrays.asList(entity.getAuthors().split(","))),
                        entity.getDescription(), entity.getPrice(), entity.getPhotoUrls(), paperBackBookEntity.getBookCondition());
                book.setBookId(entity.getId());
                book.setDescription(entity.getDescription());
                break;

        }
        return Optional.of(book);
    };


}

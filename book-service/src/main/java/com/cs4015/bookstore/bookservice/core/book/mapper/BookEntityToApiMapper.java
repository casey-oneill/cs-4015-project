package com.cs4015.bookstore.bookservice.core.book.mapper;

import com.cs4015.bookstore.api.core.book.models.*;
import com.cs4015.bookstore.bookservice.core.book.model.BookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.DigitalBookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.HardCoverBookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.PaperBackBookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

import java.util.ArrayList;
import java.util.Arrays;

@Mapper(componentModel = "spring")
public interface BookEntityToApiMapper {

//    Book entityToApi(BookEntity entity);
//
//    @Mappings({
//            @Mapping(target = "id", ignore = true)
//    })
//    BookEntity apiToEntity (Book book);

    @ObjectFactory
    default Book bookEntityToApi(BookEntity entity){
        if(entity == null){
            return null;
        }
        Book book = null;

        BookType bookType = entity.getType();
        switch (bookType){
            case DIGITAL:
                DigitalBookEntity dBookEntity = (DigitalBookEntity) entity;
                book = new DigitalBook(entity.getId(), entity.getType().toString(), entity.getTitle(),
                        new ArrayList<String>(Arrays.asList(entity.getAuthors().split(","))),
                        entity.getDescription(), entity.getPrice(), entity.getPhotoUrls(), dBookEntity.getDigitalFormat(), dBookEntity.getDigitalUrl());
                book.setUserId(entity.getUserId());
                break;
            case HARDCOVER:
                HardCoverBookEntity hardCoverBookEntity = (HardCoverBookEntity) entity;
                book = new HardCoverBook(entity.getId(), entity.getType().toString(), entity.getTitle(),
                        new ArrayList<String>(Arrays.asList(entity.getAuthors().split(","))),
                        entity.getDescription(), entity.getPrice(), entity.getPhotoUrls(), hardCoverBookEntity.getBookCondition());
                book.setUserId(entity.getUserId());
                break;
            case PAPERBACK:
                PaperBackBookEntity paperBackBookEntity = (PaperBackBookEntity) entity;
                book = new PaperBackBook(entity.getId(), entity.getType().toString(), entity.getTitle(),
                        new ArrayList<String>(Arrays.asList(entity.getAuthors().split(","))),
                        entity.getDescription(), entity.getPrice(), entity.getPhotoUrls(), paperBackBookEntity.getBookCondition());
                book.setUserId(entity.getUserId());
                break;

        }

        return book;
    }



}

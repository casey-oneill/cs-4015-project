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

public class EntityFactory {

    Book book;

    public Book toApi(BookEntity entity){
        if(entity == null){
            return null;
        }
        book = null;

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
                break;

        }
        book.setUserId(entity.getUserId());
        return book;
    }
    
}

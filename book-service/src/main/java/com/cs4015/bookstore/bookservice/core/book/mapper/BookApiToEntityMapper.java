package com.cs4015.bookstore.bookservice.core.book.mapper;

import com.cs4015.bookstore.api.core.book.models.*;
import com.cs4015.bookstore.bookservice.core.book.model.BookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.DigitalBookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.HardCoverBookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.PaperBackBookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Component
public interface BookApiToEntityMapper {
    @ObjectFactory
    default BookEntity bookApiToEntity(Book api){
        BookEntity bookEntity = null;
        if(api == null){
            return null;
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
        bookEntity.setUserId(api.getUserId());
        bookEntity.setTitle(api.getTitle());
        bookEntity.setAuthors(api.getAuthors().stream().collect(Collectors.joining(",")));
        bookEntity.setDescription(api.getDescription());
        bookEntity.setPrice(api.getPrice());
        bookEntity.setPhotoUrls(api.getPhotoUrls());

        return bookEntity;
    }
}

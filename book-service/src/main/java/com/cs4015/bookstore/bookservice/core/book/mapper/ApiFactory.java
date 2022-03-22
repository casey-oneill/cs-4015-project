package com.cs4015.bookstore.bookservice.core.book.mapper;

import com.cs4015.bookstore.api.core.book.models.*;
import com.cs4015.bookstore.bookservice.core.book.model.BookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.DigitalBookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.HardCoverBookEntity;
import com.cs4015.bookstore.bookservice.core.book.model.PaperBackBookEntity;

import java.util.stream.Collectors;

public class ApiFactory {

    BookEntity bookEntity;

    public BookEntity toEntity(Book api) {
        bookEntity = null;
        if (api == null) {
            return null;
        }

        String bookType = api.getBookType();

        switch(bookType) {
            case BookType.Values.DIGITAL:
                DigitalBook dBook = (DigitalBook) api;
                DigitalBookEntity dEntity = new DigitalBookEntity();
                dEntity.setDigitalFormat(dBook.getDigitalFormat());
                dEntity.setDigitalUrl(dBook.getUrl());
                bookEntity = dEntity;
                break;
            case BookType.Values.HARDCOVER:
                HardCoverBook hBook = (HardCoverBook) api;
                HardCoverBookEntity hEntity = new HardCoverBookEntity();
                hEntity.setBookCondition(hBook.getCondition());
                bookEntity = hEntity;
                break;
            case BookType.Values.PAPERBACK:
                PaperBackBook pBook = (PaperBackBook) api;
                PaperBackBookEntity pEntity = new PaperBackBookEntity();
                pEntity.setBookCondition(pBook.getCondition());
                bookEntity = pEntity;
                break;
        }

        if (api.getBookId() != null) {
            bookEntity.setId(api.getBookId());
        }
        if (api.getUserId() != null) {
            bookEntity.setUserId(api.getUserId());
        }
        bookEntity.setTitle(api.getTitle());
        bookEntity.setAuthors(api.getAuthors().stream().collect(Collectors.joining(",")));
        bookEntity.setDescription(api.getDescription());
        bookEntity.setPrice(api.getPrice());
        bookEntity.setPhotoUrls(api.getPhotoUrls());

        return bookEntity;
    }
    
}

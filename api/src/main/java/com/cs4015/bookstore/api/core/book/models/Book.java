package com.cs4015.bookstore.api.core.book.models;

import lombok.Data;

import java.util.List;

@Data
public abstract class Book {
    private Long bookId;
    private BookType bookType;
    private String title;
    private List<String> authors;
    private String description;
    private double price;
    private String photoUrls;

    public Book(Long bookId, BookType bookType, String title, List<String> authors, String description, double price, String photoUrls) {
        this.bookId = bookId;
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.price = price;
        this.photoUrls = photoUrls;
        this.bookType = bookType;
    }
}

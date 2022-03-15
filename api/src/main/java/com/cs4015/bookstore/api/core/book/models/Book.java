package com.cs4015.bookstore.api.core.book.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

import java.util.List;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "bookType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DigitalBook.class, name= BookType.Values.DIGITAL),
        @JsonSubTypes.Type(value = HardCoverBook.class, name=BookType.Values.HARDCOVER),
        @JsonSubTypes.Type(value = PaperBackBook.class, name=BookType.Values.PAPERBACK)
})
@Data
public abstract class Book {
    
    private Long bookId;
    private Long userId;
    private String bookType;
    private String title;
    private List<String> authors;
    private String description;
    private double price;
    private String photoUrls;

    public Book() {};

    public Book(Long bookId, String bookType, String title, List<String> authors, String description, double price, String photoUrls) {
        this.bookId = bookId;
        this.bookType = bookType;
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.price = price;
        this.photoUrls = photoUrls;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", userId=" + userId +
                ", bookType='" + bookType + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", photoUrls='" + photoUrls + '\'' +
                '}';
    }
}

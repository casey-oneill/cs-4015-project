package com.cs4015.bookstore.api.core.book.model;

import java.util.List;

public abstract class Book {
    private Integer bookId;
    private BookType bookType;
    private String title;
    private List<String> authors;
    private String description;
    private double price;
    private String photoUrls;


    public Book(Integer bookId, BookType bookType, String title, List<String> authors, double price, String photoUrls) {
        this.bookId = bookId;
        this.title = title;
        this.authors = authors;
        this.price = price;
        this.photoUrls = photoUrls;
        this.bookType = bookType;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(String photoUrls) {
        this.photoUrls = photoUrls;
    }

}

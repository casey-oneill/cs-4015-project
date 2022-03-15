package com.cs4015.bookstore.api.core.book.models;

import java.util.List;

public class DigitalBook extends Book {

    private DigitalFormat digitalFormat;
    private String url;

    public DigitalBook(Long bookId, String bookType, String title, List<String> authors, String description, double price, String photoUrls) {
        super(bookId, bookType, title, authors, description, price, photoUrls);
    }

    public DigitalBook(Long bookId, String bookType, String title, List<String> authors, String description, double price, String photoUrls, DigitalFormat digitalFormat, String url) {
        super(bookId, bookType, title, authors,description, price, photoUrls);
        this.digitalFormat = digitalFormat;
        this.url = url;
    }

    public DigitalFormat getDigitalFormat() {
        return digitalFormat;
    }

    public void setDigitalFormat(DigitalFormat digitalFormat) {
        this.digitalFormat = digitalFormat;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

package com.cs4015.bookstore.api.core.book.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

public class PaperBackBook extends Book{

    private Condition condition;

    public PaperBackBook(){};

    public PaperBackBook(Long bookId, String bookType, String title, List<String> authors, String description, double price, String photoUrls) {
        super(bookId, bookType, title, authors, description, price, photoUrls);
    }

    public PaperBackBook(Long bookId, String bookType, String title, List<String> authors, String description, double price, String photoUrls, Condition condition) {
        super(bookId, bookType, title, authors, description, price, photoUrls);
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "PaperBackBook{" +
                "bookId=" + getBookId() +
                ", bookType='" + getBookType() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", authors=" + getAuthors() +
                ", description='" +getDescription() + '\'' +
                ", price=" + getPrice() +
                ", photoUrls='" + getPhotoUrls() + '\'' +
                "condition=" + condition +
                '}';
    }
}

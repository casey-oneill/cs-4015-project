package com.cs4015.bookstore.api.core.book.model;

import java.util.List;

public class HardCoverBook extends Book{
    private Condition condition;

    public HardCoverBook(Integer bookId, BookType bookType, String title, List<String> authors, double price, String photoUrls) {
        super(bookId, bookType, title, authors, price, photoUrls);
    }

    public HardCoverBook(Integer bookId, BookType bookType, String title, List<String> authors, double price, String photoUrls,  Condition condition) {
        super(bookId, bookType, title, authors, price, photoUrls);
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}

package com.cs4015.bookstore.api.core.book.model;

import java.util.List;

public class PaperBackBook extends Book{
    private Condition condition;

    public PaperBackBook(Integer bookId, String title, List<String> authors, double price, String photoUrls, BookType bookType) {
        super(bookId, bookType, title, authors, price, photoUrls);
    }

    public PaperBackBook(Integer bookId, BookType bookType, String title, List<String> authors, double price, String photoUrls, Condition condition) {
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

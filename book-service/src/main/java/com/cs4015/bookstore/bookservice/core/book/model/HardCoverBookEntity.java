package com.cs4015.bookstore.bookservice.core.book.model;

import com.cs4015.bookstore.api.core.book.models.BookType;
import com.cs4015.bookstore.api.core.book.models.Condition;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value= BookType.Values.HARDCOVER)
@DiscriminatorColumn(name = "book_type", discriminatorType = DiscriminatorType.STRING)
public class HardCoverBookEntity extends BookEntity {
    @Enumerated(EnumType.STRING)
    private Condition bookCondition;

    public Condition getBookCondition() {
        return bookCondition;
    }

    public void setBookCondition(Condition condition) {
        this.bookCondition = condition;
    }

    @Override
    public BookType getType() {
        return BookType.HARDCOVER;
    }
}

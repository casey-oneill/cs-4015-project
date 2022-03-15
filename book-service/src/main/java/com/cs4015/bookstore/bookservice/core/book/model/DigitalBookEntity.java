package com.cs4015.bookstore.bookservice.core.book.model;

import com.cs4015.bookstore.api.core.book.models.BookType;
import com.cs4015.bookstore.api.core.book.models.DigitalFormat;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value=BookType.Values.DIGITAL)
@DiscriminatorColumn(name="book_type", discriminatorType=DiscriminatorType.STRING)
public class DigitalBookEntity extends BookEntity {
    @Enumerated(EnumType.STRING)
    private DigitalFormat digitalFormat;
    private String digitalUrl;

    public DigitalFormat getDigitalFormat() {
        return digitalFormat;
    }

    public void setDigitalFormat(DigitalFormat digitalFormat) {
        this.digitalFormat = digitalFormat;
    }

    public String getDigitalUrl() {
        return digitalUrl;
    }

    public void setDigitalUrl(String url) {
        this.digitalUrl = url;
    }

    @Override
    public BookType getType() {
        return BookType.DIGITAL;
    }

    @Override
    public String toString() {
        return "DigitalBookEntity{" +
                "digitalFormat=" + digitalFormat +
                ", digitalUrl='" + digitalUrl + '\'' +
                '}';
    }
}

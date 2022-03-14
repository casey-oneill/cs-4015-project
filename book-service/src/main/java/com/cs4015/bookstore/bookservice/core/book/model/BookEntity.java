package com.cs4015.bookstore.bookservice.core.book.model;

import com.cs4015.bookstore.api.core.book.models.BookType;
import com.cs4015.bookstore.bookservice.core.user.model.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="book_type", discriminatorType = DiscriminatorType.STRING)
@Table(name= "books")
public abstract class BookEntity {
    @javax.persistence.Id
    @GeneratedValue
    private long id;
    //private BookType bookType;
    private String title;
    private String authors;
    private String description;
    private double price;
    @Column(name="photo_urls")
    private String photoUrls;

    public BookEntity(){}

    public BookEntity(String title, String authors, String description, double price, String photoUrls) {
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.price = price;
        this.photoUrls = photoUrls;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
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

    public abstract BookType getType();

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", photoUrls='" + photoUrls + '\'' +
                '}';
    }
}


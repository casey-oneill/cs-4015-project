package com.cs4015.bookstore.api.core.book.models;

import java.util.List;

import lombok.Data;

@Data
public class UserBooks {
    private long userId;
    private String username;
    private String fullName;
    private String password;
    private String email;
    private String phone;
    private List<Book> books;

    public UserBooks(long userId, String username, String fullName, String password, String email, String phone, List<Book> books) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.books = books;
    }

    @Override
    public String toString() {
        return "UserBooks{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", books=" + books +
                '}';
    }
}

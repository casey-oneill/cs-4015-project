package com.cs4015.bookstore.api.core.book.models;

import java.util.List;

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
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

package com.cs4015.bookstore.bookservice.core.book.manager;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.models.UserBooks;

public interface UserBookManager {
    
    UserBooks addBookToUser(Long userId, Book book);
    UserBooks getUsersBooks(Long userId);
}

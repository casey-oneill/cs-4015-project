package com.cs4015.bookstore.bookservice.core.book.manager;

import com.cs4015.bookstore.api.core.book.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookManager {
    Optional<Book> getBookById(long bookId);

    Optional<Book> saveBook(Book book);

    void deleteBook(long bookId);

    Optional<List<Book>> getAllBookWithPagination(int page, int offset);

}

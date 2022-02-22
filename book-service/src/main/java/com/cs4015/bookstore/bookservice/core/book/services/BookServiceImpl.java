package com.cs4015.bookstore.bookservice.core.book.services;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.models.BookType;
import com.cs4015.bookstore.api.core.book.models.Condition;
import com.cs4015.bookstore.api.core.book.models.PaperBackBook;
import com.cs4015.bookstore.api.core.book.services.BookService;
import com.cs4015.bookstore.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final ServiceUtil serviceUtil;

    @Autowired
    public BookServiceImpl(ServiceUtil serviceUtil){
        this.serviceUtil = serviceUtil;
    }
    @Override
    public Book getBook(long bookId) {
        logger.info("/books return a book.");
        List<String> authors = new ArrayList<>();
        authors.add("AAA BBB");
        return new PaperBackBook(bookId, BookType.PAPERBACK,"ABC", authors, 99.99, "https://abc/abc.jpg", Condition.LIKENEW);
    }

    @Override
    public Book replaceBook(Book book, long bookId) {
        return null;
    }

    @Override
    public Book addBook(Book book) {
        return null;
    }

    @Override
    public void deleteBook(long bookId) {

    }

    @Override
    public List<Book> getAllBooks(int page, int offset) {
        return null;
    }
}

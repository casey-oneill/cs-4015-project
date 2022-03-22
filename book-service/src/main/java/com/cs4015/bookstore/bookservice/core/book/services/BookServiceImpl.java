
package com.cs4015.bookstore.bookservice.core.book.services;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.services.BookService;
import com.cs4015.bookstore.api.exceptions.BadRequestException;
import com.cs4015.bookstore.api.exceptions.InvalidInputException;
import com.cs4015.bookstore.api.exceptions.NotFoundException;
import com.cs4015.bookstore.bookservice.core.book.manager.BookManager;
import com.cs4015.bookstore.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    private final BookManager bookManger;
    private final ServiceUtil serviceUtil;

    @Autowired
    public BookServiceImpl(BookManager bookManger, ServiceUtil serviceUtil){
        this.serviceUtil = serviceUtil;
        this.bookManger = bookManger;
    }

    @Override
    public Book getBook(long bookId) {
        logger.info("/books return a book.");

        if (bookId < 1) {
            throw new InvalidInputException("Invalid bookId: " + bookId);
        }
        Book book = bookManger.getBookById(bookId).orElseThrow(() -> new NotFoundException("No Book found for bookId: " + bookId));
        logger.debug("getBook: found bookId: " + book.getBookId());
        return book;
    }

    @Override
    public Book updateBook(Book book, long bookId) {
        logger.debug("PUT: /books, update a book {}", book);
        if (bookId < 1) {
            throw new InvalidInputException("Invalid bookId: " + bookId);
        }
        if (bookManger.getBookById(bookId).isEmpty()) {
            throw new NotFoundException("No Book found for bookId: " + bookId);
        }
        book.setBookId(bookId);

        Book updateBook = bookManger.saveBook(book).orElseThrow(() ->new BadRequestException("An error occurs to update the book:  " + book.toString()));
        return updateBook;
    }

    @Override
    public Book addBook(Book book) {
        logger.debug("POST: /books, add a new Book {}", book);
        Book updateBook = bookManger.saveBook(book).orElseThrow(() -> new BadRequestException("An error occurs to save the book:  " + book.toString()) );
        return updateBook;
    }

    @Override
    public void deleteBook(long bookId){
        logger.debug("POST: /books, delete a book {}", bookId);
        if (bookId < 1) {
            throw new InvalidInputException("Invalid bookId: " + bookId);
        }
        bookManger.deleteBook(bookId);
    }

    @Override
    public List<Book> getAllBooks(int page, int offset) {
        if (page <= 0)
            page = 1;
        if (offset <= 0)
            offset = 10;
        List<Book> rtnBooks = bookManger.getAllBookWithPagination(page, offset).get();
        return rtnBooks;
    }
}

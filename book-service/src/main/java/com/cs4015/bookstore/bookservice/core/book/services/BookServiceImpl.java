
package com.cs4015.bookstore.bookservice.core.book.services;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.models.BookType;
import com.cs4015.bookstore.api.core.book.models.Condition;
import com.cs4015.bookstore.api.core.book.models.PaperBackBook;
import com.cs4015.bookstore.api.core.book.services.BookService;
import com.cs4015.bookstore.api.exceptions.BadRequestException;
import com.cs4015.bookstore.api.exceptions.InvalidInputException;
import com.cs4015.bookstore.api.exceptions.NotFoundException;
import com.cs4015.bookstore.bookservice.core.book.manager.BookManager;
import com.cs4015.bookstore.bookservice.core.book.mapper.BookApiToEntityMapper;
import com.cs4015.bookstore.bookservice.core.book.mapper.BookEntityToApiMapper;
import com.cs4015.bookstore.bookservice.core.book.mapper.BookMapper;
import com.cs4015.bookstore.bookservice.core.book.model.BookEntity;
import com.cs4015.bookstore.bookservice.core.book.repository.BookRepository;
import com.cs4015.bookstore.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    private final BookRepository repository;
    private final BookMapper bookMapper;
    private final BookManager bookManger;

    private final ServiceUtil serviceUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper, BookManager bookManger, ServiceUtil serviceUtil){
        this.repository = bookRepository;
        this.bookMapper = bookMapper;
        this.serviceUtil = serviceUtil;
        this.bookManger = bookManger;
    }
    @Override
    public Book getBook(long bookId) {
        logger.info("/books return a book.");

        if(bookId < 1){
            throw new InvalidInputException("Invalid bookId: " + bookId);
        }
        Book book = bookManger.getBookById(bookId).orElseThrow(() -> new NotFoundException("No Book found for bookId: " + bookId));
        logger.debug("getBook: found bookId: " + book.getBookId());
        return book;
    }

    @Override
    public Book updateBook(Book book, long bookId){
        logger.debug("PUT: /books, update a book {}", book);
        if(bookId < 1){
            throw new InvalidInputException("Invalid bookId: " + bookId);
        }
        if(bookManger.getBookById(bookId).isEmpty()){
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
        if(bookId < 1){
            throw new InvalidInputException("Invalid bookId: " + bookId);
        }
        BookEntity bookEntity = repository.findById(bookId).orElseThrow(()-> new NotFoundException("No book found for productId: " + bookId));
        repository.delete(bookEntity);
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
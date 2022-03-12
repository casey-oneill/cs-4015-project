
package com.cs4015.bookstore.bookservice.core.book.services;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.models.BookType;
import com.cs4015.bookstore.api.core.book.models.Condition;
import com.cs4015.bookstore.api.core.book.models.PaperBackBook;
import com.cs4015.bookstore.api.core.book.services.BookService;
import com.cs4015.bookstore.api.exceptions.BadRequestException;
import com.cs4015.bookstore.api.exceptions.InvalidInputException;
import com.cs4015.bookstore.api.exceptions.NotFoundException;
import com.cs4015.bookstore.bookservice.core.book.mapper.BookApiToEntityMapper;
import com.cs4015.bookstore.bookservice.core.book.mapper.BookEntityToApiMapper;
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
    private final BookEntityToApiMapper bookEntityToApiMapper;
    private final BookApiToEntityMapper bookApiToEntityMapper;

    private final ServiceUtil serviceUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookEntityToApiMapper bookEntityToApiMapper, BookApiToEntityMapper bookApiToEntityMapper, ServiceUtil serviceUtil){
        this.repository = bookRepository;
        this.bookEntityToApiMapper = bookEntityToApiMapper;
        this.bookApiToEntityMapper = bookApiToEntityMapper;
        this.serviceUtil = serviceUtil;
    }
    @Override
    public Book getBook(long bookId) {
        logger.info("/books return a book.");

        if(bookId < 1){
            throw new InvalidInputException("Invalid bookId: " + bookId);
        }
        BookEntity bookEntity = repository.findById(bookId).orElseThrow(() -> new NotFoundException("No Book found for productId: " + bookId));
        Book book = bookEntityToApiMapper.bookEntityToApi(bookEntity);
        logger.debug("getBook: found bookId: " + book.getBookId());
        return book;
    }

    @Override
    public Book updateBook(Book book, long bookId){
        logger.debug("PUT: /books, update a book {}", book);
        if(bookId < 1){
            throw new InvalidInputException("Invalid bookId: " + bookId);
        }
        BookEntity bookEntity = repository.findById(bookId).orElseThrow(()-> new NotFoundException("No book found for productId: " + bookId));
        BookEntity updateBook = bookApiToEntityMapper.bookApiToEntity(book);
        updateBook.setId(bookId);
        try{
            updateBook = repository.save(updateBook);
            return bookEntityToApiMapper.bookEntityToApi(updateBook);

        }catch(Exception ex){
            logger.error("Error to update a book {} ", book);
            throw ex;
        }
    }

    @Override
    public Book addBook(Book book) {
        logger.debug("POST: /books, add a new Book {}", book);
        try{
            BookEntity bookEntity = bookApiToEntityMapper.bookApiToEntity(book);
            logger.debug("addBook: bookEntity  to be saved into db {}", bookEntity);
            BookEntity newEntity = repository.save(bookEntity);
            logger.debug("addBook: Book entity created for bookId: ", book.getBookId());
            return bookEntityToApiMapper.bookEntityToApi(newEntity);
        }catch (DuplicateKeyException dke){
            throw new InvalidInputException("Duplicate key, Book Id: " + book.getBookId());
        }
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
        try {
            Pageable pageable = PageRequest.of(page - 1, offset, Sort.Direction.ASC, "id");
            Page<BookEntity> books = repository.findAll(pageable);
            List<BookEntity> bookEntities = books.getContent();
            List<Book> rtnBooks = bookEntities.stream().map(entity -> bookEntityToApiMapper.bookEntityToApi(entity)).collect(Collectors.toCollection(ArrayList<Book>::new));
            return rtnBooks;
        }catch(Exception ex){
            logger.error("An error to get AllBooks {}", ex);
            throw ex;
        }
    }
}
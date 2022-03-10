
package com.cs4015.bookstore.bookservice.core.book.services;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.models.BookType;
import com.cs4015.bookstore.api.core.book.models.Condition;
import com.cs4015.bookstore.api.core.book.models.PaperBackBook;
import com.cs4015.bookstore.api.core.book.services.BookService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
//        List<String> authors = new ArrayList<>();
//        authors.add("AAA BBB");
//        return new PaperBackBook(bookId, BookType.PAPERBACK,"ABC", authors, 99.99, "https://abc/abc.jpg", Condition.LIKENEW);
        BookEntity bookEntity = repository.findById(bookId).orElseThrow(() -> new NotFoundException("No Book found for productId: " + bookId));
        Book book = bookEntityToApiMapper.bookEntityToApi(bookEntity);
        logger.debug("getBook: found bookId: " + book.getBookId());
        return book;
    }

    @Override
    public Book updateBook(Book book, long bookId){
        logger.debug("POST: /books, update a book {}", book);
        if(bookId < 1){
            throw new InvalidInputException("Invalid bookId: " + bookId);
        }
        BookEntity bookEntity = repository.findById(bookId).orElseThrow(()-> new NotFoundException("No book found for productId: " + bookId));
        repository.deleteInBatch(bookEntity);
        BookEntity upBookEntity = reponsitory = bookApiToEntityMapper.bookApiToEntity(book);
        bookEntity newEntity = repository.save(upBookEntity);
        return bookEntityToApiMapper.bookEntityToApi(newEntity);
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
        logger.debug("POST: /books, delete a book {}", book);
        if(bookId < 1){
            throw new InvalidInputException("Invalid bookId: " + bookId);
        }
        BookEntity bookEntity = repository.findById(bookId).orElseThrow(()-> new NotFoundException("No book found for productId: " + bookId));
        repository.deleteInBatch(bookEntity);
    }

    @Override
    public List<Book> getAllBooks(int page, int offset) {
        return null;
    }
}
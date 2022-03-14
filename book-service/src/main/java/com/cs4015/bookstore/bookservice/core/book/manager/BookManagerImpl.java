package com.cs4015.bookstore.bookservice.core.book.manager;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.exceptions.InvalidInputException;
import com.cs4015.bookstore.api.exceptions.NotFoundException;
import com.cs4015.bookstore.bookservice.core.book.mapper.BookMapper;
import com.cs4015.bookstore.bookservice.core.book.model.BookEntity;
import com.cs4015.bookstore.bookservice.core.book.repository.BookRepository;
import com.cs4015.bookstore.bookservice.core.book.services.BookServiceImpl;
import com.cs4015.bookstore.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookManagerImpl implements BookManager {

    private static final Logger logger = LoggerFactory.getLogger(BookManagerImpl.class);
    private final BookRepository repository;
    private final BookMapper bookMapper;
    private final ServiceUtil serviceUtil;

    @Autowired
    public BookManagerImpl(BookRepository bookRepository, BookMapper bookMapper, ServiceUtil serviceUtil){
        this.repository = bookRepository;
        this.bookMapper = bookMapper;
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Optional<Book> getBookById(long bookId) {
        BookEntity bookEntity = repository.findById(bookId).orElseThrow(() -> new NotFoundException("No Book found for productId: " + bookId));
        Book book = bookMapper.entityToApi(bookEntity).orElseThrow(() -> new InvalidInputException("Cannot convert the Book Entity to API"));
        logger.debug("getBook from the database: found bookId: " + book.getBookId());
        return Optional.of(book);
    }

    @Override
    public Optional<Book> saveBook(Book book) {
        BookEntity updateBook = bookMapper.apiToEntity(book).get();
        try{
            updateBook = repository.save(updateBook);
            return bookMapper.entityToApi(updateBook);

        }catch(Exception ex){
            logger.error("Error to update a book {} ", book);
            throw ex;
        }
    }

    @Override
    public void deleteBook(long bookId) {
        logger.debug("Delete a book {}", bookId);
        if(bookId < 1){
            throw new InvalidInputException("Invalid bookId: " + bookId);
        }
        BookEntity bookEntity = repository.findById(bookId).orElseThrow(()-> new NotFoundException("No book found for productId: " + bookId));
        repository.delete(bookEntity);

    }

    @Override
    public Optional<List<Book>> getAllBookWithPagination(int page, int offset) {
        if (page <= 0)
            page = 1;
        if (offset <= 0)
            offset = 10;
        try {
            Pageable pageable = PageRequest.of(page - 1, offset, Sort.Direction.ASC, "id");
            Page<BookEntity> books = repository.findAll(pageable);
            List<BookEntity> bookEntities = books.getContent();
            List<Book> rtnBooks = bookEntities.stream().map(entity -> bookMapper.entityToApi(entity).get()).collect(Collectors.toCollection(ArrayList<Book>::new));
            return Optional.of(rtnBooks);
        }catch(Exception ex){
            logger.error("An error to get AllBooks {}", ex);
            throw ex;
        }

    }
}

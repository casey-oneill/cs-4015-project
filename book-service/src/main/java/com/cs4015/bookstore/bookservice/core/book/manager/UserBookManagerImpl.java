package com.cs4015.bookstore.bookservice.core.book.manager;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.models.UserBooks;
import com.cs4015.bookstore.bookservice.core.book.mapper.MyBookMapper;
import com.cs4015.bookstore.bookservice.core.book.mapper.UserBooksMapper;
import com.cs4015.bookstore.bookservice.core.book.model.BookEntity;
import com.cs4015.bookstore.bookservice.core.book.repository.BookRepository;
import com.cs4015.bookstore.api.core.user.models.User;
import com.cs4015.bookstore.bookservice.core.user.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserBookManagerImpl implements UserBookManager{
    
    @Autowired
    private BookManager bookManager;
    
    @Autowired
    private MyBookMapper bookMapper;

    @Autowired
    private UserBooksMapper userBooksMapper;
    
    @Autowired
    private UserManager userManager;
    
    @Autowired
    private BookRepository bookRepository;

    @Override
    public UserBooks addBookToUser(Long userId, Book book)
    {
        Optional<User> userOptional = userManager.getUserById(userId);
        if(userOptional.isPresent()) {
            BookEntity bookEntity = bookMapper.apiToEntity(book).get();
            bookEntity.setUserId(userId);
            bookRepository.save(bookEntity);
            return getUsersBooks(userId);
        }
        return null;
    }

    @Override
    public UserBooks getUsersBooks(Long userId) {
        Optional<User> userOptional = userManager.getUserById(userId);
        if(userOptional.isPresent()){
            UserBooks userBooks = userBooksMapper.userEntityToUserBooks(userOptional.get());
            List<Book> books = bookManager.getBooksByUserId(userId).get();
            userBooks.setBooks(books);
            return userBooks;
        }
        return null;
    }
}

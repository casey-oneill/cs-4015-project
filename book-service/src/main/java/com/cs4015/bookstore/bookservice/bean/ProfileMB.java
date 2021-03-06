package com.cs4015.bookstore.bookservice.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.models.UserBooks;
import com.cs4015.bookstore.api.core.user.models.User;
import com.cs4015.bookstore.bookservice.core.book.manager.BookManager;
import com.cs4015.bookstore.bookservice.core.book.manager.UserBookManager;
import com.cs4015.bookstore.bookservice.util.CurrentUserProvider;
import com.cs4015.bookstore.bookservice.util.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@Component("profileMB")
@RequestScope
@Data
public class ProfileMB {

	@Autowired
	private BookManager bookManager;
	
	@Autowired
	private UserBookManager userBookManager;

	@Autowired
	private CurrentUserProvider currentUserProvider;

	@Autowired
	private MessageService messageService;

	private User currentUser;
	private List<Book> books;

	@PostConstruct
	public void init() {
		currentUser = currentUserProvider.getCurrentUser();
		loadUserBooks();
	}

	public void deleteUserBook(Book book) {
		try {
			bookManager.deleteBook(book.getBookId());
			messageService.showInfoMessage("Listing deleted successfully.");
			loadUserBooks();
		} catch (Exception e) {
			messageService.showErrorMessage("Failed to delete listing.", e.getMessage());
		}
	}

	public void loadUserBooks() {
		if (currentUser != null) {
			try {
				UserBooks userBooks = userBookManager.getUsersBooks(currentUser.getUserId());
				if (userBooks != null) {
					books = userBooks.getBooks();
				}
			} catch (Exception e) {
				messageService.showErrorMessage("Failed to fetch user listings.", e.getMessage());
			}
		}
	}
}

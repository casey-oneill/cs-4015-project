package com.cs4015.bookstore.bookservice.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.models.UserBooks;
import com.cs4015.bookstore.bookservice.core.book.manager.UserBookManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@Component("profileMB")
@RequestScope
@Data
public class ProfileMB {
	
	@Autowired
	private UserBookManager userBookManager;

	@Autowired
	private LogonMB logonMB; // FIXME: Use Spring security

	private List<Book> books;

	@PostConstruct
	public void init() {
		UserBooks userBooks = userBookManager.getUsersBooks(logonMB.getUser().getUserId());
		if (userBooks != null) {
			books = userBooks.getBooks();
		}
	}
}

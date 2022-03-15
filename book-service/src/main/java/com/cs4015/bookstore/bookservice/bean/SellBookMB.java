package com.cs4015.bookstore.bookservice.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import lombok.Data;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.models.BookType;
import com.cs4015.bookstore.api.core.book.models.Condition;
import com.cs4015.bookstore.api.core.book.models.DigitalBook;
import com.cs4015.bookstore.api.core.book.models.DigitalFormat;
import com.cs4015.bookstore.api.core.book.models.HardCoverBook;
import com.cs4015.bookstore.api.core.book.models.PaperBackBook;
import com.cs4015.bookstore.api.core.book.models.UserBooks;
import com.cs4015.bookstore.bookservice.core.book.manager.BookManagerImpl;
import com.cs4015.bookstore.bookservice.core.book.manager.UserBookManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sellBookMB")
@Data
public class SellBookMB {
	
	@Autowired
	private BookManagerImpl bookManager;

	@Autowired
	private UserBookManager userBookManager;

	@Autowired
	private LogonMB logonMB; // FIXME: Use Spring security

	private BookType bookType;
	private String title;
	private List<String> authors;
	private String newAuthor;
	private String selectedAuthor;
	private String description;
	private double price;
	private String photoUrl;
	private Condition condition;
	private DigitalFormat digitalFormat;

	@PostConstruct
	public void init() {
		// Reset form values
		title = "";
		authors = new ArrayList<>();
		selectedAuthor = "";
		price = 0;
		bookType = BookType.HARDCOVER;
	}

	public void save() {
		Book book;
		switch (bookType) {
			case DIGITAL:
				book = new DigitalBook(null, BookType.DIGITAL.name(), title, authors, description, price, photoUrl, digitalFormat, "");
				break;
			case HARDCOVER:
				book = new HardCoverBook(null, BookType.HARDCOVER.name(), title, authors, description, price, photoUrl, condition);
				break;
			default:
				// PAPERBACK
				book = new PaperBackBook(null, BookType.HARDCOVER.name(), title, authors, description, price, photoUrl, condition);
				break;
		}
		
		try {
			Optional<Book> result = bookManager.saveBook(book);
			UserBooks userBook = userBookManager.addBookToUser(logonMB.getUser().getUserId(), result.get());
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage("Posting created successfully."));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to create listing.", e.getMessage()));
		}
	}

	public void addAuthor() {
		if (newAuthor != null && !newAuthor.isBlank() && !authors.contains(newAuthor)) {
			authors.add(newAuthor);
		}
	}

	public void deleteSelectedAuthor() {
		if (selectedAuthor != null && !selectedAuthor.isBlank()) {
			authors.remove(selectedAuthor);
		}
	}
}

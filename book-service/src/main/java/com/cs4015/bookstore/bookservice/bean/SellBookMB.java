package com.cs4015.bookstore.bookservice.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.cs4015.bookstore.bookservice.core.book.manager.UserBookManagerImpl;
import lombok.Data;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.models.BookType;
import com.cs4015.bookstore.api.core.book.models.Condition;
import com.cs4015.bookstore.api.core.book.models.DigitalBook;
import com.cs4015.bookstore.api.core.book.models.DigitalFormat;
import com.cs4015.bookstore.api.core.book.models.HardCoverBook;
import com.cs4015.bookstore.api.core.book.models.PaperBackBook;
import com.cs4015.bookstore.bookservice.core.book.manager.BookManagerImpl;
import com.cs4015.bookstore.bookservice.core.book.manager.UserBookManager;
import com.cs4015.bookstore.bookservice.util.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component("sellBookMB")
@SessionScope
@Data
public class SellBookMB {
	
	@Autowired
	private BookManagerImpl bookManager;

	@Autowired
	private UserBookManager userBookManager;

	@Autowired
	private MessageService messageService;

	@Autowired
	private LoginMB loginMB; // FIXME: Use Spring security

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
		bookType = BookType.HARDCOVER;
		title = "";
		authors = new ArrayList<>();
		selectedAuthor = "";
		description = "";
		price = 0;
		photoUrl = "";
		condition = Condition.LIKENEW;
		digitalFormat = DigitalFormat.PDF;
	}

	public void save() {
		// Validate fields
		boolean isValid = true;
		if (price < 0) {
			messageService.showErrorMessage("Price cannot be less than $0.");
			isValid = false;
		}

		if (authors.size() <= 0) {
			messageService.showErrorMessage("At least one author is required.");
			isValid = false;
		}

		if (isValid) {
			Book book;
			switch (bookType) {
				case DIGITAL:
					book = new DigitalBook(null, BookType.DIGITAL.name(), title, authors, description, price, photoUrl, digitalFormat, "");
					break;
				case HARDCOVER:
					book = new HardCoverBook(null, BookType.HARDCOVER.name(), title, authors, description, price, photoUrl, condition);
					break;
				case PAPERBACK:
					book = new PaperBackBook(null, BookType.PAPERBACK.name(), title, authors, description, price, photoUrl, condition);
					break;
				default:
					return; // If book does not match expected type, don't create
			}
			
			try {
				//Optional<Book> result = bookManager.saveBook(book);
				userBookManager.addBookToUser(loginMB.getUser().getUserId(), book);
				messageService.showInfoMessage("Posting created successfully.");
			} catch (Exception e) {
				messageService.showErrorMessage("Failed to create listing", e.getMessage());
			}
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

package com.cs4015.bookstore.bookservice.bean;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.models.BookType;
import com.cs4015.bookstore.api.core.book.services.BookService;

@Component("buyBookMB")
@Data
public class BuyBookMB {
	
	private BookService bookService;

	private List<Book> allBooks;
	private List<Book> filteredBooks;

	private String title;
	private String author;
	private Double minPrice;
	private Double maxPrice;
	private BookType bookType;

	@Autowired
	public BuyBookMB(@Qualifier("mockService") BookService bookService) {
		this.bookService = bookService;
	}

	@PostConstruct
	public void init() {
		allBooks = bookService.getAllBooks(0, 0);

		filteredBooks = new ArrayList<>();
		allBooks.forEach((b) -> {
			filteredBooks.add(b);
		});

		// Reset all filters
		title = null;
		author = null;
		minPrice = null;
		maxPrice = null;
		bookType = null;
	}

	public void filter() {
		filteredBooks = new ArrayList<>();

		for (Book book : allBooks) {
			boolean isMatch = true;

			if (title != null && !title.isBlank() && !book.getTitle().toLowerCase().contains(title.toLowerCase())) {
				isMatch = false;
			}

			if (author != null && !author.isBlank()) {
				boolean hasAuthor = false;
				for (String a : book.getAuthors()) {
					if (a.toLowerCase().contains(author.toLowerCase()) || hasAuthor) {
						hasAuthor = true;
					}
				}
				isMatch = hasAuthor;
			}

			if (minPrice != null && maxPrice != null && minPrice <= maxPrice && (book.getPrice() < minPrice || book.getPrice() > maxPrice)) {
				isMatch = false;
			} else if (minPrice != null && maxPrice == null && book.getPrice() < minPrice) {
				isMatch = false;
			} else if (minPrice == null && maxPrice != null && book.getPrice() > maxPrice) {
				isMatch = false;
			}

			if (bookType != null && !book.getBookType().equals(bookType.name())) {
				isMatch = false;
			}

			if (isMatch) {
				filteredBooks.add(book);
			}
		}
	}
}

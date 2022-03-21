package com.cs4015.bookstore.bookservice.bean;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.models.BookType;
import com.cs4015.bookstore.bookservice.core.book.manager.BookManager;
import com.cs4015.bookstore.api.core.user.models.User;
import com.cs4015.bookstore.api.core.user.services.UserService;
import com.cs4015.bookstore.bookservice.model.BookListing;
import com.cs4015.bookstore.bookservice.model.BookListingAdapter;

@Component("buyBookMB")
@RequestScope
@Data
public class BuyBookMB {
	
	@Autowired
	private BookManager bookManager;

	@Autowired
	private UserService userService;

	private List<BookListing> allBooks;
	private List<BookListing> filteredBooks;

	private String title;
	private String author;
	private Double minPrice;
	private Double maxPrice;
	private BookType bookType;

	@PostConstruct
	public void init() {
		allBooks = new ArrayList<>();

		Optional<List<Book>> bookResults = bookManager.getAllBookWithPagination(1, 0);
		if (bookResults.isPresent()) {
			for (Book book : bookResults.get()) {
				Optional<User> userResult = userService.getUser(book.getUserId());
				if (userResult.isPresent()) {
					allBooks.add(new BookListingAdapter(book, userResult.get()));
				}
			}
		}

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

		for (BookListing book : allBooks) {
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

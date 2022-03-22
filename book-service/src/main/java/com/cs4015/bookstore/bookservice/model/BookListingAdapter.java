package com.cs4015.bookstore.bookservice.model;

import java.util.List;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.models.BookType;
import com.cs4015.bookstore.api.core.book.models.Condition;
import com.cs4015.bookstore.api.core.book.models.DigitalBook;
import com.cs4015.bookstore.api.core.book.models.DigitalFormat;
import com.cs4015.bookstore.api.core.book.models.HardCoverBook;
import com.cs4015.bookstore.api.core.book.models.PaperBackBook;
import com.cs4015.bookstore.api.core.user.models.User;

/**
 * Provides book listing information by combining book and user data.
 */
public class BookListingAdapter implements BookListing {

	private Book book;
	private User user;

	public BookListingAdapter(Book book, User user) {
		this.book = book;
		this.user = user;
	}

	@Override
	public String getBookType() {
		return book.getBookType();
	}

	@Override
	public String getTitle() {
		return book.getTitle();
	}

	@Override
	public List<String> getAuthors() {
		return book.getAuthors();
	}

	@Override
	public String getDescription() {
		return book.getDescription();
	}

	@Override
	public double getPrice() {
		return book.getPrice();
	}

	@Override
	public String getPhotoUrls() {
		return book.getPhotoUrls();
	}

	@Override
	public Condition getCondition() {
		if (book.getBookType().equals(BookType.HARDCOVER.name())) {
			return ((HardCoverBook) book).getCondition();
		} else if (book.getBookType().equals(BookType.PAPERBACK.name())) {
			return ((PaperBackBook) book).getCondition();
		}
		return null;
	}

	@Override
	public DigitalFormat getDigitalFormat() {
		if (book.getBookType().equals(BookType.DIGITAL.name())) {
			return ((DigitalBook) book).getDigitalFormat();
		}
		return null;
	}

	@Override
	public String getUserFullName() {
		return user.getFullName();
	}

	@Override
	public String getUserEmail() {
		return user.getEmail();
	}

	@Override
	public String getUserPhone() {
		return user.getPhone();
	}
}

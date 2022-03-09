package com.cs4015.bookstore.bookservice.core.book.services;

import com.cs4015.bookstore.api.core.book.models.Book;
import com.cs4015.bookstore.api.core.book.models.BookType;
import com.cs4015.bookstore.api.core.book.models.Condition;
import com.cs4015.bookstore.api.core.book.models.DigitalBook;
import com.cs4015.bookstore.api.core.book.models.DigitalFormat;
import com.cs4015.bookstore.api.core.book.models.HardCoverBook;
import com.cs4015.bookstore.api.core.book.models.PaperBackBook;
import com.cs4015.bookstore.api.core.book.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Qualifier("mockService")
public class BookServiceMockImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public Book getBook(long bookId) {
		List<String> authors = Arrays.asList(
			"Erich Gamma",
			"John Vlissides",
			"Richard Helm",
			"Raplh Johnson"
		);

		String desc = "Used for class CS4015. Condition like new.";
		
        return new PaperBackBook(bookId, "PAPERBACK", "Design Patterns: Elements of Reusable Software", authors, desc, 99.99, "https://abc/abc.jpg", Condition.LIKENEW);
    }

    @Override
    public Book replaceBook(Book book, long bookId) {
        return book;
    }

    @Override
    public Book addBook(Book book) {
        return book;
    }

    @Override
    public void deleteBook(long bookId) {
		
    }

    @Override
    public List<Book> getAllBooks(int page, int offset) {
		List<String> authors1 = Arrays.asList(
			"Erich Gamma",
			"John Vlissides",
			"Richard Helm",
			"Ralph Johnson"
		);

		List<String> authors2 = Arrays.asList(
			"Len Bass",
			"Paul Clements",
			"Rick Kazman"
		);

		String desc1 = "Used for class CS4015. Condition like new.";
		String desc2 = "Very good textbook and in decent condition. Well used.";
		String desc3 = "Selling copy of my digital Dive into Design Patterns textbook.";

        return Arrays.asList(
			new PaperBackBook(1L, "PAPERBACK", "Design Patterns: Elements of Reusable Software", authors1, desc1, 99.99, "https://via.placeholder.com/150", Condition.LIKENEW),
			new HardCoverBook(2L, "HARDCOVER", "Software Architecture in Practice", authors2, desc2, 60.00, "https://via.placeholder.com/200", Condition.FAIR),
			new DigitalBook(3L, "DIGITAL", "Dive into Design Patterns", Arrays.asList("Alexander Shvets"), desc3, 25.99, "https://via.placeholder.com/300", DigitalFormat.EPUB, "https://myurl/textbook")
		);
    }
}

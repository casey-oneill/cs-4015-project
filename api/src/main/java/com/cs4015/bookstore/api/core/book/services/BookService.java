package com.cs4015.bookstore.api.core.book.services;

import com.cs4015.bookstore.api.core.book.models.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface BookService {

    /**
     * Get a book by the given bookId.
     *
     * @param bookId Id of the book.
     * @return the book, if found, else null.
     */
    @GetMapping(
            value = "/books/{bookId}",
            produces = "application/json")
    Book getBook(@PathVariable long bookId);

    /**
     * Update a book with its bookId.
     * @param book A JSON representation of the Book to be updated.
     * @param bookId Id of the book.
     * @return the book, if updated, else null.
     */
    @PutMapping(
            value = "/books/{bookId}",
            consumes = "application/json",
            produces = "application/json"
    )
    Book replaceBook(@RequestBody Book book, @PathVariable long bookId);

    /**
     * Add a new Book.
     * @param book A JSON representation of the new Book to be added.
     * @return the book, if added, else null.
     */
    @PostMapping(
            value = "/books",
            consumes = "application/json",
            produces = "application/json"
    )
    Book addBook(@RequestBody Book book);

    /**
     * Delete a book by the given bookId.
     * @param bookId id of the deleted book.
     */
    @DeleteMapping("/books/{bookId}")
    void deleteBook(@PathVariable long bookId);

    /**
     * Get all books with pagination.
     * @param page the page number
     * @param offset the number of the rows in one page
     * @return a list of Books.
     */
    @GetMapping(
            value = "/books",
            produces = "application/json"
    )
    List<Book> getAllBooks(@RequestParam("page") int page, @RequestParam("offset") int offset);
}



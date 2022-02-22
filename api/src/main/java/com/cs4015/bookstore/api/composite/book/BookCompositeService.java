package com.cs4015.bookstore.api.composite.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface BookCompositeService {
    /**
     * Get the book composite infomation
     * @param bookId Id of the book
     * @return the composite book info, in found, else null
     */
    @GetMapping(
            value = "/book-composites/{bookId}",
            produces = "application/json"
    )
    BookAggregate getBooks(@PathVariable long bookId);
}

package com.cs4015.bookstore.api.core.review.service;

import java.util.List;

import com.cs4015.bookstore.api.core.review.model.Review;
import org.springframework.web.bind.annotation.*;

public interface ReviewService {

  /**
   * Add a new review.
   *
   * @param review A JSON representation of the new review
   * @return A JSON representation of the newly created review
   */
  @PostMapping(
      value    = "/review",
      consumes = "application/json",
      produces = "application/json")
  Review createReview(@RequestBody Review review);

  /**
   * Get a review by the given book Id.
   *
   * @param bookId Id of the book
   * @return the reviews of the book
   */
  @GetMapping(
    value = "/review",
    produces = "application/json")
  List<Review> getReviews(@RequestParam(value = "bookId", required = true) long bookId);

  /**
   * Delete a review by the given bookId.
   *
   * @param bookId Id of the product
   */
  @DeleteMapping(value = "/review")
  void deleteReviews(@RequestParam(value = "bookId", required = true)  int bookId);
}

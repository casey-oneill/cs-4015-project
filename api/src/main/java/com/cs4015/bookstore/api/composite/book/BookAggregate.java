package com.cs4015.bookstore.api.composite.book;

import com.cs4015.bookstore.api.core.review.model.Review;

import java.util.List;

public class BookAggregate {
    private final long bookId;
    private final String name;
    private final int weight;
    private final List<ReviewSummary> reviews;
    private final ServiceAddresses serviceAddresses;

    public BookAggregate(long bookId, String name, int weight, List<ReviewSummary> reviews, ServiceAddresses serviceAddresses) {
        this.bookId = bookId;
        this.name = name;
        this.weight = weight;
        this.reviews = reviews;
        this.serviceAddresses = serviceAddresses;
    }

    public long getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public List<ReviewSummary> getReviews() {
        return reviews;
    }

    public ServiceAddresses getServiceAddresses() {
        return serviceAddresses;
    }
}

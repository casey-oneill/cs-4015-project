package com.cs4015.bookstore.api.core.book.models;

public enum BookType {

    HARDCOVER(Values.HARDCOVER),
    PAPERBACK(Values.PAPERBACK),
    DIGITAL(Values.DIGITAL);

    private BookType(String val) {
        if(!this.name().equals(val))
            throw new IllegalArgumentException("Incorrect use of BookType");
    }

    public static class Values {
        public static final String HARDCOVER = "HARDCOVER";
        public static final String PAPERBACK = "PAPERBACK";
        public static final String DIGITAL = "DIGITAL";
    }
}

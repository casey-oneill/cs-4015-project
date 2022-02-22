package com.cs4015.bookstore.api.composite.book;

public class ServiceAddresses {
    private final String cmp;
    private final String bok;
    private final String rev;

    public ServiceAddresses() {
        cmp = null;
        bok = null;
        rev = null;
    }

    public ServiceAddresses(
            String compositeAddress,
            String bookAddress,
            String reviewAddress) {

        this.cmp = compositeAddress;
        this.bok = bookAddress;
        this.rev = reviewAddress;
    }

    public String getCmp() {
        return cmp;
    }

    public String getBok() {
        return bok;
    }

    public String getRev() {
        return rev;
    }
}

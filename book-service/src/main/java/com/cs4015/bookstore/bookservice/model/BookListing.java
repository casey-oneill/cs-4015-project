package com.cs4015.bookstore.bookservice.model;

import com.cs4015.bookstore.api.core.book.models.Condition;
import com.cs4015.bookstore.api.core.book.models.DigitalFormat;
import java.util.List;

/**
 * Used by the UI to display book listings.
 */
public interface BookListing {
	
    public String getBookType();
    public String getTitle();
    public List<String> getAuthors();
    public String getDescription();
    public double getPrice();
    public String getPhotoUrls();

	public Condition getCondition();

	public DigitalFormat getDigitalFormat();

    public String getUserFullName();
    public String getUserEmail();
    public String getUserPhone();
}

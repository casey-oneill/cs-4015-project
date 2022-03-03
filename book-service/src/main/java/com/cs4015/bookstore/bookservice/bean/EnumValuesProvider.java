package com.cs4015.bookstore.bookservice.bean;

import com.cs4015.bookstore.api.core.book.models.BookType;
import com.cs4015.bookstore.api.core.book.models.Condition;
import com.cs4015.bookstore.api.core.book.models.DigitalFormat;

import org.springframework.stereotype.Component;

@Component("enumValuesProvider")
public class EnumValuesProvider {
	
	public BookType[] bookTypes() {
		return BookType.values();
	}

	public BookType digitalBookType() {
		return BookType.DIGITAL;
	}

	public Condition[] conditions() {
		return Condition.values();
	}

	public DigitalFormat[] digitalFormats() {
		return DigitalFormat.values();
	}
}

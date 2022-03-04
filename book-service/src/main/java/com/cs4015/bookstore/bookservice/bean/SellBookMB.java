package com.cs4015.bookstore.bookservice.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import lombok.Data;

import com.cs4015.bookstore.api.core.book.models.BookType;
import com.cs4015.bookstore.api.core.book.models.Condition;
import com.cs4015.bookstore.api.core.book.models.DigitalFormat;

import org.springframework.stereotype.Component;

@Component("sellBookMB")
@Data
public class SellBookMB {
	
	private String title;
	private List<String> authors;
	private String newAuthor;
	private String selectedAuthor;
	private double price;
	private BookType bookType;
	private Condition condition;
	private DigitalFormat digitalFormat;

	@PostConstruct
	public void init() {
		// Reset form values
		title = "";
		authors = new ArrayList<>();
		selectedAuthor = "";
		price = 0;
		bookType = BookType.HARDCOVER;
	}

	public void save() {
		// TODO: Create Book object using BookFactory
		// TODO: Connect to API

		FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage("Posting created successfully."));
	}

	public void addAuthor() {
		if (newAuthor != null && !newAuthor.isBlank() && !authors.contains(newAuthor)) {
			authors.add(newAuthor);
		}
	}

	public void deleteSelectedAuthor() {
		if (selectedAuthor != null && !selectedAuthor.isBlank()) {
			authors.remove(selectedAuthor);
		}
	}
}

package com.cs4015.bookstore.bookservice.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageService {

	@Autowired
	FacesContext facesContext;
	
	private void showMessage(Severity severity, String message, String details) {
		facesContext.addMessage("messages", new FacesMessage(severity, message, details));
	}

	public void showInfoMessage(String message, String details) {
		showMessage(FacesMessage.SEVERITY_INFO, message, details);
	}

	public void showInfoMessage(String message) {
		showInfoMessage(message, null);
	}

	public void showWarnMessage(String message, String details) {
		showMessage(FacesMessage.SEVERITY_WARN, message, details);
	}

	public void showWarnMessage(String message) {
		showWarnMessage(message, null);
	}

	public void showErrorMessage(String message, String details) {
		showMessage(FacesMessage.SEVERITY_ERROR, message, details);
	}

	public void showErrorMessage(String message) {
		showErrorMessage(message, null);
	}
}

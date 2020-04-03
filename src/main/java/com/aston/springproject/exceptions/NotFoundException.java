package com.aston.springproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException{
	
	private static final long serialVersionUID = 1L;

	public NotFoundException(String reason) {
		super(HttpStatus.NOT_FOUND, reason);
	}
	
	public NotFoundException(String id, String classe) {
		this(" L'id "+id+" n'est pas valide pour "+classe);
	}
	

}

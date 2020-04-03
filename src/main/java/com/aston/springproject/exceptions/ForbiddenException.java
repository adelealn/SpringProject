package com.aston.springproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ForbiddenException extends ResponseStatusException{

	private static final long serialVersionUID = 1L;

	public ForbiddenException(String reason) {
		super(HttpStatus.FORBIDDEN, reason);
	}
	
	public ForbiddenException(String provenance, String pid, String destination, String did, String cause) {
		this(" Imposssible d'ajouter "+provenance+" d'id "+pid+" à "+destination+" d'id"+did+" car "+cause);
	}

	
}

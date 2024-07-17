package com.templates.ecommerce.responses;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ApiResponse extends ResponseEntity<String>{

	public ApiResponse(String body, HttpStatusCode status) {
		super(body, status);
	}

	
	
}

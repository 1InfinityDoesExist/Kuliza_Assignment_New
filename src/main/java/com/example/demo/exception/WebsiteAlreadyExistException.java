package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WebsiteAlreadyExistException extends RuntimeException {
	public WebsiteAlreadyExistException(String message) {
		super(message);
	}
}

package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class RuntimeExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<?> emailExceptionHandler(EmilAlreadyExistException ex, WebRequest webReqest) {
		EmailAlreadyExistResponse response = new EmailAlreadyExistResponse(ex.getMessage());
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<?> websiteExceptionHandler(WebsiteAlreadyExistException ex, WebRequest webRequest) {
		WebsiteAlreadyExistResponse response = new WebsiteAlreadyExistResponse(ex.getMessage());
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<?> phoneNumberExceptionHandler(PhoneNumberAlreadyExistException ex, WebRequest webRequest) {
		PhoneNumberAlreadyExistResponse response = new PhoneNumberAlreadyExistResponse(ex.getMessage());
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}
}

package com.example.demo.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class MapStateToError {

	public ResponseEntity<?> errorMapState(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new LinkedHashMap<String, String>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errors, HttpStatus.OK);
		}
		return null;
	}
}

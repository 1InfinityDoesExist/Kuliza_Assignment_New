package com.example.demo.exception;

public class BaseExceptionResponse {
	private String Exception;

	public BaseExceptionResponse(String exception) {
		super();
		Exception = exception;
	}

	public String getException() {
		return Exception;
	}

	public void setException(String exception) {
		Exception = exception;
	}

}

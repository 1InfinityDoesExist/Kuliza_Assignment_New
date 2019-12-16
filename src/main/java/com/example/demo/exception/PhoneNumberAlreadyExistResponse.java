package com.example.demo.exception;

public class PhoneNumberAlreadyExistResponse {
	private String phoneNumber;

	public PhoneNumberAlreadyExistResponse(String phoneNumber) {
		super();
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}

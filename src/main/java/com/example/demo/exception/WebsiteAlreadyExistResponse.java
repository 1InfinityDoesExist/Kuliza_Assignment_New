package com.example.demo.exception;

public class WebsiteAlreadyExistResponse {
	private String website;

	public WebsiteAlreadyExistResponse(String website) {
		super();
		this.website = website;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}

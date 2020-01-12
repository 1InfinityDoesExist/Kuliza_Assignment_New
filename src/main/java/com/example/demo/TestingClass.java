package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class TestingClass {

	private String test;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public TestingClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestingClass(String test) {
		super();
		this.test = test;
	}

	public void show() {
		System.out.println("Test:-" + test);
	}

}

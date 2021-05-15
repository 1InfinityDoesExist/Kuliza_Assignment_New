package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class KulizaAssignmentApplication {

	public static void main(String[] args) {
		log.info("-----Starting the application.....!!!!!");
		SpringApplication.run(KulizaAssignmentApplication.class, args);

	}

}

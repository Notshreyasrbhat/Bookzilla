package com.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ENTRY POINT: The spark that starts the entire Spring Boot server.
@SpringBootApplication
public class BookzillaApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookzillaApplication.class, args);
	}
}

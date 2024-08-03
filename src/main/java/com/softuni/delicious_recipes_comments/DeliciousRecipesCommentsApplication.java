package com.softuni.delicious_recipes_comments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DeliciousRecipesCommentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliciousRecipesCommentsApplication.class, args);
	}

}

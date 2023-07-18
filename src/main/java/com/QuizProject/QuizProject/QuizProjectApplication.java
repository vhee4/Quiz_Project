package com.QuizProject.QuizProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class QuizProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizProjectApplication.class, args);
	}

}

package com.nlp.nlp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NlpApplication {

	public static void main(String[] args) {
		SpringApplication.run(NlpApplication.class, args);
	}

}

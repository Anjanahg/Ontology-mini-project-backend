package com.example.MLB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MLBApplication {

	public static void main(String[] args) {
		OwlHelper.getInstance();
		SpringApplication.run(MLBApplication.class, args);
	}

}

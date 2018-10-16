package com.bgunduz.demospring01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class Demospring01Application {

	public static void main(String[] args) {
		SpringApplication.run(Demospring01Application.class, args);
	}
}

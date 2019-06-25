package com.rabbitwfly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Chapter15Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter15Application.class, args);
	}

}

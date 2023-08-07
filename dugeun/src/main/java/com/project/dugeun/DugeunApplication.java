package com.project.dugeun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DugeunApplication {
	public static void main(String[] args) {

		SpringApplication.run(DugeunApplication.class, args);

	}
}

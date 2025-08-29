package com.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // ?
public class Spring2250828Application {

	public static void main(String[] args) {

        SpringApplication.run(Spring2250828Application.class, args);
	}

}

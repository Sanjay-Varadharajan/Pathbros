package com.pathbros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PathbrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PathbrosApplication.class, args);
	}

}

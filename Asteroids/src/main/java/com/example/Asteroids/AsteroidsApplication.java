package com.example.Asteroids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.Asteroids.Client.RestClient;

@SpringBootApplication
public class AsteroidsApplication {
	
	//public static final Logger LOGGER = LoggerFactory.getLogger(AsteroidsApplication.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AsteroidsApplication.class, args);
	}

}

package com.example.Asteroids;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.context.annotation.Bean;
import com.example.Asteroids.Client.RestClient;

@SpringBootApplication
public class AsteroidsApplication {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AsteroidsApplication.class);

	public static void main(String[] args) throws Exception {
		RestClient r = new RestClient();
		r.getAsteroids();
		SpringApplication.run(AsteroidsApplication.class, args);
	}
	
	@Bean
	public WebClient webClient() {
		return WebClient.create("https://api.nasa.gov");
	} 
}

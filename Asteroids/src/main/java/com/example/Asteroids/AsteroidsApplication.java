package com.example.Asteroids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.Asteroids.Client.RestClient;

@SpringBootApplication
public class AsteroidsApplication {
	
	//public static final Logger LOGGER = LoggerFactory.getLogger(AsteroidsApplication.class);

	public static void main(String[] args) throws Exception {
		RestClient r = new RestClient();
		r.getInfo("2022-01-01", "2022-01-07");
		SpringApplication.run(AsteroidsApplication.class, args);
	}

}

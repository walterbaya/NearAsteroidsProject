package com.example.Asteroids;

import com.example.Asteroids.Client.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class AsteroidsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsteroidsApplication.class, args);
	}
	private static final Logger log = LoggerFactory.getLogger(AsteroidsApplication.class);

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote = restTemplate.getForObject(
					"https://api.nasa.gov/neo/rest/v1/feed?start_date=2022-01-4&end_date=&api_key=ed1x7CKGHSOBi5R9J7O5AfvXxS4I7gRtpyLB9z2G", Quote.class);
			Map<String,List<Map<String,Object>>> mapa = (Map<String,List<Map<String,Object>>>)quote.getNear_earth_objects();

			log.info(mapa.get("2022-01-07").get(0).get("is_potentially_hazardous_asteroid").toString());
		};
	}
}

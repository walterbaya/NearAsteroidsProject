package com.example.Asteroids.Client;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.Asteroids.AsteroidsApplication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RestController
@Configurable
public class RestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(AsteroidsApplication.class);

	@Autowired
	private WebClient webClient;


	@SuppressWarnings("unchecked")
	public void getAsteroids() throws Exception {
		Quote quote = this.webClient.get().uri("/neo/rest/v1/feed?start_date=2022-01-4&end_date=&api_key=ed1x7CKGHSOBi5R9J7O5AfvXxS4I7gRtpyLB9z2G").retrieve().bodyToMono(Quote.class)
				.block();
		Map<String, List<Map<String, Object>>> mapa = (Map<String, List<Map<String, Object>>>) quote
				.getNear_earth_objects();
		LOGGER.info(mapa.get("2022-01-07").get(0).get("is_potentially_hazardous_asteroid").toString());
	}
	
}

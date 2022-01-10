package com.example.Asteroids.Client;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
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
@Service
public class RestClient {

	private static final String BASE_URL = "https://api.nasa.gov";
	private static final String KEY =  "ed1x7CKGHSOBi5R9J7O5AfvXxS4I7gRtpyLB9z2G";
	private static final Logger LOGGER = LoggerFactory.getLogger(AsteroidsApplication.class);
	private static final WebClient webClient = WebClient.builder().baseUrl(BASE_URL).build();

	@SuppressWarnings("unchecked")
	public void getInfo(String startDate, String endDate) throws Exception {
		Quote quote = webClient.get().uri("/neo/rest/v1/feed?start_date=" + startDate + "&end_date=" + endDate + "&api_key=" + KEY).retrieve().bodyToMono(Quote.class)
				.block();
		Map<String, List<Map<String, Object>>> mapa = (Map<String, List<Map<String, Object>>>) quote.getNear_earth_objects();
		for (String key: mapa.keySet()) {
			LOGGER.info(mapa.get(key).get(0).get("is_potentially_hazardous_asteroid").toString());
		}
	
	}
	
}

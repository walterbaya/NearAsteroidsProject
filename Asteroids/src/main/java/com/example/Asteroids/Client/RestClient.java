package com.example.Asteroids.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.Asteroids.model.Asteroid;
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
	public static ArrayList<Asteroid> getAsteroids(String startDate, String endDate) throws Exception {
        ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
		Quote quote = webClient.get().uri("/neo/rest/v1/feed?start_date=" + startDate + "&end_date=" + endDate + "&api_key=" + KEY).retrieve().bodyToMono(Quote.class)
				.block();
		Map<String, List<Map<String, Object>>> mapa = (Map<String, List<Map<String, Object>>>) quote.getNear_earth_objects();
		for (String key: mapa.keySet()) {

            ArrayList<Map<String,Object>>map2 = (ArrayList<Map<String,Object>>)mapa.get(key).get(0).get("close_approach_data");
            Map<String, Object> relativeVelocity =(Map<String, Object>)  map2.get(0).get("relative_velocity");
            Map<String, Object> missDistance =(Map<String, Object>)  map2.get(0).get("miss_distance");
            Map<String,Object>mapDiameter = (Map<String,Object>)mapa.get(key).get(0).get("estimated_diameter");
            Map<String,Object>mapMeters = (Map<String,Object>)mapDiameter.get("meters");
            Map<String,Object>mapFeet = (Map<String,Object>)mapDiameter.get("feet");

			Asteroid asteroid = new Asteroid();
            asteroid.setIsPotentiallyHazardousAsteroid(mapa.get(key).get(0).get("is_potentially_hazardous_asteroid").toString());
            asteroid.setId(mapa.get(key).get(0).get("id").toString());
            asteroid.setNeo_reference_id(mapa.get(key).get(0).get("neo_reference_id").toString());
            asteroid.setName(mapa.get(key).get(0).get("name").toString());
            asteroid.setNasaJplUrl(mapa.get(key).get(0).get("nasa_jpl_url").toString());
            asteroid.setAbsoluteMagnitudeH(mapa.get(key).get(0).get("absolute_magnitude_h").toString());
            asteroid.setIsSentryObject(mapa.get(key).get(0).get("is_sentry_object").toString());

            asteroid.setEstimatedDiameterMinFeet(mapFeet.get("estimated_diameter_min").toString());
            asteroid.setEstimatedDiameterMinMeters(mapMeters.get("estimated_diameter_min").toString());
            asteroid.setEstimatedDiameterMaxFeet(mapFeet.get("estimated_diameter_max").toString());
            asteroid.setEstimatedDiameterMaxMeters(mapMeters.get("estimated_diameter_max").toString());

            asteroid.setCloseApproachDateFull(map2.get(0).get("close_approach_date_full").toString());
            asteroid.setOrbitingBody(map2.get(0).get("orbiting_body").toString());
            asteroid.setRelativeVelocityKilometersPerHour(relativeVelocity.get("kilometers_per_hour").toString());
            asteroid.setRelativeVelocityMilesPerHour(relativeVelocity.get("miles_per_hour").toString());
            asteroid.setMissDistanceKilometers(missDistance.get("kilometers").toString());
            asteroid.setMissDistanceMiles(missDistance.get("miles").toString());

            asteroids.add(asteroid);
        }
        return asteroids;
	}
	
}

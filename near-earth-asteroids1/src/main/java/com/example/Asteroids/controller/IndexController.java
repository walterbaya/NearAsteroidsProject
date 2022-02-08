package com.example.Asteroids.controller;

import com.example.Asteroids.AsteroidsApplication;
import com.example.Asteroids.Client.RestClient;
import com.example.Asteroids.model.Asteroid;
import com.example.Asteroids.model.TwoDates;
import com.example.Asteroids.model.TwoDatesLocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;


@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsteroidsApplication.class);

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("twoDates", new TwoDates());
        return "index";
    }

    @PostMapping("/dates")
    public String postingDates(@ModelAttribute TwoDates twoDates, Model model) throws Exception {
        model.addAttribute("twoDates", twoDates);
        TwoDatesLocalDate date = new TwoDatesLocalDate(twoDates.getFirstDate(), twoDates.getSecondDate());

        ArrayList<Asteroid> asteroids = RestClient.getAsteroids(date.getFrom(), date.getTo(), date.getDayDifference());
        model.addAttribute("asteroidName", asteroids.get(0).toString());
        model.addAttribute("numberOfAsteroids", String.valueOf(asteroids.size()));
        model.addAttribute("asteroids", asteroids);
        return "index";
    }
}

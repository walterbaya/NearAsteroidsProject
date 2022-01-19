package com.example.Asteroids.controller;

import com.example.Asteroids.Client.RestClient;
import com.example.Asteroids.model.Asteroid;
import com.example.Asteroids.model.TimeInterval;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.expression.Dates;

import java.sql.Time;
import java.util.ArrayList;

@Controller
public class IndexController {


    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("timeInterval", new TimeInterval());
        return "index";
    }

    @PostMapping("/setTimeInterval")
    public String greetingSubmit(@ModelAttribute TimeInterval timeInterval, Model model) throws Exception {
        model.addAttribute("timeInterval", timeInterval);
        ArrayList<Asteroid> asteroids = RestClient.getAsteroids(timeInterval.getFrom(), timeInterval.getTo());
        model.addAttribute("asteroidName", asteroids.get(0).toString());
        model.addAttribute("numberOfAsteroids", String.valueOf(asteroids.size()));
        model.addAttribute("asteroids", asteroids);
        return "index";
    }

}

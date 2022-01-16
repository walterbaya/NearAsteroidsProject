package com.example.Asteroids.controller;

import com.example.Asteroids.Client.RestClient;
import com.example.Asteroids.model.Asteroid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class IndexController {

    @GetMapping("/")
    public String greeting(Model model) throws Exception {
        ArrayList<Asteroid> asteroids = RestClient.getAsteroids("2022-01-9","2022-01-16");
        model.addAttribute("asteroidName", asteroids.get(0).toString());
        model.addAttribute("numberOfAsteroids", String.valueOf(asteroids.size()));
        model.addAttribute("asteroids", asteroids);
        return "index";
    }

}

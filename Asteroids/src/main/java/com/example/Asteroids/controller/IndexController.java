package com.example.Asteroids.controller;

import com.example.Asteroids.Client.RestClient;
import com.example.Asteroids.model.Asteroid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class IndexController {

    @GetMapping("/")
    public String greeting(Model model) throws Exception {
        ArrayList<Asteroid> asteroids = RestClient.getAsteroids("2022-01-1","2022-01-7");
        model.addAttribute("asteroidName", asteroids.get(0).toString());
        model.addAttribute("numberOfAsteroids", asteroids.size());
        return "index";
    }

}

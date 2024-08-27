package com.crio.learningNavigator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/hidden-feature")
public class EasterEggController {

    private final RestTemplate restTemplate;

    public EasterEggController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{number}")
    public ResponseEntity<String> getNumberFact(@PathVariable("number") int number) {
        String url = "http://numbersapi.com/" + number;
        String fact = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(fact);
    }
}
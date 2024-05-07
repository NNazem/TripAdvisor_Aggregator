package org.example.kafkatest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired KafkaTemplate<String, String> kafkaTemplate;

    private static final String LOCATION = "location";

    @GetMapping("/publish")
    public ResponseEntity<String> getNearbyLocations(@RequestParam String latitude, @RequestParam String longitude, @RequestParam(defaultValue = "100") String radius) throws IOException, InterruptedException {
        String apiKey = "46D896B8B5E84AC1AA88BA80B9E7CF58";


        String url = "https://api.content.tripadvisor.com/api/v1/location/nearby_search?latLong=" + String.format("%.4s", latitude) + "%2C" + String.format("%.4s", longitude) + "&key=" + apiKey + "&radius=" + String.format("%.4s", radius) + "&language=en";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        kafkaTemplate.send(LOCATION, response.body());

        return ResponseEntity.ok("Published Successfully");
    }

}
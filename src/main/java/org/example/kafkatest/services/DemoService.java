package org.example.kafkatest.services;

import lombok.AllArgsConstructor;
import org.example.kafkatest.LocationRepository;
import org.example.kafkatest.ReviewRepository;
import org.example.kafkatest.entities.Location;
import org.example.kafkatest.entities.Review;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@AllArgsConstructor
public class DemoService {

    private LocationRepository locationRepository;
    private ReviewRepository reviewRepository;

    public void saveLocation(Location location) throws IOException, InterruptedException {
        String location_id = location.getLocation_id();

        String url = "http://localhost:8080/searchReview?location_id=" + location_id;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.discarding());

        locationRepository.save(location);
    }

    public void saveReview(Review review) throws IOException, InterruptedException {
        reviewRepository.save(review);
    }
}

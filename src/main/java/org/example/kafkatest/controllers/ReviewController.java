package org.example.kafkatest.controllers;

import lombok.AllArgsConstructor;
import org.example.kafkatest.aggregations.ReviewAggregationResult;
import org.example.kafkatest.entities.Review;
import org.example.kafkatest.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {

    private ReviewService reviewService;

    @Autowired KafkaTemplate<String, String> kafkaTemplate;

    private static final String REVIEW = "review";

    @GetMapping("/publish")
    public ResponseEntity<String> getLocationReviews(@RequestParam String location_id) throws IOException, InterruptedException {
        String apiKey = "46D896B8B5E84AC1AA88BA80B9E7CF58";

        String url = "https://api.content.tripadvisor.com/api/v1/location/" + location_id + "/reviews?key=" + apiKey + "&language=en";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        kafkaTemplate.send(REVIEW, response.body());

        return ResponseEntity.ok("Published Successfully");
    }

    @GetMapping("/{location_id}")
    public ResponseEntity<List<Review>> getReviews(@PathVariable String location_id){
        List<Review> reviews = reviewService.getReviewsByLocationId(location_id);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping
    public ResponseEntity<List<ReviewAggregationResult>> getAllReviews(){
        return ResponseEntity.ok(reviewService.getReviewAggregationResult());
    }
}